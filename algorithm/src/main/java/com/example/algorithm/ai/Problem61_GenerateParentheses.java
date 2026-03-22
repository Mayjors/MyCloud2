package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 61. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 * 
 * 示例:
 * 输入: n = 3
 * 输出: ["((()))","(()())","(())()","()(())","()()()"]
 */
public class Problem61_GenerateParentheses {
    
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }
    
    private void backtrack(List<String> result, String current, 
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
}
