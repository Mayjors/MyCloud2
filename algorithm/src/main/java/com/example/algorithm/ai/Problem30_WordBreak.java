package com.example.algorithm.ai;

import java.util.*;

/**
 * 30. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。
 * 如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 
 * 示例:
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 */
public class Problem30_WordBreak {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
    
    public static void main(String[] args) {
        Problem30_WordBreak solution = new Problem30_WordBreak();
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(solution.wordBreak("leetcode", wordDict)); // true
        System.out.println(solution.wordBreak("applepenapple", Arrays.asList("apple", "pen"))); // true
        System.out.println(solution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"))); // false
    }
}
