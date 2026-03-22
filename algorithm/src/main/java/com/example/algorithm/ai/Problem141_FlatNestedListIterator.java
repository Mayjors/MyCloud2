package com.example.algorithm.ai;

import java.util.*;

/**
 * 141. 扁平化嵌套列表迭代器
 * 给你一个嵌套的整数列表 nestedList。每个元素要么是一个整数，
 * 要么是一个列表；该列表的元素也可能是整数或者是其他列表。
 * 实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
 * 
 * 示例:
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 */
public class Problem141_FlatNestedListIterator {
    
    interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }
    
    class NestedIterator implements Iterator<Integer> {
        private Stack<NestedInteger> stack;
        
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            // 逆序压入栈
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }
        
        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }
        
        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger top = stack.peek();
                
                if (top.isInteger()) {
                    return true;
                }
                
                stack.pop();
                List<NestedInteger> list = top.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }
            
            return false;
        }
    }
}
