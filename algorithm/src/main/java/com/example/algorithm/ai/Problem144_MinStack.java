package com.example.algorithm.ai;

import java.util.*;

/**
 * 144. 最小栈
 * 设计一个支持 push、pop、top 操作，并能在常数时间内检索到最小元素的栈。
 * 
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   // -3
 * minStack.pop();
 * minStack.top();      // 0
 * minStack.getMin();   // -2
 */
public class Problem144_MinStack {
    
    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;
        
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }
        
        public void pop() {
            int val = stack.pop();
            if (val == minStack.peek()) {
                minStack.pop();
            }
        }
        
        public int top() {
            return stack.peek();
        }
        
        public int getMin() {
            return minStack.peek();
        }
    }
    
    // 优化版本：单个栈
    class MinStackOptimized {
        private Stack<Long> stack;
        private long minVal;
        
        public MinStackOptimized() {
            stack = new Stack<>();
            minVal = 0;
        }
        
        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(0L);
                minVal = val;
            } else {
                long diff = (long) val - minVal;
                stack.push(diff);
                if (diff < 0) {
                    minVal = val;
                }
            }
        }
        
        public void pop() {
            long diff = stack.pop();
            if (diff < 0) {
                minVal = minVal - diff;
            }
        }
        
        public int top() {
            long diff = stack.peek();
            if (diff >= 0) {
                return (int) (minVal + diff);
            } else {
                return (int) minVal;
            }
        }
        
        public int getMin() {
            return (int) minVal;
        }
    }
}
