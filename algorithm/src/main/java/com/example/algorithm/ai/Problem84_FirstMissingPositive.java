package com.example.algorithm.ai;

/**
 * 84. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * 
 * 示例:
 * 输入: nums = [1,2,0]
 * 输出: 3
 */
public class Problem84_FirstMissingPositive {
    
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        // 将每个正整数放到对应的位置上
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        
        // 找到第一个不在正确位置的数
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        
        return n + 1;
    }
}
