package com.example.algorithm.pattern.adapter;

/**
 * 已存在，具有特殊功能、但不符合我们既有标准接口的类
 */
public class Adaptee {
    public void specificRequest() {
        System.out.println("被适配类，具有特殊功能...");
    }
}
