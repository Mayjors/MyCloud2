package com.example.algorithm.ai;

/**
 * 167. 最长公共子数组
 * 给两个整数数组 nums1 和 nums2，返回两个数组中公共的、长度最长的子数组的长度。
 * 
 * 示例:
 * 输入: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出: 3
 */
public class Problem167_MaxLengthOfRepeatedSubarray {
    
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        
        return maxLen;
    }
}
