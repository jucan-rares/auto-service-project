package dao;

import model.Car;

import java.util.NoSuchElementException;

public class CarDAO extends AbstractDAO<Car> {

    public Car find(String owner) {

        Car car = findByOwner(owner);

        if (car == null)
            throw new NoSuchElementException("Error! The car with the owner " + owner + " could not be found.");

        return car;
    }
}