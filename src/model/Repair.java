package model;

import java.sql.Date;

public class Repair {

    private int repairID;
    private int carID;
    private String damage;
    private int price;
    private Date inDate;
    private Date outDate;

    //constructors
    public Repair() {
    }
    public Repair(int repairID, int carID, String damage, int price, Date inDate, Date outDate) {
        this.repairID = repairID;
        this.carID = carID;
        this.damage = damage;
        this.price = price;
        this.inDate = inDate;
        this.outDate = outDate;
    }
    public Repair(int carID, String damage, int price, Date inDate, Date outDate) {
        this.carID = carID;
        this.damage = damage;
        this.price = price;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    //getters
    public int getRepairID() {
        return repairID;
    }
    public int getCarID() {
        return carID;
    }
    public String getDamage() {
        return damage;
    }
    public int getPrice() {
        return price;
    }
    public Date getInDate() {
        return inDate;
    }
    public Date getOutDate() {
        return outDate;
    }

    //setters
    public void setRepairID(int repairID) {
        this.repairID = repairID;
    }
    public void setCarID(int carID) {
        this.carID = carID;
    }
    public void setDamage(String damage) {
        this.damage = damage;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }
    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

}