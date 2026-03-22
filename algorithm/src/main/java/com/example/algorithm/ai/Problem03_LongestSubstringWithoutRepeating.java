package com.example.algorithm.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s，请你找出其中不含有重复字符的最长子串的长度。
 * 
 * 示例:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class Problem03_LongestSubstringWithoutRepeating {
    
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        Problem03_LongestSubstringWithoutRepeating solution = new Problem03_LongestSubstringWithoutRepeating();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(solution.lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(solution.lengthOfLongestSubstring("pwwkew")); // 3
    }
}
