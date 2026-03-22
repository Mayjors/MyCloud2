package com.example.algorithm.ai;

/**
 * 157. 摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
 * 给你一个整数数组 nums，返回 nums 中作为摆动序列的最长子序列的长度。
 * 
 * 示例:
 * 输入: nums = [1,7,4,9,2,5]
 * 输出: 6
 */
public class Problem157_WiggleSubsequence {
    
    // 贪心算法
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        
        int up = 1, down = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        
        return Math.max(up, down);
    }
    
    // 动态规划
    public int wiggleMaxLengthDP(int[] nums) {
        if (nums.length < 2) return nums.length;
        
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        
        up[0] = 1;
        down[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            up[i] = up[i - 1];
            down[i] = down[i - 1];
            
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i], down[i - 1] + 1);
            } else if (nums[i] < nums[i - 1]) {
                down[i] = Math.max(down[i], up[i - 1] + 1);
            }
        }
        
        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }
}
