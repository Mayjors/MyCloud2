package com.example.algorithm.pattern.decorator;

public class Decorator_zero extends Decorator {
    public Decorator_zero(Human human) {
        super(human);
    }

    public void goHome() {
        System.out.println("进房子。。");
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        goHome();
    }
}
