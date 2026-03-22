package com.example.algorithm.ai;

import java.util.Stack;

/**
 * 47. 每日温度
 * 给定一个整数数组 temperatures，表示每天的温度，返回一个数组 answer，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 
 * 示例:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 */
public class Problem47_DailyTemperatures {
    
    // 单调栈解法
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // 存储索引
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Problem47_DailyTemperatures solution = new Problem47_DailyTemperatures();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = solution.dailyTemperatures(temperatures);
        for (int days : result) {
            System.out.print(days + " ");
        }
        // 输出: 1 1 4 2 1 1 0 0
    }
}
