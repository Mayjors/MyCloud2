package com.example.algorithm.pattern.adapter;

public class Client {
    public static void main(String[] args) {
        Target concreateTarget = new ConcreteTarget();
        concreateTarget.request();

        Target adapter = new Adapter();
        adapter.request();

        Target adapter2 = new Adapter2(new Adaptee());
        adapter2.request();
    }
}
