package com.example.algorithm.ai;

/**
 * 200. 会议室 II
 * 给你一个会议时间安排的数组 intervals，每个会议时间都会包括开始和结束的时间 
 * intervals[i] = [starti, endi]，为避免会议冲突，同时要考虑充分利用会议室资源，
 * 请你计算至少需要多少间会议室，才能满足这些会议安排。
 * 
 * 示例:
 * 输入: intervals = [[0,30],[5,10],[15,20]]
 * 输出: 2
 */
public class Problem200_MeetingRooms2 {
    
    // 方法 1: 扫描线算法
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        java.util.Arrays.sort(starts);
        java.util.Arrays.sort(ends);
        
        int rooms = 0;
        int endIdx = 0;
        
        for (int i = 0; i < n; i++) {
            if (starts[i] < ends[endIdx]) {
                rooms++;
            } else {
                endIdx++;
            }
        }
        
        return rooms;
    }
    
    // 方法 2: 最小堆
    public int minMeetingRoomsHeap(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        java.util.Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        java.util.PriorityQueue<Integer> minHeap = 
            new java.util.PriorityQueue<>();
        minHeap.offer(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i][1]);
        }
        
        return minHeap.size();
    }
}
