package com.example.algorithm.ai;

import java.util.*;

/**
 * 123. 最小覆盖子串（优化版）
 * 使用数组代替 HashMap 优化性能
 */
public class Problem123_MinimumWindowSubstring {
    
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        
        int count = t.length();
        int left = 0, right = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        
        while (right < s.length()) {
            char c = s.charAt(right);
            if (need[c] > 0) {
                count--;
            }
            need[c]--;
            right++;
            
            while (count == 0) {
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                
                char d = s.charAt(left);
                need[d]++;
                if (need[d] > 0) {
                    count++;
                }
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
