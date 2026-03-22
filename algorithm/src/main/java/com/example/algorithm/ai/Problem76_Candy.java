package com.example.algorithm.ai;

/**
 * 76. 分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * - 每个孩子至少分配到 1 个糖果。
 * - 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你准备并返回需要准备的最少糖果数目。
 * 
 * 示例:
 * 输入: ratings = [1,0,2]
 * 输出: 5
 */
public class Problem76_Candy {
    
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        
        // 每个孩子至少 1 个糖果
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }
        
        // 从左到右遍历
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        
        // 从右到左遍历
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        
        // 计算总数
        int total = 0;
        for (int candy : candies) {
            total += candy;
        }
        
        return total;
    }
}
