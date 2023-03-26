package controller;

import dao.UserDAO;
import model.User;
import view.MainGUI;
import view.RegisterGUI;

import javax.swing.*;
import java.util.Objects;

public class RegisterController {

    private final RegisterGUI registerGUI;

    //constructor
    public RegisterController(MainGUI mainGUI) {
        this.registerGUI = new RegisterGUI();
        init(mainGUI);
    }

    //method
    public void init(MainGUI mainGUI) {

        registerGUI.getRegister().addActionListener(e -> {

            if (registerGUI.blank()) {
                JOptionPane.showMessageDialog(null, "Field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = new User(registerGUI.getRole(), registerGUI.getUsername(), registerGUI.getPassword(), registerGUI.getEmail(), registerGUI.getPhoneNumber());
            user.encrypt();
            UserDAO userDAO = new UserDAO();

            if (Objects.equals(user.getRole(), "Administrator") && !Objects.equals(user.getPassword(), "304dd31d9a0cce1f69fd6ccdf2bb1c72")) {
                JOptionPane.showMessageDialog(null, "Your ass is not an admin!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (userDAO.insert(user) == -1) {
                JOptionPane.showMessageDialog(null, "Cannot register new user!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "New user was registered!", "Success!", JOptionPane.INFORMATION_MESSAGE);

            registerGUI.setVisible(false);
            mainGUI.setVisible(true);
        });
    }
}