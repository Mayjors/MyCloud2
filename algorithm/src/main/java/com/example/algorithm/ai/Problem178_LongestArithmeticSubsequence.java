package com.example.algorithm.ai;

/**
 * 178. 最长等差数列
 * 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
 * 
 * 示例:
 * 输入: nums = [3,6,9,12]
 * 输出: 4
 */
public class Problem178_LongestArithmeticSubsequence {
    
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][20001];
        int maxLen = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j] + 10000;
                dp[i][diff] = dp[j][diff] + 1;
                maxLen = Math.max(maxLen, dp[i][diff]);
            }
        }
        
        return maxLen + 1;
    }
}
