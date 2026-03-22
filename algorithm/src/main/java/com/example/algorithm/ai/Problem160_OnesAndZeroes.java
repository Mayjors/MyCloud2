package com.example.algorithm.ai;

/**
 * 160. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n。
 * 请你找出并返回 strs 的最大子集的长度，该子集中最多有 m 个 0 和 n 个 1。
 * 
 * 示例:
 * 输入: strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出: 4
 */
public class Problem160_OnesAndZeroes {
    
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        
        for (String str : strs) {
            int zeros = 0, ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }
            
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        
        return dp[m][n];
    }
}
