package com.example.algorithm.ai;

/**
 * 195. 复原 IP 地址
 * 有效 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），
 * 整数之间用 '.' 分隔。
 * 
 * 示例:
 * 输入: s = "25525511135"
 * 输出: ["255.255.11.135","255.255.111.35"]
 */
public class Problem195_RestoreIPAddresses {
    
    public java.util.List<String> restoreIpAddresses(String s) {
        java.util.List<String> result = new java.util.ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return result;
        
        backtrack(result, new java.util.ArrayList<>(), s, 0);
        return result;
    }
    
    private void backtrack(java.util.List<String> result, 
                          java.util.List<String> current, 
                          String s, int start) {
        if (current.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }
        
        for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
            String segment = s.substring(start, start + len);
            
            if (isValid(segment)) {
                current.add(segment);
                backtrack(result, current, s, start + len);
                current.remove(current.size() - 1);
            }
        }
    }
    
    private boolean isValid(String segment) {
        if (segment.length() > 1 && segment.startsWith("0")) return false;
        int val = Integer.parseInt(segment);
        return val >= 0 && val <= 255;
    }
}
