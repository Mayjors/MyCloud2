package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 44. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix，请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 
 * 示例:
 * 输入: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出: [1,2,3,6,9,8,7,4,5]
 */
public class Problem44_SpiralMatrix {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;
        
        while (top <= bottom && left <= right) {
            // 从左到右
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;
            
            // 从上到下
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            
            // 从右到左
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--;
            }
            
            // 从下到上
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        
        return result;
    }
}
