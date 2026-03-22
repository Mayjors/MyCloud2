package com.example.algorithm.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。
 * 
 * 示例:
 * 输入: nums = [2,7,11,15], target = 9
 * 输出: [0,1]
 */
public class Problem01_TwoSum {
    
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }
    
    // 测试
    public static void main(String[] args) {
        Problem01_TwoSum solution = new Problem01_TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
