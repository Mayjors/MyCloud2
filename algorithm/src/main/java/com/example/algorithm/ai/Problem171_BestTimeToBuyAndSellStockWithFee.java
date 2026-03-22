package com.example.algorithm.ai;

/**
 * 171. 最佳买卖股票时机含手续费
 * 给定一个整数数组 prices，其中 prices[i] 表示第 i 天的股票价格；
 * 整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。
 * 
 * 示例:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 */
public class Problem171_BestTimeToBuyAndSellStockWithFee {
    
    public int maxProfit(int[] prices, int fee) {
        int cash = 0;       // 不持有股票的最大利润
        int hold = -prices[0];  // 持有股票的最大利润
        
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        
        return cash;
    }
}
