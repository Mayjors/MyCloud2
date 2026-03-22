package com.example.algorithm.ai;

import java.util.*;

/**
 * 117. 完全平方数
 * 给你一个整数 n，返回和为 n 的完全平方数的最少数量。
 * 完全平方数是一个整数，其值等于另一个整数的平方。
 * 
 * 示例:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4
 */
public class Problem117_PerfectSquares {
    
    // 方法 1: 动态规划
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        return dp[n];
    }
    
    // 方法 2: BFS
    public int numSquaresBFS(int n) {
        Set<Integer> squares = new HashSet<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        
        Set<Integer> visited = new HashSet<>();
        visited.add(n);
        
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                if (squares.contains(curr)) {
                    return level;
                }
                
                for (int square : squares) {
                    int next = curr - square;
                    if (next > 0 && !visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
        }
        
        return level;
    }
}
