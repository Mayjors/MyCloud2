package com.example.algorithm.ai;

import java.util.*;

/**
 * 140. 组合迭代器
 * 设计一个迭代器类 CombinationIterator，支持以下操作：
 * - next(): 返回下一个组合
 * - hasNext(): 判断是否还有下一个组合
 * 
 * 示例:
 * CombinationIterator itr = new CombinationIterator("abc", 2);
 * itr.next();    // "ab"
 * itr.hasNext(); // true
 * itr.next();    // "ac"
 */
public class Problem140_IteratorForCombination {
    
    class CombinationIterator {
        private Queue<String> queue;
        
        public CombinationIterator(String characters, int combinationLength) {
            queue = new LinkedList<>();
            backtrack(characters, combinationLength, 0, new StringBuilder());
        }
        
        private void backtrack(String chars, int k, int start, StringBuilder current) {
            if (current.length() == k) {
                queue.offer(current.toString());
                return;
            }
            
            for (int i = start; i < chars.length(); i++) {
                current.append(chars.charAt(i));
                backtrack(chars, k, i + 1, current);
                current.deleteCharAt(current.length() - 1);
            }
        }
        
        public String next() {
            return queue.poll();
        }
        
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}
