package com.example.algorithm.ai;

import java.util.*;

/**
 * 121. 字母异位词分组
 * 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词是由重新排列源单词的字母得到的一个新单词。
 * 
 * 示例:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class Problem121_GroupAnagrams {
    
    // 方法 1: 排序
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    // 方法 2: 计数
    public List<List<String>> groupAnagramsCounting(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            StringBuilder key = new StringBuilder();
            for (int c : count) {
                key.append('#').append(c);
            }
            
            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
}
