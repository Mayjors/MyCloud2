package com.example.algorithm.ai;

import java.util.*;

/**
 * 35. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1。
 * 在选修某些课程之前需要一些先修课程。先修课程按数组 prerequisites 给出。
 * 请你判断是否可能完成所有课程的学习。
 * 
 * 示例:
 * 输入: numCourses = 2, prerequisites = [[1,0]]
 * 输出: true
 */
public class Problem35_CourseSchedule {
    
    // 拓扑排序 (BFS)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            
            for (int next : graph.get(course)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return count == numCourses;
    }
    
    // DFS 检测环
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
        }
        
        int[] visited = new int[numCourses]; // 0:未访问，1:访问中，2:已访问
        
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, visited, i)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int node) {
        if (visited[node] == 1) return true;
        if (visited[node] == 2) return false;
        
        visited[node] = 1;
        for (int neighbor : graph.get(node)) {
            if (hasCycle(graph, visited, neighbor)) {
                return true;
            }
        }
        visited[node] = 2;
        
        return false;
    }
}
