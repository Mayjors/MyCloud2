package com.example.algorithm.ai;

import java.util.*;

/**
 * 149. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为：k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 
 * 示例:
 * 输入: s = "3[a]2[bc]"
 * 输出: "aaabcbc"
 */
public class Problem149_DecodeString {
    
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder current = new StringBuilder();
        int k = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(k);
                stringStack.push(current);
                current = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                StringBuilder decoded = stringStack.pop();
                int count = countStack.pop();
                
                for (int i = 0; i < count; i++) {
                    decoded.append(current);
                }
                current = decoded;
            } else {
                current.append(c);
            }
        }
        
        return current.toString();
    }
    
    // 递归解法
    public String decodeStringRecursive(String s) {
        return decodeHelper(s, new int[]{0});
    }
    
    private String decodeHelper(String s, int[] i) {
        StringBuilder result = new StringBuilder();
        
        while (i[0] < s.length() && s.charAt(i[0]) != ']') {
            char c = s.charAt(i[0]);
            
            if (!Character.isDigit(c)) {
                result.append(c);
                i[0]++;
            } else {
                int k = 0;
                while (i[0] < s.length() && Character.isDigit(s.charAt(i[0]))) {
                    k = k * 10 + (s.charAt(i[0]) - '0');
                    i[0]++;
                }
                
                i[0]++; // skip '['
                String decoded = decodeHelper(s, i);
                i[0]++; // skip ']'
                
                while (k-- > 0) {
                    result.append(decoded);
                }
            }
        }
        
        return result.toString();
    }
}
