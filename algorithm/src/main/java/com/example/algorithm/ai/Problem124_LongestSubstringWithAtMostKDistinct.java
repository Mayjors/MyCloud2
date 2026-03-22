package com.example.algorithm.ai;

import java.util.*;

/**
 * 124. 至多包含 K 个不同字符的最长子串
 * 给定一个字符串 s，找出至多包含 k 个不同字符的最长子串。
 * 
 * 示例:
 * 输入: s = "eceba", k = 2
 * 输出: 3 ("ece")
 */
public class Problem124_LongestSubstringWithAtMostKDistinct {
    
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k == 0) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
