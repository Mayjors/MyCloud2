package com.example.algorithm.ai;

/**
 * 43. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在原地旋转图像。
 * 
 * 示例:
 * 输入: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出: [[7,4,1],[8,5,2],[9,6,3]]
 */
public class Problem43_RotateImage {
    
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // 先转置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // 再翻转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}
