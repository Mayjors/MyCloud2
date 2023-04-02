package com.example.algorithm.pattern.factory;

public class Customer {
    public static void main(String[] args) {
        Car car1 = new AudiFactory().createCar();
        car1.run();

        Car car2 = new BenzFactory().createCar();
        car2.run();
    }
}
