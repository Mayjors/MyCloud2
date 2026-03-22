package com.example.algorithm.ai;

/**
 * 162. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
 * 返回你可以获得的最大乘积。
 * 
 * 示例:
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 */
public class Problem162_IntegerBreak {
    
    // 动态规划
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        
        return dp[n];
    }
    
    // 数学方法：尽可能多的 3
    public int integerBreakMath(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        
        int quotient = n / 3;
        int remainder = n % 3;
        
        if (remainder == 0) return (int) Math.pow(3, quotient);
        if (remainder == 1) return (int) Math.pow(3, quotient - 1) * 4;
        return (int) Math.pow(3, quotient) * 2;
    }
}
