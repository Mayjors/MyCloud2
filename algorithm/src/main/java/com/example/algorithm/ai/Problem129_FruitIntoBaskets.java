package com.example.algorithm.ai;

import java.util.*;

/**
 * 129. 水果成篮
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，
 * 其中 fruits[i] 是第 i 棵树上的水果种类。
 * 你想要尽可能多地收集水果。你只有两个篮子，并且每个篮子只能装单一类型的水果。
 * 你可以选择任意一棵树开始采摘，你必须从每棵树上恰好摘一个水果。
 * 返回你可以收集的水果的最大数目。
 * 
 * 示例:
 * 输入: fruits = [1,2,1]
 * 输出: 3
 */
public class Problem129_FruitIntoBaskets {
    
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);
            
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }
            
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
}
