package com.example.algorithm.ai;

/**
 * 27. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 * 1. 插入一个字符
 * 2. 删除一个字符
 * 3. 替换一个字符
 * 
 * 示例:
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 */
public class Problem27_EditDistance {
    
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // 动态规划
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i - 1][j], dp[i][j - 1]), // 删除或插入
                        dp[i - 1][j - 1] // 替换
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        Problem27_EditDistance solution = new Problem27_EditDistance();
        System.out.println(solution.minDistance("horse", "ros")); // 3
        System.out.println(solution.minDistance("intention", "execution")); // 5
    }
}
