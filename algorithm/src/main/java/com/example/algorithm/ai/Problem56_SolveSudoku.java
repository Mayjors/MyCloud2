package com.example.algorithm.ai;

/**
 * 56. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需遵循如下规则：
 * 1. 数字 1-9 在每一行只能出现一次。
 * 2. 数字 1-9 在每一列只能出现一次。
 * 3. 数字 1-9 在每一个 3x3 宫内只能出现一次。
 * 
 * 示例:
 * 输入: board = [["5","3",".",".","7",".",".",".","."],...]
 * 输出: 填充后的数独
 */
public class Problem56_SolveSudoku {
    
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 检查行
            if (board[row][i] == c) return false;
            // 检查列
            if (board[i][col] == c) return false;
            // 检查 3x3 宫格
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }
}
