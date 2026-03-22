package com.example.algorithm.ai;

import java.util.*;

/**
 * 104. 重构字符串
 * 给定一个字符串 s，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 返回 s 的任意可能的重新排列。如不可行，返回空字符串 ""。
 * 
 * 示例:
 * 输入: s = "aab"
 * 输出: "aba"
 */
public class Problem104_ReorganizeString {
    
    public String reorganizeString(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
            (a, b) -> count.get(b) - count.get(a)
        );
        maxHeap.addAll(count.keySet());
        
        if (count.get(maxHeap.peek()) > (s.length() + 1) / 2) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        while (maxHeap.size() >= 2) {
            char c1 = maxHeap.poll();
            char c2 = maxHeap.poll();
            
            sb.append(c1).append(c2);
            
            count.put(c1, count.get(c1) - 1);
            count.put(c2, count.get(c2) - 1);
            
            if (count.get(c1) > 0) maxHeap.offer(c1);
            if (count.get(c2) > 0) maxHeap.offer(c2);
        }
        
        if (!maxHeap.isEmpty()) {
            sb.append(maxHeap.poll());
        }
        
        return sb.toString();
    }
}
