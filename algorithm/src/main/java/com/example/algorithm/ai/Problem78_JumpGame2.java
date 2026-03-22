package com.example.algorithm.ai;

/**
 * 78. 跳跃游戏 II
 * 给你一个非负整数数组 nums，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 示例:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 */
public class Problem78_JumpGame2 {
    
    // 贪心算法
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        
        return jumps;
    }
    
    // BFS 思路
    public int jumpBFS(int[] nums) {
        int jumps = 0;
        int left = 0, right = 0;
        
        while (right < nums.length - 1) {
            int farthest = 0;
            for (int i = left; i <= right; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            left = right + 1;
            right = farthest;
            jumps++;
        }
        
        return jumps;
    }
}
