package com.example.algorithm.ai;

/**
 * 72. 不同的子序列
 * 给定一个字符串 s 和一个字符串 t，计算在 s 的子序列中 t 出现的个数。
 * 
 * 示例:
 * 输入: s = "rabbbit", t = "rabbit"
 * 输出: 3
 */
public class Problem72_DistinctSubsequences {
    
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        if (n > m) return 0;
        
        // dp[i][j] 表示 s 的前 i 个字符中包含 t 的前 j 个字符的子序列个数
        int[][] dp = new int[m + 1][n + 1];
        
        // 初始化：空字符串是任何字符串的子序列
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j]; // 不使用 s[i-1]
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1]; // 使用 s[i-1]
                }
            }
        }
        
        return dp[m][n];
    }
    
    // 空间优化版本
    public int numDistinctOptimized(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}
