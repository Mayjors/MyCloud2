package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 组合总和
 * 给你一个无重复元素的整数数组 candidates 和一个目标整数 target，
 * 找出 candidates 中可以使数字和为目标数 target 的所有不同组合。
 * candidates 中的同一个数字可以无限制重复被选取。
 * 
 * 示例:
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[2,2,3],[7]]
 */
public class Problem57_CombinationSum {
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> current, 
                          int[] candidates, int remain, int start) {
        if (remain < 0) return;
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(result, current, candidates, remain - candidates[i], i);
            current.remove(current.size() - 1);
        }
    }
}
