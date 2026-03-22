package com.example.algorithm.ai;

/**
 * 99. 缺失数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 
 * 示例:
 * 输入: nums = [3,0,1]
 * 输出: 2
 */
public class Problem99_MissingNumber {
    
    // 方法 1: 数学求和
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        
        for (int num : nums) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }
    
    // 方法 2: 异或
    public int missingNumberXOR(int[] nums) {
        int result = nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        
        return result;
    }
}
