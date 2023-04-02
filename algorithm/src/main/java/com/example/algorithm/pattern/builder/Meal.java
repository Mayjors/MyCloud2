package com.example.algorithm.pattern.builder;

import com.example.algorithm.base.ArrayList;
import com.example.algorithm.base.List;

/**
 * 订餐类：维护用户点的食物条目
 */
public class Meal {
    private List<Item> items = new ArrayList<>();

    // 将用户点的食物条目添加到集合中
    public void addItem(Item item) {
        items.add(item);
    }

    // 获取所有的点餐事物的总价格
    public float getCost() {
        float cost = 0;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    // 编写一个获取用户点餐明细
    public void showItems() {
        for (Item item : items) {
            System.out.println("名字：" + item.name());
            System.out.println("包装：" + item.packing());
            System.out.println("价格：" + item.price());
            System.out.println("--------------------");
        }
    }
}
