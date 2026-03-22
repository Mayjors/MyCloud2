package com.example.algorithm.ai;

/**
 * 87. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * - '.' 匹配任意单个字符
 * - '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串 s 的，而不是部分字符串。
 * 
 * 示例:
 * 输入: s = "aa", p = "a*"
 * 输出: true
 */
public class Problem87_RegularExpressionMatching {
    
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        // 处理模式以 * 开头的情况
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                char sc = s.charAt(i - 1);
                
                if (pc == '*') {
                    dp[i][j] = dp[i][j - 2]; // 不使用 *
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; // 使用 *
                    }
                } else if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        
        return dp[m][n];
    }
}
