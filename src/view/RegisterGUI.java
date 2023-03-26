package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RegisterGUI extends JFrame {

    private final JComboBox role = new JComboBox();

    private final JTextField username = new JTextField();
    private final JTextField password = new JTextField();
    private final JTextField email = new JTextField();
    private final JTextField phoneNumber = new JTextField();

    private final JButton register = new JButton("Register");

    //constructor
    public RegisterGUI() {

        JPanel content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        content.setLayout(new GridLayout(0, 2));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        content.add(new JLabel("Username:"));
        content.add(username);

        content.add(new JLabel("Password:"));
        content.add(password);

        role.addItem("Administrator");
        role.addItem("Client");
        content.add(new JLabel("Role:"));
        content.add(role);

        content.add(new JLabel("Email:"));
        content.add(email);

        content.add(new JLabel("Phone number:"));
        content.add(phoneNumber);

        content.add(new JLabel(""));
        content.add(register);
    }

    //getters
    public String getRole() {
        return Objects.requireNonNull(role.getSelectedItem()).toString();
    }
    public String getUsername() {
        return username.getText();
    }
    public String getPassword() {
        return password.getText();
    }
    public String getEmail() {
        return email.getText();
    }
    public String getPhoneNumber() {
        return phoneNumber.getText();
    }
    public JButton getRegister() {
        return register;
    }

    //method
    public boolean blank(){
        return username.getText().isBlank() || password.getText().isBlank() || email.getText().isBlank() || phoneNumber.getText().isBlank();
    }
}