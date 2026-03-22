package com.example.algorithm.ai;

/**
 * 194. 简化路径
 * 给你一个字符串 path，表示指向某一文件或目录的 Unix 风格绝对路径（以 '/' 开头），
 * 请你将其转化为更加简洁的规范路径。
 * 
 * 示例:
 * 输入: path = "/home//foo/"
 * 输出: "/home/foo"
 */
public class Problem194_SimplifyPath {
    
    public String simplifyPath(String path) {
        java.util.Deque<String> stack = new java.util.ArrayDeque<>();
        String[] parts = path.split("/");
        
        for (String part : parts) {
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (!part.isEmpty() && !part.equals(".")) {
                stack.offerLast(part);
            }
        }
        
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append("/").append(stack.pollFirst());
        }
        
        return result.length() == 0 ? "/" : result.toString();
    }
}
