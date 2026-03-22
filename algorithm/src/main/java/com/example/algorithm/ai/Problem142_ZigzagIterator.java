package com.example.algorithm.ai;

import java.util.*;

/**
 * 142. 锯齿迭代器
 * 给出两个整型向量 v1 和 v2，实现一个迭代器，交替地返回 v1 和 v2 的元素。
 * 
 * 示例:
 * 输入: v1 = [1,2], v2 = [3,4,5,6]
 * 输出: [1,3,2,4,5,6]
 */
public class Problem142_ZigzagIterator {
    
    class ZigzagIterator {
        private Queue<Iterator<Integer>> queue;
        
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            queue = new LinkedList<>();
            if (!v1.isEmpty()) queue.offer(v1.iterator());
            if (!v2.isEmpty()) queue.offer(v2.iterator());
        }
        
        public int next() {
            Iterator<Integer> iter = queue.poll();
            int result = iter.next();
            
            if (iter.hasNext()) {
                queue.offer(iter);
            }
            
            return result;
        }
        
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
    
    // 扩展：支持 k 个向量的锯齿迭代器
    class ZigzagIteratorK {
        private Queue<Iterator<Integer>> queue;
        
        public ZigzagIteratorK(List<List<Integer>> lists) {
            queue = new LinkedList<>();
            for (List<Integer> list : lists) {
                if (!list.isEmpty()) {
                    queue.offer(list.iterator());
                }
            }
        }
        
        public int next() {
            Iterator<Integer> iter = queue.poll();
            int result = iter.next();
            
            if (iter.hasNext()) {
                queue.offer(iter);
            }
            
            return result;
        }
        
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}
