package com.example.algorithm.ai;

import java.util.*;

/**
 * 118. 单词拆分 II
 * 给定一个字符串 s 和一个字符串字典 wordDict，在字符串 s 中增加空格来构建一个句子，
 * 使得句子中所有的单词都在词典中。以任意顺序返回所有这些可能的句子。
 * 
 * 示例:
 * 输入: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出: ["cats and dog","cat sand dog"]
 */
public class Problem118_WordBreak2 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> memo = new HashMap<>();
        return wordBreakHelper(s, new HashSet<>(wordDict), memo);
    }
    
    private List<String> wordBreakHelper(String s, Set<String> wordDict, 
                                         Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        List<String> result = new ArrayList<>();
        
        if (s.isEmpty()) {
            result.add("");
            return result;
        }
        
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> subResult = wordBreakHelper(
                    s.substring(word.length()), wordDict, memo
                );
                
                for (String sub : subResult) {
                    if (sub.isEmpty()) {
                        result.add(word);
                    } else {
                        result.add(word + " " + sub);
                    }
                }
            }
        }
        
        memo.put(s, result);
        return result;
    }
}
