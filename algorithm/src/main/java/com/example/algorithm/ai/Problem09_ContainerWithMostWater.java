package com.example.algorithm.ai;

/**
 * 9. 盛最多水的容器
 * 给你 n 个非负整数 a1,a2,...,an，每个数代表坐标中的一个点 (i, ai)。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
public class Problem09_ContainerWithMostWater {
    
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            
            // 移动较短的板子
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        Problem09_ContainerWithMostWater solution = new Problem09_ContainerWithMostWater();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(height)); // 49
    }
}
