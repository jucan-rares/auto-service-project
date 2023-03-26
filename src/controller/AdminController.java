package controller;

import dao.CarDAO;
import dao.RepairDAO;
import model.Repair;
import view.AdminGUI;

import javax.swing.*;

public class AdminController {

    private final AdminGUI adminGUI;

    //constructor
    public AdminController() {
        this.adminGUI = new AdminGUI();
        init();
    }

    //methods
    public void init() {

        adminGUI.getNotify().addActionListener(e -> {

            if (adminGUI.blank()) {
                JOptionPane.showMessageDialog(null, "Field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Repair repair = new Repair(adminGUI.getCarID(), adminGUI.getDamage(), adminGUI.getPrice(), adminGUI.getInDate(), adminGUI.getOutDate());
            RepairDAO repairDAO = new RepairDAO();

            if (repairDAO.insert(repair) == -1) {
                JOptionPane.showMessageDialog(null, "Cannot schedule the repair!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "The repair has been scheduled!", "Success", JOptionPane.INFORMATION_MESSAGE);

        });
    }
}