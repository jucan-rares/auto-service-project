package controller;

import dao.CarDAO;
import model.Car;
import view.ClientGUI;

import javax.swing.*;

public class ClientController {

    private final ClientGUI clientGUI;

    //constructor
    public ClientController(String owner) {
        this.clientGUI = new ClientGUI();
        init(owner);
    }

    //method
    public void init(String owner) {

        clientGUI.getMakeReservation().addActionListener(e -> {

            if (clientGUI.blank()) {
                JOptionPane.showMessageDialog(null, "Field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Car car = new Car(clientGUI.getBrand(), clientGUI.getModel(), clientGUI.getYear(), clientGUI.getColor(), clientGUI.getDamage(), owner);
            CarDAO carDAO = new CarDAO();

            if (carDAO.insert(car) == -1) {
                JOptionPane.showMessageDialog(null, "Cannot make appointment!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "Your appointment has been made!", "Success", JOptionPane.INFORMATION_MESSAGE);

        });

        clientGUI.getUpdate().addActionListener(e -> {

            if (clientGUI.blank()) {
                JOptionPane.showMessageDialog(null, "Field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Car car = new Car(clientGUI.getBrand(), clientGUI.getModel(), clientGUI.getYear(), clientGUI.getColor(), clientGUI.getDamage(), owner);
            CarDAO carDAO = new CarDAO();
            carDAO.update(car);

            JOptionPane.showMessageDialog(null, "Your car's info has been updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}