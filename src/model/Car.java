package model;

public class Car {

    private int carID;
    private String brand;
    private String model;
    private int year;
    private String color;
    private String damage;
    private String owner;

    //constructors
    public Car() {
    }
    public Car(int carID, String brand, String model, int year, String color, String damage, String owner) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.damage = damage;
        this.owner = owner;
    }
    public Car(String brand, String model, int year, String color, String damage, String owner) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.damage = damage;
        this.owner = owner;
    }

    //getters
    public int getCarID() {
        return carID;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public String getColor() {
        return color;
    }
    public String getDamage() {
        return damage;
    }
    public String getOwner() {
        return owner;
    }

    //setters
    public void setCarID(int carID) {
        this.carID = carID;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setDamage(String damage) {
        this.damage = damage;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    //methods
    public String carName() {
        return brand + model + year + color;
    }
}