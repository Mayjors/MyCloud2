package com.example.algorithm.pattern.adapter;

/**
 * 对象的适配器类，实现标准接口
 * 直接关联被适配类
 */
public class Adapter2 implements Target {
    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        this.adaptee.specificRequest();
    }
}
