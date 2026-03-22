package com.example.algorithm.ai;

/**
 * 163. 完全平方数（最少数量）
 * 同 Problem117，这里提供另一种解法
 */
public class Problem163_PerfectSquares2 {
    
    // 四平方和定理
    public int numSquares(int n) {
        // 如果 n 是完全平方数
        if (isPerfectSquare(n)) return 1;
        
        // 如果 n 满足 4^k(8m+7) 形式，返回 4
        int temp = n;
        while (temp % 4 == 0) {
            temp /= 4;
        }
        if (temp % 8 == 7) return 4;
        
        // 检查是否可以表示为两个平方数之和
        for (int i = 1; i * i <= n; i++) {
            if (isPerfectSquare(n - i * i)) {
                return 2;
            }
        }
        
        // 否则返回 3
        return 3;
    }
    
    private boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}
