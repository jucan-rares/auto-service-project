package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class AdminGUI extends JFrame{

    private final JTextField carID = new JTextField();
    private final JTextField damage = new JTextField();
    private final JTextField price = new JTextField();
    private final JTextField inDate = new JTextField();
    private final JTextField outDate = new JTextField();

    private final JButton notify = new JButton("Notify the user");

    //method
    public AdminGUI() {

        JPanel content = new JPanel();
        setContentPane(content);
        setSize(1000, 750);
        content.setLayout(new GridLayout(0, 2));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        content.add(new JLabel("Car id:"));
        content.add(carID);

        content.add(new JLabel("Work done on the car:"));
        content.add(damage);

        content.add(new JLabel("Price of reparation:"));
        content.add(price);

        content.add(new JLabel("Date for the car to come in (YYYY-MM-DD):"));
        content.add(inDate);

        content.add(new JLabel("Date for the car to be picked up (YYYY-MM-DD):"));
        content.add(outDate);

        content.add(new JLabel(""));
        content.add(notify);
    }

    //getters
    public int getCarID() {
        return Integer.parseInt(carID.getText());
    }
    public String getDamage() {
        return damage.getText();
    }
    public int getPrice() {
        return Integer.parseInt(price.getText());
    }
    public Date getInDate() {
        return Date.valueOf(inDate.getText());
    }
    public Date getOutDate() {
        return Date.valueOf(outDate.getText());
    }
    public JButton getNotify() {
        return notify;
    }

    //method
    public boolean blank(){
        return price.getText().isBlank() || inDate.getText().isBlank() || outDate.getText().isBlank();
    }
}
