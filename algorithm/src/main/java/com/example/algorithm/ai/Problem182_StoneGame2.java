package com.example.algorithm.ai;

/**
 * 182. 石子游戏 II
 * Alice 和 Bob 继续他们的石子游戏。许多堆石子排成一行，每堆都有正整数颗石子 piles[i]。
 * 游戏以谁手中的石子最多来决出胜负。
 * Alice 和 Bob 轮流进行，Alice 先开始。最初，M = 1。
 * 在每个玩家的回合中，该玩家可以拿走剩下的前 X 堆的所有石子，其中 1 <= X <= 2M。
 * 然后，令 M = max(M, X)。
 * 
 * 示例:
 * 输入: piles = [2,7,9,4,4]
 * 输出: 10
 */
public class Problem182_StoneGame2 {
    
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        int[][] dp = new int[n][n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                if (i + 2 * m >= n) {
                    dp[i][m] = suffixSum[i];
                } else {
                    for (int x = 1; x <= 2 * m; x++) {
                        dp[i][m] = Math.max(dp[i][m], 
                            suffixSum[i] - dp[i + x][Math.max(m, x)]);
                    }
                }
            }
        }
        
        return dp[0][1];
    }
}
