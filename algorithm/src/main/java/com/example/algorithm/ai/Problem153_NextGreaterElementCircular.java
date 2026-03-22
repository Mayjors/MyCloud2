package com.example.algorithm.ai;

/**
 * 153. 每日温度 II（循环数组）
 * 给定一个循环数组，输出每个元素的下一个更大元素。
 * 
 * 示例:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 */
public class Problem153_NextGreaterElementCircular {
    
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        java.util.Arrays.fill(result, -1);
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                result[stack.pop()] = num;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        
        return result;
    }
}
