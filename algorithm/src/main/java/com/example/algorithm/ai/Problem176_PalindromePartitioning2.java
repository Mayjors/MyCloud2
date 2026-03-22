package com.example.algorithm.ai;

/**
 * 176. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * 返回符合要求的 最少分割次数。
 * 
 * 示例:
 * 输入: s = "aab"
 * 输出: 1
 */
public class Problem176_PalindromePartitioning2 {
    
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[] dp = new int[n];
        
        // 预处理：判断每个子串是否为回文
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    isPalindrome[i][j] = true;
                } else if (len == 2) {
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && 
                                         isPalindrome[i + 1][j - 1];
                }
            }
        }
        
        // 动态规划求最少分割次数
        for (int i = 0; i < n; i++) {
            if (isPalindrome[0][i]) {
                dp[i] = 0;
            } else {
                dp[i] = i; // 最坏情况：每个字符都分割
                for (int j = 1; j <= i; j++) {
                    if (isPalindrome[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        
        return dp[n - 1];
    }
}
