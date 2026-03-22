package com.example.algorithm.ai;

/**
 * 183. 最小路径代价
 * 给你一个下标从 0 开始的整数矩阵 grid，矩阵大小为 m x n，由从 0 到 m * n - 1 的不同整数组成。
 * 你可以在此矩阵中，从一个单元格移动到下一行的任何其他单元格。
 * 
 * 示例:
 * 输入: grid = [[5,3],[4,0],[2,1]], moveCost = [[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]
 * 输出: 17
 */
public class Problem183_MinimumPathCost {
    
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        
        // 初始化第一行
        for (int j = 0; j < n; j++) {
            dp[0][j] = grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    int cost = dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minCost = Math.min(minCost, dp[m - 1][j]);
        }
        
        return minCost;
    }
}
