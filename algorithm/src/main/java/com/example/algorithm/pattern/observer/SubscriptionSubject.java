package com.example.algorithm.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionSubject implements Subject {
    private List<Observer> weixinUserList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        weixinUserList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        weixinUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserList) {
            observer.update(message);
        }
    }
}
