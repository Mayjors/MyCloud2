package com.example.algorithm.ai;

import java.util.Arrays;

/**
 * 26. 最长递增子序列
 * 给你一个整数数组 nums，找到其中最长严格递增子序列的长度。
 * 
 * 示例:
 * 输入: nums = [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长递增子序列是 [2,3,7,101]，因此长度为 4。
 */
public class Problem26_LongestIncreasingSubsequence {
    
    // 动态规划 O(n^2)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
    
    // 贪心 + 二分查找 O(n log n)
    public int lengthOfLISOptimized(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        
        for (int num : nums) {
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tails[left] = num;
            if (left == size) size++;
        }
        
        return size;
    }
}
