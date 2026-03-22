package com.example.algorithm.ai;

/**
 * 190. 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），
 * 请你将它们分割成一个或多个长度至少为 3 的连续子序列。
 * 如果可以完成上述分割，则返回 true；否则，返回 false。
 * 
 * 示例:
 * 输入: [1,2,3,3,4,5]
 * 输出: true
 */
public class Problem190_SplitArrayIntoConsecutiveSubsequences {
    
    public boolean isPossible(int[] nums) {
        java.util.Map<Integer, Integer> count = new java.util.HashMap<>();
        java.util.Map<Integer, Integer> need = new java.util.HashMap<>();
        
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        for (int num : nums) {
            if (count.get(num) == 0) continue;
            
            if (need.getOrDefault(num, 0) > 0) {
                count.put(num, count.get(num) - 1);
                need.put(num, need.get(num) - 1);
                need.put(num + 1, need.getOrDefault(num + 1, 0) + 1);
            } else if (count.getOrDefault(num + 1, 0) > 0 && 
                       count.getOrDefault(num + 2, 0) > 0) {
                count.put(num, count.get(num) - 1);
                count.put(num + 1, count.get(num + 1) - 1);
                count.put(num + 2, count.get(num + 2) - 1);
                need.put(num + 3, need.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
        }
        
        return true;
    }
}
