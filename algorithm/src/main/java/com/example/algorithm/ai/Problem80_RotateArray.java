package com.example.algorithm.ai;

/**
 * 80. 轮转数组
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 
 * 示例:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 */
public class Problem80_RotateArray {
    
    // 方法 1: 三次翻转
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    
    // 方法 2: 使用额外数组
    public void rotateWithExtraSpace(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = nums[i];
        }
        
        System.arraycopy(result, 0, nums, 0, n);
    }
}
