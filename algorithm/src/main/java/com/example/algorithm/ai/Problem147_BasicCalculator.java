package com.example.algorithm.ai;

import java.util.*;

/**
 * 147. 基本计算器
 * 给你一个字符串表达式 s，请你实现一个基本计算器来计算并返回它的值。
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval()。
 * 
 * 示例:
 * 输入: s = "1 + 1"
 * 输出: 2
 */
public class Problem147_BasicCalculator {
    
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;
        int num = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * num;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * num;
                num = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * num;
                num = 0;
                result *= stack.pop(); // sign
                result += stack.pop(); // previous result
            }
        }
        
        result += sign * num;
        return result;
    }
}
