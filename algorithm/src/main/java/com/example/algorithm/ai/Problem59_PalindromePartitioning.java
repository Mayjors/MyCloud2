package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 59. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 
 * 示例:
 * 输入: s = "aab"
 * 输出: [["a","a","b"],["aa","b"]]
 */
public class Problem59_PalindromePartitioning {
    
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }
    
    private void backtrack(List<List<String>> result, List<String> current, 
                          String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                current.add(s.substring(start, end + 1));
                backtrack(result, current, s, end + 1);
                current.remove(current.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
