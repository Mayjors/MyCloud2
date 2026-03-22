package com.example.algorithm.ai;

/**
 * 126. 替换后的最长重复字符
 * 给你一个字符串 s 和一个整数 k。你可以选择字符串中的任一字符，
 * 并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 * 
 * 示例:
 * 输入: s = "ABAB", k = 2
 * 输出: 4
 */
public class Problem126_LongestRepeatingCharacterReplacement {
    
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, maxCount = 0, maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'A']++;
            maxCount = Math.max(maxCount, count[c - 'A']);
            
            // 如果需要替换的字符数超过 k，移动左指针
            while (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
