package com.example.algorithm.ai;

/**
 * 180. 预测赢家
 * 给你一个整数数组 nums。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
 * 玩家 1 和玩家 2 轮流进行，玩家 1 先开始。每回合，玩家从数组的任意一端取一个数字。
 * 取到的数字将会从数组中移除。直到所有数字都被取完。
 * 如果玩家 1 的得分大于或等于玩家 2 的得分，返回 true。
 * 
 * 示例:
 * 输入: nums = [1,5,2]
 * 输出: false
 */
public class Problem180_PredictTheWinner {
    
    // 动态规划
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], 
                                    nums[j] - dp[i][j - 1]);
            }
        }
        
        return dp[0][n - 1] >= 0;
    }
    
    // 递归 + 记忆化
    public boolean PredictTheWinnerMemo(int[] nums) {
        return helper(nums, 0, nums.length - 1, new java.util.HashMap<>()) >= 0;
    }
    
    private int helper(int[] nums, int left, int right, 
                       java.util.Map<String, Integer> memo) {
        if (left == right) return nums[left];
        
        String key = left + "," + right;
        if (memo.containsKey(key)) return memo.get(key);
        
        int result = Math.max(nums[left] - helper(nums, left + 1, right, memo),
                             nums[right] - helper(nums, left, right - 1, memo));
        
        memo.put(key, result);
        return result;
    }
}
