package view;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {

    private final JTextField brand = new JTextField();
    private final JTextField model = new JTextField();
    private final JTextField year = new JTextField();
    private final JTextField color = new JTextField();
    private final JTextField damage = new JTextField();

    private final JButton makeReservation = new JButton("Make an appointment");
    private final JButton update = new JButton("Update car's info");

    //method
    public ClientGUI() {

        JPanel content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        content.setLayout(new GridLayout(0, 2));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        content.add(new JLabel("Input your car's info"));
        content.add(new JLabel(""));

        content.add(new JLabel("Brand:"));
        content.add(brand);

        content.add(new JLabel("Model:"));
        content.add(model);

        content.add(new JLabel("Year of fabrication:"));
        content.add(year);

        content.add(new JLabel("Color:"));
        content.add(color);

        content.add(new JLabel("What do you want to be changed?"));
        content.add(damage);

        content.add(update);
        content.add(makeReservation);
    }

    //getters
    public String getBrand() {
        return brand.getText();
    }
    public String getModel() {
        return model.getText();
    }
    public int getYear() {
        return Integer.parseInt(year.getText());
    }
    public String getColor() {
        return color.getText();
    }
    public String getDamage() {
        return damage.getText();
    }
    public JButton getMakeReservation() {
        return makeReservation;
    }
    public JButton getUpdate() {
        return update;
    }

    //method
    public boolean blank(){
        return brand.getText().isBlank() || model.getText().isBlank() || year.getText().isBlank() || color.getText().isBlank() || damage.getText().isBlank();
    }
}