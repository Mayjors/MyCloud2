package com.example.algorithm.ai;

import java.util.*;

/**
 * 107. 课程表 II
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
 * 给你一个数组 prerequisites，其中 prerequisites[i] = [ai, bi]，
 * 表示在选修课程 ai 前必须先选修 bi。
 * 返回你为了学完所有课程所安排的学习顺序。
 * 
 * 示例:
 * 输入: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] 或 [0,2,1,3]
 */
public class Problem107_CourseSchedule2 {
    
    // 拓扑排序 (BFS)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
            indegree[pair[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] result = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
            
            for (int next : graph.get(course)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return index == numCourses ? result : new int[0];
    }
}
