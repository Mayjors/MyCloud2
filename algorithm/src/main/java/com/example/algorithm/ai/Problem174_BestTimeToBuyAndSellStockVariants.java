package com.example.algorithm.ai;

/**
 * 174. 最佳买卖股票时机（含冷却期和手续费的综合变体）
 * 这是一个综合变体题目
 */
public class Problem174_BestTimeToBuyAndSellStockVariants {
    
    // 基础版本：只能交易一次
    public int maxProfitOneTransaction(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        
        return maxProfit;
    }
    
    // 无限次交易
    public int maxProfitUnlimited(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
