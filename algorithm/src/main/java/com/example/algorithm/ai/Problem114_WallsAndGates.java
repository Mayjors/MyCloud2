package com.example.algorithm.ai;

import java.util.*;

/**
 * 114. 墙与门
 * 你被给定一个 m × n 的二维网格 rooms，网格中有以下三种可能的初始化值：
 * - -1 表示墙或障碍物
 * - 0 表示一扇门
 * - INF 表示一个空房间
 * 请给每个空房间填上该位置到最近门的距离，如果无法到达门，则填 INF。
 * 
 * 示例:
 * 输入: [[INF,-1,0,INF],[INF,INF,INF,-1],[INF,-1,INF,-1],[0,-1,INF,INF]]
 * 输出: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 */
public class Problem114_WallsAndGates {
    
    private static final int INF = Integer.MAX_VALUE;
    
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        
        int m = rooms.length;
        int n = rooms[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        
        // 将所有门加入队列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int distance = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                
                for (int[] dir : directions) {
                    int ni = cell[0] + dir[0];
                    int nj = cell[1] + dir[1];
                    
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && 
                        rooms[ni][nj] == INF) {
                        rooms[ni][nj] = distance;
                        queue.offer(new int[]{ni, nj});
                    }
                }
            }
        }
    }
}
