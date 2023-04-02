package com.example.algorithm.pattern.decorator;

public class Decorator_first extends Decorator {
    public Decorator_first(Human human) {
        super(human);
    }

    public void goClothespress() {
        System.out.println("去衣柜找找看。。");
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        goClothespress();
    }
}
