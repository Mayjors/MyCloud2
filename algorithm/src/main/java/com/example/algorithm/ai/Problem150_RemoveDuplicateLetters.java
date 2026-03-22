package com.example.algorithm.ai;

import java.util.*;

/**
 * 150. 去除重复字母
 * 给你一个字符串 s，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 
 * 示例:
 * 输入: s = "bcabc"
 * 输出: "abc"
 */
public class Problem150_RemoveDuplicateLetters {
    
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (seen[c - 'a']) continue;
            
            while (!stack.isEmpty() && stack.peek() > c && 
                   lastIndex[stack.peek() - 'a'] > i) {
                seen[stack.pop() - 'a'] = false;
            }
            
            stack.push(c);
            seen[c - 'a'] = true;
        }
        
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        
        return result.toString();
    }
}
