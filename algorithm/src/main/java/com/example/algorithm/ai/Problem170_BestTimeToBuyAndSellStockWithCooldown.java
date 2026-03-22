package com.example.algorithm.ai;

/**
 * 170. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易：
 * - 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * - 卖出股票后，你无法在第二天买入股票（即冷冻期为 1 天）。
 * 
 * 示例:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 */
public class Problem170_BestTimeToBuyAndSellStockWithCooldown {
    
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        
        int hold = -prices[0];  // 持有股票
        int sold = 0;           // 刚卖出（冷冻期）
        int rest = 0;           // 休息（可以买入）
        
        for (int i = 1; i < prices.length; i++) {
            int prevHold = hold;
            hold = Math.max(hold, rest - prices[i]);
            rest = Math.max(rest, sold);
            sold = prevHold + prices[i];
        }
        
        return Math.max(sold, rest);
    }
}
