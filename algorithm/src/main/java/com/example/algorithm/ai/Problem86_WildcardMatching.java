package com.example.algorithm.ai;

/**
 * 86. 通配符匹配
 * 给你一个输入字符串 (s) 和一个字符模式 (p)，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配。
 * - '?' 可以匹配任何单个字符。
 * - '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够完全匹配输入字符串。
 * 
 * 示例:
 * 输入: s = "adceb", p = "*a*b"
 * 输出: true
 */
public class Problem86_WildcardMatching {
    
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        // 处理模式以 * 开头的情况
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                char sc = s.charAt(i - 1);
                
                if (pc == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (pc == '?' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        
        return dp[m][n];
    }
    
    // 贪心算法（更优）
    public boolean isMatchGreedy(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;
        
        while (sIdx < sLen) {
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            } else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                sTmpIdx = sIdx;
                pIdx++;
            } else if (starIdx == -1) {
                return false;
            } else {
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }
        
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }
        
        return pIdx == pLen;
    }
}
