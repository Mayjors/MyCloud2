package com.example.algorithm.ai;

/**
 * 186. 地下城游戏
 * 恶魔们抓住了公主并将她关在了地下城 dungeon 的右下角。
 * 地下城是由 m x n 个房间组成的二维网格。
 * 我们英勇的骑士最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 
 * 示例:
 * 输入: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * 输出: 7
 */
public class Problem186_DungeonGame {
    
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            java.util.Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minHP = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = Math.max(1, minHP);
            }
        }
        
        return dp[0][0];
    }
}
