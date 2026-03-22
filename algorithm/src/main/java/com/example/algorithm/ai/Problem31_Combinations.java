package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 31. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 输入: n = 4, k = 2
 * 输出: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 */
public class Problem31_Combinations {
    
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> current, 
                          int start, int n, int k) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            current.add(i);
            backtrack(result, current, i + 1, n, k);
            current.remove(current.size() - 1);
        }
    }
}
