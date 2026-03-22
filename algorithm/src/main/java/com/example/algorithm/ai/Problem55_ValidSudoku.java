package com.example.algorithm.ai;

/**
 * 55. 有效的数独
 * 请你判断一个 9 x 9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 1. 数字 1-9 在每一行只能出现一次。
 * 2. 数字 1-9 在每一列只能出现一次。
 * 3. 数字 1-9 在每一个 3x3 宫内只能出现一次。
 * 
 * 示例:
 * 输入: board = 
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出: true
 */
public class Problem55_ValidSudoku {
    
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                
                int num = board[i][j] - '1';
                int boxIndex = (i / 3) * 3 + (j / 3);
                
                if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                    return false;
                }
                
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        
        return true;
    }
}
