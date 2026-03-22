package com.example.algorithm.ai;

/**
 * 119. 通配符匹配变体
 * 支持 '.' 和 '*' 的简化正则匹配
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的字符
 */
public class Problem119_RegularExpressionMatch {
    
    // 递归解法
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        
        boolean firstMatch = !s.isEmpty() && 
            (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 两种情况：不使用 * 或使用 *
            return isMatch(s, p.substring(2)) || 
                   (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }
    
    // 动态规划解法
    public boolean isMatchDP(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}
