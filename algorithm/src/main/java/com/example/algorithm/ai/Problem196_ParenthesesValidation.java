package com.example.algorithm.ai;

/**
 * 196. 括号生成 II（带校验）
 * 扩展版本，支持校验括号序列有效性
 */
public class Problem196_ParenthesesValidation {
    
    // 生成所有有效括号组合
    public java.util.List<String> generateParenthesis(int n) {
        java.util.List<String> result = new java.util.ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }
    
    private void backtrack(java.util.List<String> result, String current,
                          int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }
        
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
    
    // 验证括号序列是否有效
    public boolean isValid(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) return false;
        }
        return balance == 0;
    }
    
    // 使括号有效的最少添加
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int closeNeeded = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                closeNeeded++;
            } else {
                if (closeNeeded > 0) {
                    closeNeeded--;
                } else {
                    openNeeded++;
                }
            }
        }
        
        return openNeeded + closeNeeded;
    }
}
