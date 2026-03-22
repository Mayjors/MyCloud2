package com.example.algorithm.ai;

/**
 * 161. 组合总和 IV
 * 给你一个由不同整数组成的数组 nums，和一个目标整数 target。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 
 * 示例:
 * 输入: nums = [1,2,3], target = 4
 * 输出: 7
 */
public class Problem161_CombinationSum4 {
    
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        
        return dp[target];
    }
}
