package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. 插入区间
 * 给你一个无重叠的、按照区间起始端点排序的区间列表 intervals，
 * 其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，
 * 请你将一个新区间 newInterval 插入到 intervals 中。
 * 
 * 示例:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 */
public class Problem51_InsertInterval {
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        
        // 添加所有在 newInterval 之前且不重叠的区间
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // 合并所有重叠的区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // 添加剩余的区间
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }
}
