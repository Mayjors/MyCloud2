package com.example.algorithm.ai;

/**
 * 120. 外观数列
 * 给定一个正整数 n，输出外观数列的第 n 项。
 * 外观数列是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 
 * 示例:
 * 输入: n = 4
 * 输出: "1211"
 * 解释:
 * n=1: "1"
 * n=2: "11" (一个 1)
 * n=3: "21" (两个 1)
 * n=4: "1211" (一个 2，一个 1)
 */
public class Problem120_CountAndSay {
    
    public String countAndSay(int n) {
        if (n == 1) return "1";
        
        String prev = countAndSay(n - 1);
        StringBuilder result = new StringBuilder();
        
        int count = 1;
        for (int i = 0; i < prev.length(); i++) {
            if (i + 1 < prev.length() && prev.charAt(i) == prev.charAt(i + 1)) {
                count++;
            } else {
                result.append(count).append(prev.charAt(i));
                count = 1;
            }
        }
        
        return result.toString();
    }
}
