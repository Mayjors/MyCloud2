package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 58. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 
 * 示例:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8
 * 输出: [[1,1,6],[1,2,5],[1,7],[2,6]]
 */
public class Problem58_CombinationSum2 {
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
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
            // 跳过重复元素
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            
            current.add(candidates[i]);
            backtrack(result, current, candidates, remain - candidates[i], i + 1);
            current.remove(current.size() - 1);
        }
    }
}
