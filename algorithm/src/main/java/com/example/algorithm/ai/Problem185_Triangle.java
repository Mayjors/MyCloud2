package com.example.algorithm.ai;

/**
 * 185. 三角形最小路径和
 * 给定一个三角形 triangle，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。
 * 
 * 示例:
 * 输入: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出: 11
 */
public class Problem185_Triangle {
    
    public int minimumTotal(java.util.List<java.util.List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        
        // 从底向上
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        
        return dp[0];
    }
}
