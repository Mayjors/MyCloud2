package com.example.algorithm.ai;

import java.util.*;

/**
 * 105. 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。
 * 每个任务执行需要 1 个单位时间。两个相同种类的任务之间必须有长度为整数 n 的冷却时间。
 * 计算完成所有任务所需要的最短时间。
 * 
 * 示例:
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 */
public class Problem105_TaskScheduler {
    
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }
        
        Arrays.sort(count);
        int maxFreq = count[25];
        
        // 计算最大频率的任务有多少个
        int maxCount = 1;
        for (int i = 24; i >= 0; i--) {
            if (count[i] == maxFreq) {
                maxCount++;
            } else {
                break;
            }
        }
        
        // 计算需要的最少时间
        int partCount = maxFreq - 1;
        int emptySlots = partCount * (n - (maxCount - 1));
        int availableTasks = tasks.length - maxFreq * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);
        
        return tasks.length + idles;
    }
}
