package com.example.algorithm.ai;

/**
 * 164. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 
 * 示例:
 * 输入: nums = [2,3,2]
 * 输出: 3
 */
public class Problem164_HouseRobber2 {
    
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        // 分两种情况：不偷第一家 或 不偷最后一家
        return Math.max(robRange(nums, 0, nums.length - 2),
                       robRange(nums, 1, nums.length - 1));
    }
    
    private int robRange(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        
        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}
