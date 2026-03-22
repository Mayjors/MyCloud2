package com.example.algorithm.ai;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 102. 最后一块石头的重量
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * 
 * 示例:
 * 输入: [2,7,4,1,8,1]
 * 输出: 1
 */
public class Problem102_LastStoneWeight {
    
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        
        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();
            
            if (stone1 != stone2) {
                maxHeap.offer(stone1 - stone2);
            }
        }
        
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}
