package com.example.algorithm.ai;

/**
 * 7. 字符串转换整数 (atoi)
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
 * 
 * 示例:
 * 输入: s = "42"
 * 输出: 42
 */
public class Problem07_StringToInteger {
    
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int i = 0;
        int sign = 1;
        long result = 0;
        
        // 跳过前导空格
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        
        // 处理符号
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '+' ? 1 : -1;
            i++;
        }
        
        // 转换数字
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            
            // 检查溢出
            if (sign * result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        
        return (int) (sign * result);
    }
    
    public static void main(String[] args) {
        Problem07_StringToInteger solution = new Problem07_StringToInteger();
        System.out.println(solution.myAtoi("42")); // 42
        System.out.println(solution.myAtoi("   -42")); // -42
        System.out.println(solution.myAtoi("4193 with words")); // 4193
        System.out.println(solution.myAtoi("-91283472332")); // -2147483648
    }
}
