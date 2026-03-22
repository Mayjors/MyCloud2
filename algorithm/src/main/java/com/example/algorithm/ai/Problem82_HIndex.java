package com.example.algorithm.ai;

import java.util.Arrays;

/**
 * 82. H 指数
 * 给你一个整数数组 citations，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。
 * 计算并返回该研究者的 h 指数。
 * h 指数的定义：h 代表"高引用次数"（high citations），一名科研人员的 h 指数是指他（她）的
 * （n 篇论文中）至少有 h 篇论文分别被引用了至少 h 次。
 * 
 * 示例:
 * 输入: citations = [3,0,6,1,5]
 * 输出: 3
 */
public class Problem82_HIndex {
    
    // 方法 1: 排序
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int h = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] >= n - i) {
                h = n - i;
            } else {
                break;
            }
        }
        
        return h;
    }
    
    // 方法 2: 计数排序
    public int hIndexCounting(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        
        for (int c : citations) {
            if (c >= n) {
                count[n]++;
            } else {
                count[c]++;
            }
        }
        
        int total = 0;
        for (int i = n; i >= 0; i--) {
            total += count[i];
            if (total >= i) {
                return i;
            }
        }
        
        return 0;
    }
}
