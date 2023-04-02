package com.example.algorithm.pattern.decorator;

public class Decorator_two extends Decorator {
    public Decorator_two(Human human) {
        super(human);
    }

    public void findClothes() {
        System.out.println("找到一件D&G。。");
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        findClothes();
    }
}
