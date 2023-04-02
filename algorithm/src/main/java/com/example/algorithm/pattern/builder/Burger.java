package com.example.algorithm.pattern.builder;

/**
 * 汉堡
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

}
