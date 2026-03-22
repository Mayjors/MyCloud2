package com.example.algorithm.ai;

/**
 * 92. 戳气球
 * 有 n 个气球，编号为 0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1 或 i + 1 超出了数组的边界，
 * 那么就当它是一个数字为 1 的气球。
 * 
 * 示例:
 * 输入: nums = [3,1,5,8]
 * 输出: 167
 */
public class Problem92_BurstBalloons {
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
        
        // dp[i][j] 表示戳破 (i, j) 之间气球的最大硬币数
        int[][] dp = new int[n + 2][n + 2];
        
        for (int len = 1; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], 
                        dp[i][k - 1] + balloons[i - 1] * balloons[k] * balloons[j + 1] + dp[k + 1][j]);
                }
            }
        }
        
        return dp[1][n];
    }
}
