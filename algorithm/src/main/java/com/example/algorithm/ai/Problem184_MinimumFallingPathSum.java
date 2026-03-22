package com.example.algorithm.ai;

/**
 * 184. 下降路径最小和
 * 给你一个 n x n 的方形整数数组 matrix，请你找出并返回通过 matrix 的下降路径的最小和。
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列。
 * 
 * 示例:
 * 输入: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出: 13
 */
public class Problem184_MinimumFallingPathSum {
    
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int minPrev = matrix[i - 1][j];
                if (j > 0) minPrev = Math.min(minPrev, matrix[i - 1][j - 1]);
                if (j < n - 1) minPrev = Math.min(minPrev, matrix[i - 1][j + 1]);
                matrix[i][j] += minPrev;
            }
        }
        
        int minSum = Integer.MAX_VALUE;
        for (int val : matrix[n - 1]) {
            minSum = Math.min(minSum, val);
        }
        
        return minSum;
    }
}
