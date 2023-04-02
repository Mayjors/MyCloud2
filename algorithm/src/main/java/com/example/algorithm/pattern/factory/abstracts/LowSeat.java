package com.example.algorithm.pattern.factory.abstracts;

public class LowSeat implements Seat {
    @Override
    public void message() {
        System.out.println("不能按摩！");
    }
}
