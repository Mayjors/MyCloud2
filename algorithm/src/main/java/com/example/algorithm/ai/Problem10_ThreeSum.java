package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 10. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c，
 * 使得 a + b + c = 0？请你找出所有和为 0 且不重复的三元组。
 * 
 * 示例:
 * 输入: nums = [-1,0,1,2,-1,-4]
 * 输出: [[-1,-1,2],[-1,0,1]]
 */
public class Problem10_ThreeSum {
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            // 如果当前数大于0，后面的数都大于0，不可能和为0
            if (nums[i] > 0) break;
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳过重复元素
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Problem10_ThreeSum solution = new Problem10_ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums)); // [[-1, -1, 2], [-1, 0, 1]]
    }
}
