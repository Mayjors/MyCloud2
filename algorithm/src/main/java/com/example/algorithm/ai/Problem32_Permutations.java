package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 32. 全排列
 * 给定一个不含重复数字的数组 nums，返回其所有可能的全排列。
 * 
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Problem32_Permutations {
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int num : nums) {
            if (current.contains(num)) continue;
            current.add(num);
            backtrack(result, current, nums);
            current.remove(current.size() - 1);
        }
    }
}
