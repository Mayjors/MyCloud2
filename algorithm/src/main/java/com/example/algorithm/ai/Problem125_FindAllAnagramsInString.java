package com.example.algorithm.ai;

import java.util.*;

/**
 * 125. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的异位词的子串，返回这些子串的起始索引。
 * 
 * 示例:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 */
public class Problem125_FindAllAnagramsInString {
    
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }
        
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        int left = 0, right = 0;
        
        while (right < s.length()) {
            sCount[s.charAt(right) - 'a']++;
            
            if (right - left + 1 == p.length()) {
                if (Arrays.equals(pCount, sCount)) {
                    result.add(left);
                }
                sCount[s.charAt(left) - 'a']--;
                left++;
            }
            
            right++;
        }
        
        return result;
    }
}
