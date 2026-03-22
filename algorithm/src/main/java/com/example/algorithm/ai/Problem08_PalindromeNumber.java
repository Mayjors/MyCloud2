package com.example.algorithm.ai;

/**
 * 8. 回文数
 * 给你一个整数 x，如果 x 是一个回文整数，返回 true；否则，返回 false。
 * 
 * 示例:
 * 输入: x = 121
 * 输出: true
 */
public class Problem08_PalindromeNumber {
    
    public boolean isPalindrome(int x) {
        // 负数和末尾为0的正数不是回文数
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int reversed = 0;
        int original = x;
        
        while (x > 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        
        return original == reversed;
    }
    
    // 优化版本：只反转一半
    public boolean isPalindromeOptimized(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        
        return x == reversed || x == reversed / 10;
    }
    
    public static void main(String[] args) {
        Problem08_PalindromeNumber solution = new Problem08_PalindromeNumber();
        System.out.println(solution.isPalindrome(121)); // true
        System.out.println(solution.isPalindrome(-121)); // false
        System.out.println(solution.isPalindrome(10)); // false
    }
}
