package com.example.algorithm.ai;

/**
 * 155. 最大矩形
 * 给定一个仅包含 0 和 1、大小为 rows x cols 的二维二进制矩阵，
 * 找出只包含 1 的最大矩形，并返回其面积。
 * 
 * 示例:
 * 输入: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出: 6
 */
public class Problem155_MaximalRectangle {
    
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;
        
        for (char[] row : matrix) {
            for (int j = 0; j < n; j++) {
                if (row[j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        
        return maxArea;
    }
    
    private int largestRectangleArea(int[] heights) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        
        return maxArea;
    }
}
