package com.example.algorithm.ai;

/**
 * 192. 有效数字
 * 给定一个字符串 s，返回 s 是否是一个有效数字。
 * 
 * 示例:
 * 输入: s = "0"
 * 输出: true
 */
public class Problem192_ValidNumber {
    
    public boolean isNumber(String s) {
        s = s.trim();
        boolean seenDigit = false;
        boolean seenExponent = false;
        boolean seenDot = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '+' || c == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false;
            } else {
                return false;
            }
        }
        
        return seenDigit;
    }
}
