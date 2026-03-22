package com.example.algorithm.ai;

import java.util.*;

/**
 * 146. 逆波兰表达式求值
 * 给你一个字符串数组 tokens，表示一个根据逆波兰表示法表示的算术表达式。
 * 请你计算该表达式，返回一个表示表达式值的整数。
 * 
 * 示例:
 * 输入: tokens = ["2","1","+","3","*"]
 * 输出: 9
 * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 */
public class Problem146_EvaluateReversePolishNotation {
    
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int d = stack.pop();
                    int c = stack.pop();
                    stack.push(c / d);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
}
