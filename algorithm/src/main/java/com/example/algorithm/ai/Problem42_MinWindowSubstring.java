package com.example.algorithm.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * 42. 最小覆盖子串
 * 给你一个字符串 s、一个字符串 t。返回 s 中涵盖 t 所有字符的最小子串。
 * 
 * 示例:
 * 输入: s = "ADOBECODEBANC", t = "ABC"
 * 输出: "BANC"
 */
public class Problem42_MinWindowSubstring {
    
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            
            while (valid == need.size()) {
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                
                char d = s.charAt(left);
                left++;
                
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
