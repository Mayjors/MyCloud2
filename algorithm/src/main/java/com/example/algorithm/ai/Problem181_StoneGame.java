package com.example.algorithm.ai;

/**
 * 181. 石子游戏
 * Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行。
 * 每堆都有正整数颗石子，数目为 piles[i]。
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * Alice 和 Bob 轮流进行，Alice 先开始。每回合，玩家从行的开始或结束处取走整堆石头。
 * 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true，当 Bob 赢得比赛时返回 false。
 * 
 * 示例:
 * 输入: piles = [5,3,4,5]
 * 输出: true
 */
public class Problem181_StoneGame {
    
    // 数学解法：Alice 总是赢
    public boolean stoneGame(int[] piles) {
        return true;
    }
    
    // 动态规划解法
    public boolean stoneGameDP(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], 
                                    piles[j] - dp[i][j - 1]);
            }
        }
        
        return dp[0][n - 1] > 0;
    }
}
