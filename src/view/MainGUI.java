package view;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private final JTextField username = new JTextField();
    private final JPasswordField password = new JPasswordField();

    private final JButton register = new JButton("Register now");
    private final JButton signIn = new JButton("Sign in");

    //constructor
    public MainGUI() {

        JPanel content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        content.setLayout(new GridLayout(0, 2));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        content.add(new JLabel("Welcome!"));
        content.add(new JLabel(""));

        content.add(new JLabel(""));
        content.add(new JLabel(""));

        content.add(new JLabel("Username:"));
        content.add(username);

        content.add(new JLabel("Password:"));
        content.add(password);

        content.add(new JLabel(""));
        content.add(signIn);

        content.add(new JLabel(""));
        content.add(new JLabel(""));

        content.add(new JLabel("Not registered?"));
        content.add(register);
    }

    //getters
    public String getUsername() {
        return username.getText();
    }
    public String getPassword() {
        return String.copyValueOf(password.getPassword());
    }
    public JButton getRegister() {
        return register;
    }
    public JButton getSignIn() {
        return signIn;
    }

    //method
    public boolean blank(){
        return username.getText().isBlank() || password.getText().isBlank();
    }
}