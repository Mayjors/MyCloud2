package com.example.algorithm.ai;

/**
 * 179. 猜数字大小 II
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * 每次你猜错了，我会告诉你，我选的数字比你的更大或者更小，并且你需要继续猜数。
 * 每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。
 * 直到你猜到我选的数字，你才算赢得了这个游戏。
 * 
 * 示例:
 * 输入: n = 10
 * 输出: 16
 */
public class Problem179_GuessNumberHigherOrLower2 {
    
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], 
                        k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
            }
        }
        
        return dp[1][n];
    }
}
