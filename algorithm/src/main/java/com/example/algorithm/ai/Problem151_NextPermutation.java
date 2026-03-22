package com.example.algorithm.ai;

import java.util.*;

/**
 * 151. 下一个排列
 * 整数数组的一个排列就是将其所有成员以序列或线性顺序排列。
 * 下一个排列是指字典序中下一个更大的排列。
 * 
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出: [1,3,2]
 */
public class Problem151_NextPermutation {
    
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        
        // 1. 从右往左找第一个 nums[i] < nums[i+1]
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            // 2. 从右往左找第一个大于 nums[i] 的数
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        
        // 3. 反转 i+1 到末尾
        reverse(nums, i + 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
