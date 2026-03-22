package com.example.algorithm.ai;

/**
 * 159. 目标和
 * 给你一个非负整数数组 nums 和一个整数 target。
 * 向数组中的每个整数前添加 '+' 或 '-'，然后串联起所有整数，构造一个表达式。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同表达式的数目。
 * 
 * 示例:
 * 输入: nums = [1,1,1,1,1], target = 3
 * 输出: 5
 */
public class Problem159_TargetSum {
    
    // 动态规划（0-1 背包）
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        if (sum < Math.abs(target)) return 0;
        if ((sum + target) % 2 != 0) return 0;
        
        int positiveSum = (sum + target) / 2;
        int[] dp = new int[positiveSum + 1];
        dp[0] = 1;
        
        for (int num : nums) {
            for (int i = positiveSum; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        
        return dp[positiveSum];
    }
    
    // DFS + 记忆化
    public int findTargetSumWaysDFS(int[] nums, int target) {
        return dfs(nums, 0, 0, target, new java.util.HashMap<>());
    }
    
    private int dfs(int[] nums, int index, int sum, int target, 
                    java.util.Map<String, Integer> memo) {
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }
        
        String key = index + "," + sum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int result = dfs(nums, index + 1, sum + nums[index], target, memo) +
                     dfs(nums, index + 1, sum - nums[index], target, memo);
        
        memo.put(key, result);
        return result;
    }
}
