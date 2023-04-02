package com.example.algorithm.pattern.builder;

/**
 * 食物条目的接口
 */
public interface Item {
    String name();
    Packing packing();
    float price();
}
