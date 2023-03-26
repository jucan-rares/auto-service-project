package dao;

import model.User;

import java.util.NoSuchElementException;

public class UserDAO extends AbstractDAO<User> {

    //method
    public User find(String username) {

        User user = findByUsername(username);

        if (user == null)
            throw new NoSuchElementException("Error! The user " + username + " could not be found.");

        return user;
    }
}