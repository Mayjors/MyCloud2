package com.example.algorithm.offer;

public class Medium {
    public static void main(String[] args) {

    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    static boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        // 判断传入参数的可行性i 与图行数row比较，j与列数col比较
        // i,j初始0，在图左上角
        // k是传入字符串当前索引，开始时0，如果当前字符串索引和图当前索引对应的值不相等，表示第一个数就不相等
        // 所以继续找第一个相等的数

        // 如果board[i][j] == word[k] 则表明当前找到了对应的数，就继续执行
        if (i >= board.length || i<0|| j >= board[0].length || j < 0 || board[i][j] != words[k])
            return false;
        // 表明找到了，每个字符都找到了
        // 一开始k=0，而word.length肯定不是0，所以没找到，就执行dfs继续
        if (k == words.length-1) {
            return true;
        }
        // 访问过的标记空字符串，“ ”是空格 '\0'是空字符串，不一样的！
        // 比如当前为A，没有标记找过，且A是word中对应元素，则此时应该找A下一个元素，假设是B，在dfs（B）的时候还是-
        // ->要搜索B左边的元素（假设A在B左边），所以就是ABA（凭空多出一个A，A用了2次，不可以），如果标记为空字符串->
        // 就不会有这样的问题，因为他们值不相等AB != ABA。
        board[i][j] = '\0';

        boolean res = dfs(board, words, i + 1, j, k + 1) || dfs(board, words, i - 1, j, k + 1) ||
                dfs(board, words, i, j + 1, k + 1) || dfs(board, words, i , j - 1, k + 1);

        // 还原找过的元素，因为之后可能还会访问到
        board[i][j] = words[k];
        return res;
    }
}
