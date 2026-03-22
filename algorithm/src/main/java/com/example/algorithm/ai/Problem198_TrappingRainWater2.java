package com.example.algorithm.ai;

/**
 * 198. 接雨水 II（三维版本）
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，
 * 请计算图中形状最多能接多少体积的雨水。
 * 
 * 示例:
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 */
public class Problem198_TrappingRainWater2 {
    
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) {
            return 0;
        }
        
        int m = heightMap.length;
        int n = heightMap[0].length;
        
        java.util.PriorityQueue<int[]> pq = new java.util.PriorityQueue<>(
            (a, b) -> a[2] - b[2]
        );
        
        boolean[][] visited = new boolean[m][n];
        
        // 将边界加入优先队列
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i, 0, heightMap[i][0]});
            pq.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = visited[i][n - 1] = true;
        }
        
        for (int j = 1; j < n - 1; j++) {
            pq.offer(new int[]{0, j, heightMap[0][j]});
            pq.offer(new int[]{m - 1, j, heightMap[m - 1][j]});
            visited[0][j] = visited[m - 1][j] = true;
        }
        
        int water = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            
            for (int[] dir : directions) {
                int ni = cell[0] + dir[0];
                int nj = cell[1] + dir[1];
                
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj]) {
                    visited[ni][nj] = true;
                    water += Math.max(0, cell[2] - heightMap[ni][nj]);
                    pq.offer(new int[]{ni, nj, 
                              Math.max(heightMap[ni][nj], cell[2])});
                }
            }
        }
        
        return water;
    }
}
