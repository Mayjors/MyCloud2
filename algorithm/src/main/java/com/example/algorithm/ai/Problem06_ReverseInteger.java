package com.example.algorithm.ai;

/**
 * 6. 整数反转
 * 给你一个 32 位的有符号整数 x，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1]，就返回 0。
 * 
 * 示例:
 * 输入: x = 123
 * 输出: 321
 */
public class Problem06_ReverseInteger {
    
    public int reverse(int x) {
        long result = 0;
        
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int) result;
    }
    
    public static void main(String[] args) {
        Problem06_ReverseInteger solution = new Problem06_ReverseInteger();
        System.out.println(solution.reverse(123)); // 321
        System.out.println(solution.reverse(-123)); // -321
        System.out.println(solution.reverse(1534236469)); // 0 (overflow)
    }
}
