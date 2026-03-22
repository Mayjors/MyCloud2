package com.example.algorithm.ai;

/**
 * 189. 火柴拼正方形
 * 你将得到一个整数数组 matchsticks，其中 matchsticks[i] 是第 i 个火柴棒的长度。
 * 你要用所有的火柴棍拼成一个正方形。你不能折断任何一根火柴棒，但你可以把它们连在一起，
 * 而且每根火柴棒必须使用一次。如果你能使这个正方形，则返回 true，否则返回 false。
 * 
 * 示例:
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 */
public class Problem189_MatchsticksToSquare {
    
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) return false;
        
        int sum = 0;
        for (int stick : matchsticks) sum += stick;
        
        if (sum % 4 != 0) return false;
        
        int side = sum / 4;
        java.util.Arrays.sort(matchsticks);
        reverse(matchsticks); // 降序排序，优化剪枝
        
        int[] sides = new int[4];
        return backtrack(matchsticks, 0, sides, side);
    }
    
    private boolean backtrack(int[] matchsticks, int index, int[] sides, int target) {
        if (index == matchsticks.length) {
            return sides[0] == target && sides[1] == target && 
                   sides[2] == target && sides[3] == target;
        }
        
        for (int i = 0; i < 4; i++) {
            if (sides[i] + matchsticks[index] > target) continue;
            
            // 剪枝：如果当前边和前一边长度相同，跳过
            int j = i - 1;
            while (j >= 0 && sides[j] == sides[i]) j--;
            if (j != -1) continue;
            
            sides[i] += matchsticks[index];
            if (backtrack(matchsticks, index + 1, sides, target)) return true;
            sides[i] -= matchsticks[index];
        }
        
        return false;
    }
    
    private void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
