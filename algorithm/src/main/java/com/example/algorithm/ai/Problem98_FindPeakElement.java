package com.example.algorithm.ai;

/**
 * 98. 寻找峰值
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 
 * 示例:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 */
public class Problem98_FindPeakElement {
    
    // 二分查找
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                // 峰值在左侧（包括 mid）
                right = mid;
            } else {
                // 峰值在右侧
                left = mid + 1;
            }
        }
        
        return left;
    }
}
