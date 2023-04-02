package com.example.algorithm.pattern.adapter;

public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("普通类，具有普通功能");
    }
}
