package com.example.algorithm.ai;

/**
 * 94. 最长公共子串
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子串的长度。
 * 子串要求连续。
 * 
 * 示例:
 * 输入: text1 = "abcdef", text2 = "zcdemf"
 * 输出: 3 (cde)
 */
public class Problem94_LongestCommonSubstring {
    
    public int longestCommonSubstring(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        
        return maxLen;
    }
}
