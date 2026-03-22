package com.example.algorithm.ai;

/**
 * 173. 最佳买卖股票时机 IV（最多 k 笔交易）
 * 给定一个整数数组 prices 和一个整数 k，其中 prices[i] 是某支股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 
 * 示例:
 * 输入: k = 2, prices = [2,4,1]
 * 输出: 2
 */
public class Problem173_BestTimeToBuyAndSellStock4 {
    
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k == 0) return 0;
        
        // 如果 k 很大，相当于无限次交易
        if (k >= prices.length / 2) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
        
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        
        for (int i = 0; i <= k; i++) {
            buy[i] = -prices[0];
        }
        
        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        
        return sell[k];
    }
}
