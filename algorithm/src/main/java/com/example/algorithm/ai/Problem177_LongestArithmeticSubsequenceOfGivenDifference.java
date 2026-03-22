package com.example.algorithm.ai;

/**
 * 177. 最长定差子序列
 * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，
 * 该子序列中相邻元素之间的差等于 difference。
 * 
 * 示例:
 * 输入: arr = [1,2,3,4], difference = 1
 * 输出: 4
 */
public class Problem177_LongestArithmeticSubsequenceOfGivenDifference {
    
    public int longestSubsequence(int[] arr, int difference) {
        java.util.Map<Integer, Integer> dp = new java.util.HashMap<>();
        int maxLen = 0;
        
        for (int num : arr) {
            dp.put(num, dp.getOrDefault(num - difference, 0) + 1);
            maxLen = Math.max(maxLen, dp.get(num));
        }
        
        return maxLen;
    }
}
