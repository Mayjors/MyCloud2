package com.example.algorithm.ai;

import java.util.*;

/**
 * 109. 单词接龙
 * 字典 wordList 中从单词 beginWord 到 endWord 的转换序列是一个按下述规格形成的序列：
 * - 序列中第一个单词是 beginWord
 * - 序列中最后一个单词是 endWord
 * - 每次转换只能改变一个字母
 * - 转换过程中的中间单词必须是字典 wordList 中的单词
 * 
 * 示例:
 * 输入: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 */
public class Problem109_WordLadder {
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordSet.remove(beginWord);
        
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                
                if (word.equals(endWord)) {
                    return level;
                }
                
                // 尝试改变每个字符
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        
                        chars[j] = c;
                        String newWord = new String(chars);
                        
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                    
                    chars[j] = original;
                }
            }
            
            level++;
        }
        
        return 0;
    }
}
