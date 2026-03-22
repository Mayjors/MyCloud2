package com.example.algorithm.ai;

/**
 * 188. 优美的排列
 * 假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），
 * 只要满足下述条件之一，该数组就是一个优美的排列：
 * - perm[i] 能够被 i 整除
 * - i 能够被 perm[i] 整除
 * 给你一个整数 n，返回可以构造的优美排列的数量。
 * 
 * 示例:
 * 输入: n = 2
 * 输出: 2
 */
public class Problem188_BeautifulArrangement {
    
    private int count = 0;
    
    public int countArrangement(int n) {
        count = 0;
        boolean[] used = new boolean[n + 1];
        backtrack(n, 1, used);
        return count;
    }
    
    private void backtrack(int n, int pos, boolean[] used) {
        if (pos > n) {
            count++;
            return;
        }
        
        for (int i = 1; i <= n; i++) {
            if (!used[i] && (i % pos == 0 || pos % i == 0)) {
                used[i] = true;
                backtrack(n, pos + 1, used);
                used[i] = false;
            }
        }
    }
    
    // 动态规划 + 状态压缩
    public int countArrangementDP(int n) {
        int[] dp = new int[1 << n];
        dp[0] = 1;
        
        for (int mask = 0; mask < (1 << n); mask++) {
            int pos = Integer.bitCount(mask) + 1;
            
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    int num = i + 1;
                    if (num % pos == 0 || pos % num == 0) {
                        dp[mask | (1 << i)] += dp[mask];
                    }
                }
            }
        }
        
        return dp[(1 << n) - 1];
    }
}
