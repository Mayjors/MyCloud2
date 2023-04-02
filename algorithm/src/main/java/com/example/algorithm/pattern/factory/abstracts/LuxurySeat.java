package com.example.algorithm.pattern.factory.abstracts;

public class LuxurySeat implements Seat {
    @Override
    public void message() {
        System.out.println("可以自动按摩！");
    }
}
