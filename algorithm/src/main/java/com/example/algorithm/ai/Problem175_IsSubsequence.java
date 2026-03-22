package com.example.algorithm.ai;

/**
 * 175. 判断子序列
 * 给定字符串 s 和 t，判断 s 是否为 t 的子序列。
 * 
 * 示例:
 * 输入: s = "abc", t = "ahbgdc"
 * 输出: true
 */
public class Problem175_IsSubsequence {
    
    // 双指针
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        
        return i == s.length();
    }
    
    // 进阶：大量 s 需要查询
    public boolean isSubsequenceOptimized(String s, String t) {
        int[][] next = new int[t.length() + 1][26];
        
        // 预处理 t，记录每个位置后每个字符第一次出现的位置
        for (int i = t.length() - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                next[i][j] = next[i + 1][j];
            }
            next[i][t.charAt(i) - 'a'] = i + 1;
        }
        
        int pos = 0;
        for (char c : s.toCharArray()) {
            pos = next[pos][c - 'a'];
            if (pos == 0) return false;
        }
        
        return true;
    }
}
