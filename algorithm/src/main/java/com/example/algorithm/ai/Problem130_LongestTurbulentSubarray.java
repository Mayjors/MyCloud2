package com.example.algorithm.ai;

/**
 * 130. 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * - 若 i <= k < j，当 k 为奇数时，A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * - 或 i <= k < j，当 k 为偶数时，A[k] > A[k+1]，且当 k 为奇数时，A[k] < A[k+1]。
 * 
 * 示例:
 * 输入: arr = [9,4,2,10,7,8,8,1,9]
 * 输出: 5
 */
public class Problem130_LongestTurbulentSubarray {
    
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length == 1) return 1;
        
        int maxLen = 1;
        int up = 1, down = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                up = down + 1;
                down = 1;
            } else if (arr[i] < arr[i - 1]) {
                down = up + 1;
                up = 1;
            } else {
                up = 1;
                down = 1;
            }
            
            maxLen = Math.max(maxLen, Math.max(up, down));
        }
        
        return maxLen;
    }
}
