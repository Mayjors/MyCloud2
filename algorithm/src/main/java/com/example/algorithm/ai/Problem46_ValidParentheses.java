package com.example.algorithm.ai;

import java.util.Stack;

/**
 * 46. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s，判断字符串是否有效。
 * 
 * 示例:
 * 输入: s = "()[]{}"
 * 输出: true
 */
public class Problem46_ValidParentheses {
    
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) return false;
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        Problem46_ValidParentheses solution = new Problem46_ValidParentheses();
        System.out.println(solution.isValid("()")); // true
        System.out.println(solution.isValid("()[]{}")); // true
        System.out.println(solution.isValid("(]")); // false
        System.out.println(solution.isValid("([)]")); // false
        System.out.println(solution.isValid("{[]}")); // true
    }
}
