package com.example.algorithm.ai;

/**
 * 40. 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回数组 answer，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 
 * 示例:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 */
public class Problem40_ProductOfArrayExceptSelf {
    
    // 方法 1: 左右乘积列表
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // 左侧乘积
        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        
        // 右侧乘积
        int[] right = new int[n];
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        
        // 结果
        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }
        
        return result;
    }
    
    // 方法 2: 空间优化
    public int[] productExceptSelfOptimized(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // 先计算左侧乘积
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // 再乘以右侧乘积
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }
        
        return result;
    }
}
