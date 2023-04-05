package com.example.algorithm.pattern.observer;

/**
 * 被观察者
 */
public class WeixinUser implements Observer {
    private String name;

    public WeixinUser(String name) {
        this.name = name;
    }


    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}
