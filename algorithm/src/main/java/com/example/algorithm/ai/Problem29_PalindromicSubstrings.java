package com.example.algorithm.ai;

/**
 * 29. 回文子串
 * 给你一个字符串 s，请你统计并返回这个字符串中回文子串的数目。
 * 
 * 示例:
 * 输入: s = "abc"
 * 输出: 3
 * 解释: 三个回文子串："a", "b", "c"
 */
public class Problem29_PalindromicSubstrings {
    
    // 中心扩展法
    public int countSubstrings(String s) {
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // 奇数长度回文
            count += expandAroundCenter(s, i, i);
            // 偶数长度回文
            count += expandAroundCenter(s, i, i + 1);
        }
        
        return count;
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }
    
    // 动态规划
    public int countSubstringsDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = true;
                } else if (len == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j]) count++;
            }
        }
        
        return count;
    }
}
