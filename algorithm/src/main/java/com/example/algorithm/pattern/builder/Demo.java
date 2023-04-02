package com.example.algorithm.pattern.builder;

public class Demo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal meal = mealBuilder.prepareVegMeal();
        meal.showItems();
    }
}
