package com.example.algorithm.ai;

/**
 * 199. 天际线问题
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部线。
 * 给你所有建筑物的位置和高度，请返回由这些建筑物形成的天际线。
 * 
 * 示例:
 * 输入: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * 输出: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 */
public class Problem199_TheSkylineProblem {
    
    public java.util.List<java.util.List<Integer>> getSkyline(int[][] buildings) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<int[]> events = new java.util.ArrayList<>();
        
        for (int[] building : buildings) {
            events.add(new int[]{building[0], -building[2]}); // 左端点，负高度表示开始
            events.add(new int[]{building[1], building[2]});  // 右端点，正高度表示结束
        }
        
        // 排序：按 x 坐标，x 相同时按高度
        events.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>(
            java.util.Collections.reverseOrder()
        );
        pq.offer(0);
        int prevMax = 0;
        
        for (int[] event : events) {
            int x = event[0];
            int h = event[1];
            
            if (h < 0) {
                pq.offer(-h);
            } else {
                pq.remove(h);
            }
            
            int currMax = pq.peek();
            if (currMax != prevMax) {
                java.util.List<Integer> point = new java.util.ArrayList<>();
                point.add(x);
                point.add(currMax);
                result.add(point);
                prevMax = currMax;
            }
        }
        
        return result;
    }
}
