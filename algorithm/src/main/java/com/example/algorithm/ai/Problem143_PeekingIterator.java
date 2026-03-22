package com.example.algorithm.ai;

import java.util.*;

/**
 * 143. 顶端迭代器
 * 设计一个迭代器，在迭代接口的基础上支持 peek 操作。
 * peek 操作返回下一个元素但不移动迭代器。
 * 
 * 示例:
 * PeekingIterator iterator = new PeekingIterator([1, 2, 3]);
 * iterator.next();    // 1
 * iterator.peek();    // 2
 * iterator.next();    // 2
 */
public class Problem143_PeekingIterator {
    
    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer nextElement;
        
        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
            if (iterator.hasNext()) {
                nextElement = iterator.next();
            }
        }
        
        public Integer peek() {
            return nextElement;
        }
        
        @Override
        public Integer next() {
            Integer result = nextElement;
            
            if (iterator.hasNext()) {
                nextElement = iterator.next();
            } else {
                nextElement = null;
            }
            
            return result;
        }
        
        @Override
        public boolean hasNext() {
            return nextElement != null;
        }
    }
}
