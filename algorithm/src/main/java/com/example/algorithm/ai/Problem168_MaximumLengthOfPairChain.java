package com.example.algorithm.ai;

/**
 * 168. 最长数对链
 * 给出 n 个数对。在每一个数对中，第一个数字总是比第二个数字小。
 * 定义一种跟随关系：当且仅当 b < c 时，数对 (c, d) 才可以跟在 (a, b) 后面。
 * 找出能够形成的最长数对链的长度。
 * 
 * 示例:
 * 输入: [[1,2], [2,3], [3,4]]
 * 输出: 2
 */
public class Problem168_MaximumLengthOfPairChain {
    
    // 贪心算法
    public int findLongestChain(int[][] pairs) {
        java.util.Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        
        int count = 1;
        int currentEnd = pairs[0][1];
        
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > currentEnd) {
                count++;
                currentEnd = pairs[i][1];
            }
        }
        
        return count;
    }
    
    // 动态规划
    public int findLongestChainDP(int[][] pairs) {
        java.util.Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        
        int[] dp = new int[pairs.length];
        java.util.Arrays.fill(dp, 1);
        
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int maxLen = 0;
        for (int len : dp) {
            maxLen = Math.max(maxLen, len);
        }
        
        return maxLen;
    }
}
