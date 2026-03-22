package com.example.algorithm.ai;

/**
 * 187. 不同路径 III
 * 在二维网格 grid 上，有 4 种类型的方格：
 * - 1 表示起始方格
 * - 2 表示结束方格
 * - 0 表示我们可以走过的空方格
 * - -1 表示我们无法跨越的障碍
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
 * 
 * 示例:
 * 输入: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * 输出: 2
 */
public class Problem187_UniquePaths3 {
    
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int empty = 1; // 包括起点
        int startX = 0, startY = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        return dfs(grid, startX, startY, empty);
    }
    
    private int dfs(int[][] grid, int x, int y, int empty) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || 
            grid[x][y] == -1) {
            return 0;
        }
        
        if (grid[x][y] == 2) {
            return empty == 0 ? 1 : 0;
        }
        
        grid[x][y] = -1; // 标记为已访问
        int paths = dfs(grid, x + 1, y, empty - 1) +
                    dfs(grid, x - 1, y, empty - 1) +
                    dfs(grid, x, y + 1, empty - 1) +
                    dfs(grid, x, y - 1, empty - 1);
        grid[x][y] = 0; // 回溯
        
        return paths;
    }
}
