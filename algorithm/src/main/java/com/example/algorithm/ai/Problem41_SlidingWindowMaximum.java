package com.example.algorithm.ai;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 41. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出: [3,3,5,5,6,7]
 */
public class Problem41_SlidingWindowMaximum {
    
    // 双端队列解法
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // 存储索引
        
        for (int i = 0; i < n; i++) {
            // 移除超出窗口范围的索引
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // 移除所有小于当前元素的索引（从队尾）
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
            
            // 当窗口形成后，记录最大值
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
}
