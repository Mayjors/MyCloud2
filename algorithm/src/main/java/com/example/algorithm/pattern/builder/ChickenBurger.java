package com.example.algorithm.pattern.builder;

public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public float price() {
        return 30;
    }
}
