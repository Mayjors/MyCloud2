package com.example.algorithm.ai;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 49. 数据流的中位数
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，
 * 中位数是两个中间值的平均值。
 * 
 * 示例:
 * MedianFinder mf = new MedianFinder();
 * mf.addNum(1);
 * mf.addNum(2);
 * mf.findMedian(); // 返回 1.5
 * mf.addNum(3);
 * mf.findMedian(); // 返回 2.0
 */
public class Problem49_MedianFinder {
    
    class MedianFinder {
        // 最大堆存储较小的一半
        private PriorityQueue<Integer> maxHeap;
        // 最小堆存储较大的一半
        private PriorityQueue<Integer> minHeap;
        
        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }
        
        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            
            // 平衡两个堆
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        
        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                return maxHeap.peek();
            }
        }
    }
}
