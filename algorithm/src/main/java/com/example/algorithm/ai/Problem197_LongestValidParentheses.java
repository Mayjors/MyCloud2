package com.example.algorithm.ai;

/**
 * 197. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 
 * 示例:
 * 输入: s = ")()())"
 * 输出: 4
 */
public class Problem197_LongestValidParentheses {
    
    // 动态规划
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        
        int[] dp = new int[s.length()];
        int maxLen = 0;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && 
                          s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + 
                           (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        
        return maxLen;
    }
    
    // 栈解法
    public int longestValidParenthesesStack(String s) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(-1);
        int maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        
        return maxLen;
    }
    
    // 双指针
    public int longestValidParenthesesTwoPass(String s) {
        int left = 0, right = 0;
        int maxLen = 0;
        
        // 从左到右
        for (char c : s.toCharArray()) {
            if (c == '(') left++;
            else right++;
            
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        
        // 从右到左
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') right++;
            else left++;
            
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        
        return maxLen;
    }
}
