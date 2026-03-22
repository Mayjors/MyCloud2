package com.example.algorithm.ai;

import java.util.*;

/**
 * 127. 字符串的排列
 * 给你两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 如果是，返回 true；否则，返回 false。
 * 
 * 示例:
 * 输入: s1 = "ab", s2 = "eidbaooo"
 * 输出: true
 */
public class Problem127_PermutationInString {
    
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }
        
        if (Arrays.equals(s1Count, s2Count)) return true;
        
        for (int i = s1.length(); i < s2.length(); i++) {
            s2Count[s2.charAt(i) - 'a']++;
            s2Count[s2.charAt(i - s1.length()) - 'a']--;
            
            if (Arrays.equals(s1Count, s2Count)) return true;
        }
        
        return false;
    }
}
