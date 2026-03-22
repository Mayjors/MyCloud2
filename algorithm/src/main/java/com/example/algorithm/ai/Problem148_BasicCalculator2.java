package com.example.algorithm.ai;

import java.util.*;

/**
 * 148. 基本计算器 II
 * 给你一个字符串表达式 s，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 
 * 示例:
 * 输入: s = "3+2*2"
 * 输出: 7
 */
public class Problem148_BasicCalculator2 {
    
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                
                sign = c;
                num = 0;
            }
        }
        
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    
    // 空间优化版本
    public int calculateOptimized(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int lastNum = 0;
        int result = 0;
        char sign = '+';
        int num = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (sign == '+') {
                    result += lastNum;
                    lastNum = num;
                } else if (sign == '-') {
                    result += lastNum;
                    lastNum = -num;
                } else if (sign == '*') {
                    lastNum *= num;
                } else if (sign == '/') {
                    lastNum /= num;
                }
                
                sign = c;
                num = 0;
            }
        }
        
        return result + lastNum;
    }
}
