package com.example.algorithm.ai;

/**
 * 89. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 
 * 示例:
 * 输入: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出: 2
 */
public class Problem89_UniquePaths2 {
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[0][0] == 1) return 0;
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        // 初始化第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = obstacleGrid[0][j] == 0 ? dp[0][j - 1] : 0;
        }
        
        // 初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 0 ? dp[i - 1][0] : 0;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        
        return dp[m - 1][n - 1];
    }
}
