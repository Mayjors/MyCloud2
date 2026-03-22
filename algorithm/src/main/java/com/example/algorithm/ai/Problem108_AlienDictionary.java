package com.example.algorithm.ai;

import java.util.*;

/**
 * 108. 外星文字典
 * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
 * 给定一个字符串列表 words，作为这门语言的词典，words 中的字符串已经按这门新语言的字母顺序进行了排序。
 * 请你根据该词典还原出此语言中已知的字母顺序，并按字母递增顺序排列。
 * 
 * 示例:
 * 输入: words = ["wrt","wrf","er","ett","rftt"]
 * 输出: "wertf"
 */
public class Problem108_AlienDictionary {
    
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        
        // 初始化所有字符
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }
        
        // 构建图
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // 检查无效情况：word1 是 word2 的前缀但 word1 更长
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        
        // 拓扑排序
        Queue<Character> queue = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            result.append(c);
            
            for (char next : graph.get(c)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return result.length() == indegree.size() ? result.toString() : "";
    }
}
