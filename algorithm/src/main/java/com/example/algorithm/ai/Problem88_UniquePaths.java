package com.example.algorithm.ai;

/**
 * 88. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角。
 * 问总共有多少条不同的路径？
 * 
 * 示例:
 * 输入: m = 3, n = 7
 * 输出: 28
 */
public class Problem88_UniquePaths {
    
    // 动态规划
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        // 初始化第一行和第一列
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }
    
    // 空间优化
    public int uniquePathsOptimized(int m, int n) {
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) dp[j] = 1;
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
    
    // 数学方法（组合数）
    public int uniquePathsMath(int m, int n) {
        long result = 1;
        for (int i = 1; i < m; i++) {
            result = result * (n + i - 1) / i;
        }
        return (int) result;
    }
}
