package com.example.algorithm.ai;

/**
 * 158. 分割等和子集
 * 给你一个只包含正整数的非空数组 nums。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 示例:
 * 输入: nums = [1,5,11,5]
 * 输出: true
 */
public class Problem158_PartitionEqualSubsetSum {
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        if (sum % 2 != 0) return false;
        
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        
        return dp[target];
    }
}
