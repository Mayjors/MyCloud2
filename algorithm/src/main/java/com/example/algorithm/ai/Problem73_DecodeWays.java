package com.example.algorithm.ai;

/**
 * 73. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了编码：
 * 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26"
 * 要解码已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母。
 * 给你一个只含数字的非空字符串 s，请计算并返回解码方法的总数。
 * 
 * 示例:
 * 输入: s = "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 */
public class Problem73_DecodeWays {
    
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            // 一位数字
            int oneDigit = s.charAt(i - 1) - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }
            
            // 两位数字
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[n];
    }
    
    // 空间优化版本
    public int numDecodingsOptimized(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int prev2 = 1;
        int prev1 = 1;
        
        for (int i = 2; i <= s.length(); i++) {
            int current = 0;
            
            int oneDigit = s.charAt(i - 1) - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                current += prev1;
            }
            
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                current += prev2;
            }
            
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}
