package view;

import model.Car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PendingRepairsGUI extends JFrame {

    //constructor
    public PendingRepairsGUI(List<Car> cars) {

        JPanel content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setSize(1000, 750);

        model.addColumn("id");
        model.addColumn("brand");
        model.addColumn("model");
        model.addColumn("year");
        model.addColumn("color");
        model.addColumn("damage");
        model.addColumn("owner");

        for (Car car : cars) {
            Object[] row = new Object[7];
            row[0] = car.getCarID();
            row[1] = car.getBrand();
            row[2] = car.getModel();
            row[3] = car.getYear();
            row[4] = car.getColor();
            row[5] = car.getDamage();
            row[6] = car.getOwner();
            model.addRow(row);
        }

        content.add(new JScrollPane(table));
    }

}
