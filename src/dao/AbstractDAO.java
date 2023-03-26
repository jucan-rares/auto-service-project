package dao;

import connection.ConnectionFactory;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private List<T> createObjects(ResultSet rs) {

        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        int l = constructors.length;
        Constructor constructor = null;

        for (Constructor item : constructors) {

            constructor = item;
            if (constructor.getGenericParameterTypes().length == 0)
                break;
        }
        try {

            while (rs.next()) {

                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance();

                for (Field field : type.getDeclaredFields()) {

                    String fieldName = field.getName();
                    Object value = rs.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }

                list.add(instance);
            }
        }
        //error cases
        catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }

        return list;
    } //the method that transforms a given result set into a list of usable objects (basically the most important method here)

    private String createSelectAllQuery() {
        return "SELECT * FROM " + type.getSimpleName() + "s";
    } //we create a "SELECT * FROM entity" type query

    private String createSelectQuery(String givenField) {
        return  "SELECT * FROM " + type.getSimpleName() + "s WHERE " + givenField + " = ?";
    } //we create a "SELECT * FROM entity WHERE given_field = given_value" type query

    private String createInsertQuery() {

        int cont = 0, end = 0, l = type.getDeclaredFields().length;

        String s = "INSERT INTO " + type.getSimpleName() + "s (";

        for (Field field : type.getDeclaredFields()) {

            cont++; //current field

            if (cont == l) { //we reached the last field

                end = 1;
                s = s.concat(field.getName() + ") VALUES (");
            }
            if (cont > 1 && end == 0) //there are more fields
                s = s.concat(field.getName() + ", ");
        } // first we traverse and select all the fields

        for (int i = 0; i < l; ++i) {

            if (i == l - 2) { //if it's the last value

                s = s.concat("?)");
                break;
            }

            s = s.concat("?, ");
        } //then we insert all the values

        return s;
    } //we create a "INSERT INTO entity (fields) VALUES (values)" type query

    private String createDeleteQuery(String id) {
        return "DELETE FROM " + type.getSimpleName() + "s WHERE " + id + " = ?";
    } //we create a "DELETE FROM entity WHERE id = given value" type query

    private String createUpdateQuery(Field[] fields) {

        String s = "UPDATE " + type.getSimpleName() + "s SET ";

        for (int i = 1; i < fields.length - 1; ++i)
            s = s.concat(fields[i].getName() + " = ?, ");

        s = s.concat(fields[fields.length - 1].getName() + "  = ? WHERE " + fields[0].getName() + " = ?");

        return s;
    } //we create a "UPDATE entity SET id = given value" type query

    public int insert(T t) {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        int insertedID = -1;

        try {

            statement = connection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < t.getClass().getDeclaredFields().length - 1; ++i) {

                Field field = t.getClass().getDeclaredFields()[i + 1];
                field.setAccessible(true);
                Object objValue = field.get(t);
                statement.setObject(i + 1, objValue);
            }
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next())
                insertedID = rs.getInt(1);
        } catch (SQLException e) { //error case

            LOGGER.log(Level.WARNING, "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) { //error case

            throw new RuntimeException(e);
        } finally { //we close the connections

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return insertedID;
    }

    public T update(T t) {

        Field[] fieldsToUpdate = type.getDeclaredFields();
        Connection databaseConnection = null;
        PreparedStatement updateStatement = null;
        String updateQuery = createUpdateQuery(fieldsToUpdate);

        try {
            databaseConnection = ConnectionFactory.getConnection();
            updateStatement = databaseConnection.prepareStatement(updateQuery);

            fieldsToUpdate[0].setAccessible(true);
            for (int i = 1; i < fieldsToUpdate.length; ++i) {     // start from i = 1, in order to skip over the possibility of updating the ID
                fieldsToUpdate[i].setAccessible(true);
                updateStatement.setObject(i, fieldsToUpdate[i].get(t));
            }
            fieldsToUpdate[fieldsToUpdate.length - 1].setAccessible(true);
            updateStatement.setObject(fieldsToUpdate.length, fieldsToUpdate[0].get(t));
            updateStatement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(databaseConnection);
        }

        return null;
    }

    public int delete(int id) {

        Connection connection = null;
        PreparedStatement statement = null;
        String s = createDeleteQuery(type.getDeclaredFields()[0].getName());

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(s);

            statement.setInt(1, id);
            statement.executeUpdate();

            return id; //we return the id of the deleted item to notify that it's all good
        } catch (SQLException e) { //error case

            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally { //we close the connections

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return -1; //if we get here it means something went wrong
    }

    public List<T> findAll() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        String s = createSelectAllQuery();

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(s);

            rs = statement.executeQuery(); //get the data

            return createObjects(rs); //return that data as an object
        } catch (SQLException e) { //error case

            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll" + e.getMessage());
        } finally { //we close the connections

            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    public T findByUsername(String username) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        String s = createSelectQuery(type.getDeclaredFields()[2].getName());

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(s);
            statement.setString(1, username);

            rs = statement.executeQuery(); //get the data

            List<T> objects = createObjects(rs); //transform the data in

            if (objects.size() == 0)
                return null;
            else
                return objects.get(0);

        } catch (SQLException e) { //error case

            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByUsername" + e.getMessage());
        } finally { //we close the connections

            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }
    public T findByOwner(String owner) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String s = createSelectQuery(type.getDeclaredFields()[6].getName());

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(s);
            statement.setString(1, owner);

            rs = statement.executeQuery(); //get the data

            List<T> objects = createObjects(rs); //transform the data in

            if (objects.size() == 0)
                return null;
            else
                return objects.get(0);
        } catch (SQLException e) { //error case

            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByOwner" + e.getMessage());
        } finally { //we close the connections

            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }
}