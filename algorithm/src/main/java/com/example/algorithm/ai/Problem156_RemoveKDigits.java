package com.example.algorithm.ai;

/**
 * 156. 移除 K 位数字
 * 给你一个以字符串表示的非负整数 num 和一个整数 k，
 * 移除这个数中的 k 位数字，使得剩下的数字最小。
 * 
 * 示例:
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 */
public class Problem156_RemoveKDigits {
    
    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";
        
        StringBuilder stack = new StringBuilder();
        
        for (char digit : num.toCharArray()) {
            while (k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > digit) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }
            stack.append(digit);
        }
        
        // 如果还有剩余的 k，从末尾删除
        while (k > 0) {
            stack.deleteCharAt(stack.length() - 1);
            k--;
        }
        
        // 删除前导零
        int i = 0;
        while (i < stack.length() && stack.charAt(i) == '0') {
            i++;
        }
        
        String result = stack.substring(i);
        return result.isEmpty() ? "0" : result;
    }
}
