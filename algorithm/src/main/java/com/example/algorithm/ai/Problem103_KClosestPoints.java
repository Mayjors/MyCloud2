package com.example.algorithm.ai;

import java.util.PriorityQueue;
import java.util.Arrays;

/**
 * 103. 最接近原点的 K 个点
 * 给定一个数组 points，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，
 * 并且是一个整数 k，返回离原点 (0,0) 最近的 k 个点。
 * 
 * 示例:
 * 输入: points = [[1,3],[-2,2]], k = 1
 * 输出: [[-2,2]]
 */
public class Problem103_KClosestPoints {
    
    // 方法 1: 最大堆
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );
        
        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        
        return result;
    }
    
    // 方法 2: 快速选择
    public int[][] kClosestQuickSelect(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }
    
    private void quickSelect(int[][] points, int left, int right, int k) {
        if (left >= right) return;
        
        int pivotIndex = partition(points, left, right);
        
        if (pivotIndex == k) {
            return;
        } else if (pivotIndex < k) {
            quickSelect(points, pivotIndex + 1, right, k);
        } else {
            quickSelect(points, left, pivotIndex - 1, k);
        }
    }
    
    private int partition(int[][] points, int left, int right) {
        int[] pivot = points[right];
        int pivotDist = pivot[0] * pivot[0] + pivot[1] * pivot[1];
        int i = left;
        
        for (int j = left; j < right; j++) {
            int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            if (dist <= pivotDist) {
                swap(points, i, j);
                i++;
            }
        }
        swap(points, i, right);
        
        return i;
    }
    
    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}
