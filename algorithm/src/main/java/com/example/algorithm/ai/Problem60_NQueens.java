package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. N 皇后
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 示例:
 * 输入: n = 4
 * 输出: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 */
public class Problem60_NQueens {
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        for (char[] row : board) {
            for (int i = 0; i < n; i++) {
                row[i] = '.';
            }
        }
        
        backtrack(result, board, 0, n);
        return result;
    }
    
    private void backtrack(List<List<String>> result, char[][] board, int row, int n) {
        if (row == n) {
            result.add(construct(board));
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q';
                backtrack(result, board, row + 1, n);
                board[row][col] = '.';
            }
        }
    }
    
    private boolean isValid(char[][] board, int row, int col, int n) {
        // 检查列
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        
        // 检查左上对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        
        // 检查右上对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        
        return true;
    }
    
    private List<String> construct(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }
}
