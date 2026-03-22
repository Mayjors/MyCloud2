package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 33. 子集
 * 给你一个整数数组 nums，数组中的元素互不相同。返回该数组所有可能的子集（幂集）。
 * 
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class Problem33_Subsets {
    
    // 回溯法
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> current, 
                          int[] nums, int start) {
        result.add(new ArrayList<>(current));
        
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(result, current, nums, i + 1);
            current.remove(current.size() - 1);
        }
    }
    
    // 迭代法
    public List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }
        
        return result;
    }
}
