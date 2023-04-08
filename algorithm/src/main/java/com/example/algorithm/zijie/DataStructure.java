package com.example.algorithm.zijie;

import java.util.Stack;

public class DataStructure {
    private Stack<Integer> stack;
    private Stack<Integer> min_stack;

    public DataStructure() {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (min_stack.isEmpty() || val <= min_stack.peek()) {
            min_stack.push(val);
        }
    }

    public void pop() {
        if (stack.pop().equals(min_stack.peek())) {
            min_stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}
