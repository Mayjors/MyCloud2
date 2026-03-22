package com.example.algorithm.ai;

import java.util.*;

/**
 * 112. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与太平洋和大西洋相邻。
 * "太平洋"处于大陆的左边界和上边界，而"大西洋"处于大陆的右边界和下边界。
 * 给定一个 m x n 的非负整数矩阵来表示大陆上每个单元格的高度，
 * 雨水可以往四个方向流动到高度相同或更低的相邻单元格。
 * 返回可以同时流向太平洋和大西洋的单元格坐标列表。
 * 
 * 示例:
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 */
public class Problem112_PacificAtlanticWaterFlow {
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0) return result;
        
        int m = heights.length;
        int n = heights[0].length;
        
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        // 从太平洋边界开始 DFS
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, n - 1);
        }
        
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j);
            dfs(heights, atlantic, m - 1, j);
        }
        
        // 找到可以同时到达两个海洋的单元格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private void dfs(int[][] heights, boolean[][] visited, int i, int j) {
        int m = heights.length;
        int n = heights[0].length;
        
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
            return;
        }
        
        visited[i][j] = true;
        
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && 
                heights[ni][nj] >= heights[i][j]) {
                dfs(heights, visited, ni, nj);
            }
        }
    }
}
