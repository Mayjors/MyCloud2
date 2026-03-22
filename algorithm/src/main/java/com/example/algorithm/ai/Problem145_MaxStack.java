package com.example.algorithm.ai;

import java.util.*;

/**
 * 145. 最大栈
 * 设计一个最大栈数据结构，既支持栈操作，又支持查找栈中最大元素。
 * 
 * 示例:
 * MaxStack stk = new MaxStack();
 * stk.push(5);
 * stk.push(1);
 * stk.push(5);
 * stk.top();     // 5
 * stk.popMax();  // 5
 * stk.top();     // 1
 */
public class Problem145_MaxStack {
    
    class MaxStack {
        private Stack<Integer> stack;
        private Stack<Integer> maxStack;
        
        public MaxStack() {
            stack = new Stack<>();
            maxStack = new Stack<>();
        }
        
        public void push(int x) {
            stack.push(x);
            if (maxStack.isEmpty() || x >= maxStack.peek()) {
                maxStack.push(x);
            }
        }
        
        public int pop() {
            int val = stack.pop();
            if (val == maxStack.peek()) {
                maxStack.pop();
            }
            return val;
        }
        
        public int top() {
            return stack.peek();
        }
        
        public int peekMax() {
            return maxStack.peek();
        }
        
        public int popMax() {
            int max = maxStack.peek();
            Stack<Integer> buffer = new Stack<>();
            
            while (stack.peek() != max) {
                buffer.push(pop());
            }
            pop();
            
            while (!buffer.isEmpty()) {
                push(buffer.pop());
            }
            
            return max;
        }
    }
}
