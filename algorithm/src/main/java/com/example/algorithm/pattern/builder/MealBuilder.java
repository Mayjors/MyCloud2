package com.example.algorithm.pattern.builder;

/**
 * 进餐的建造者类
 */
public class MealBuilder {
    // 套餐：表示素食进餐的方法
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }
}
