package com.example.algorithm.tujie.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划
 */
public class Solution {

    public static void main(String[] args) {
        maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        lengthOfLongestSubstring("abcabcbb");
        maxProfit(new int[]{7,1,5,3,6,4});
    }

    /**
     * 剑指 Offer 19. 正则表达式匹配
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length()+1, n = p.length()+1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // 初始化首行
        for (int j=2; j<n; j+=2) {
            dp[0][j] = dp[0][j-2] && p.charAt(j-1) == '*';
        }
        // 状态转移
        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {
                if (p.charAt(j-1) == '*') {
                    if (dp[i][j-2]) dp[i][j] = true;
                    else if (dp[i-1][j] && s.charAt(i-1) == p.charAt(j-2)) dp[i][j] = true;
                    else if (dp[i-1][j] && p.charAt(j-2) == '.') dp[i][j] = true;
                } else {
                    if (dp[i-1][j-1] && s.charAt(i-1) == p.charAt(j-1)) dp[i][j] = true;
                    else if (dp[i-1][j-1] && p.charAt(j-1) == '.') dp[i][j] = true;
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i=1; i< nums.length; i++) {
            nums[i] += Math.max(nums[i-1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    /**
     * 剑指 Offer 47. 礼物的最大价值
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 12
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i==0 && j==0) continue;
                if (i==0) grid[i][j] += grid[i][j-1];
                else if (j==0) grid[i][j] += grid[i-1][j];
                else {
                    grid[i][j] += Math.max(grid[i][j-1], grid[i-1][j]);
                }
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     * 输入: "abcabcbb"
     * 输出: 3
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        // 当i<0，即s[j]左边没相同字符，则dp[j] = dp[j-1] +1
        // 当dp[j-1] < j-i,说明字符s[i]在字符串dp[j-1]区间外，则dp[j]=dp[j-1]+1
        // 当dp[j-1]>=j-i,说明自负s[i]在字符串dp[j-1]区间中，则dp[j]的左边界由s[i]决定，即dp[j] =j-i
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, temp = 0, len = s.length();
        for (int j=0; j<len; j++) {
            int i = dic.getOrDefault(s.charAt(j), -1);
            dic.put(s.charAt(j), j);
            temp = temp<j-i ? temp+1 : j-i;
            res = Math.max(res, temp);
        }
        return res;
    }

    /**
     * 剑指 Offer 49.丑数
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n - 1];
    }

    /**
     * 剑指 Offer 60. n 个骰子的点数
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0/6.0);
        for (int i=2; i<=n; i++) {
            double[] tmp = new double[5 * i +1];
            for (int j=0; j<dp.length; j++) {
                for (int k=0; k<6; k++) {
                    tmp[j+k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }

    /**
     * 剑指 Offer 63. 股票的最大利润
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        // dp[i]=max(dp[i−1],prices[i]−min(cost,prices[i])
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price-cost);
        }
        return profit;
    }
}
