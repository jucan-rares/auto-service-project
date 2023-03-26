package controller;

import dao.CarDAO;
import dao.UserDAO;
import model.User;
import view.MainGUI;
import view.PendingRepairsGUI;

import javax.swing.*;
import java.util.Objects;

public class MainController {

    private final MainGUI mainGUI;

    //constructor
    public MainController() {
        this.mainGUI = new MainGUI();
        init();
    }

    //method
    public void init() {

        mainGUI.getRegister().addActionListener(e -> {
            mainGUI.setVisible(false);
            new RegisterController(mainGUI);
        });

        mainGUI.getSignIn().addActionListener(e -> {

            if (mainGUI.blank()) {
                JOptionPane.showMessageDialog(null, "Field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            UserDAO userDAO = new UserDAO();
            User user = userDAO.find(mainGUI.getUsername());

            User dummy = new User();
            dummy.setPassword(mainGUI.getPassword());
            dummy.encrypt();

            if (!Objects.equals(user.getPassword(), dummy.getPassword())) {
                JOptionPane.showMessageDialog(null, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "Signed in successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            if (Objects.equals(user.getRole(), "Client")) {
                mainGUI.setVisible(false);
                new ClientController(mainGUI.getUsername());
            } else {
                mainGUI.setVisible(false);

                CarDAO carDAO = new CarDAO();
                new PendingRepairsGUI(carDAO.findAll());
                new AdminController();
            }

        });
    }
}