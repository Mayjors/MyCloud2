package com.example.algorithm.ai;

import java.util.*;

/**
 * 115. 蛇梯棋
 * 给你一个大小为 n x n 的整数矩阵 board，方格按从 1 到 n^2 编号，
 * 编号遵循转行交替方式，从左下角开始的每一行改变方向。
 * 你一开始位于棋盘上的方格 1。每一回合，玩家需要从当前方格 curr 开始出发，
 * 按下述要求前进：
 * 1. 选定目标方格 next，目标方格的编号在范围 [curr + 1, min(curr + 6, n^2)]
 * 2. 如果目标方格存在蛇或梯子，那么玩家会移动到蛇或梯子的目的地
 * 返回到达方格 n^2 所需的最少移动次数。
 * 
 * 示例:
 * 输入: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],
 *              [-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
 * 输出: 4
 */
public class Problem115_SnakesAndLadders {
    
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        
        boolean[] visited = new boolean[target + 1];
        visited[1] = true;
        
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                if (curr == target) {
                    return moves;
                }
                
                for (int next = curr + 1; next <= Math.min(curr + 6, target); next++) {
                    int[] pos = getBoardPosition(next, n);
                    int destination = board[pos[0]][pos[1]] == -1 ? next : board[pos[0]][pos[1]];
                    
                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.offer(destination);
                    }
                }
            }
            
            moves++;
        }
        
        return -1;
    }
    
    private int[] getBoardPosition(int num, int n) {
        int row = n - 1 - (num - 1) / n;
        int col = (num - 1) % n;
        
        // 如果是偶数行（从下往上数），需要反转列
        if ((n - 1 - row) % 2 == 1) {
            col = n - 1 - col;
        }
        
        return new int[]{row, col};
    }
}
