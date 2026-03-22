package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组。
 * 
 * 示例:
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 */
public class Problem39_MergeIntervals {
    
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        
        // 按起始位置排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                // 重叠，合并
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                // 不重叠，添加当前区间
                result.add(current);
                current = intervals[i];
            }
        }
        
        result.add(current);
        
        return result.toArray(new int[result.size()][]);
    }
    
    public static void main(String[] args) {
        Problem39_MergeIntervals solution = new Problem39_MergeIntervals();
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result = solution.merge(intervals);
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + "," + interval[1] + "]");
        }
    }
}
