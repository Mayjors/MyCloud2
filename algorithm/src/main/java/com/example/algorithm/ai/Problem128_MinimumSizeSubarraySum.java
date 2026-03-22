package com.example.algorithm.ai;

import java.util.Arrays;

/**
 * 128. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target。
 * 找出该数组中满足其和 ≥ target 的长度最小的连续子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 * 
 * 示例:
 * 输入: target = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 */
public class Problem128_MinimumSizeSubarraySum {
    
    // 滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minLen = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    // 二分查找 + 前缀和 O(n log n)
    public int minSubArrayLenBinarySearch(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int toFind = target + prefixSum[i];
            int index = Arrays.binarySearch(prefixSum, toFind);
            
            if (index < 0) {
                index = -(index + 1);
            }
            
            if (index <= n) {
                minLen = Math.min(minLen, index - i);
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
