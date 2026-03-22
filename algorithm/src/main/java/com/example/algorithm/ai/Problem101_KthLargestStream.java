package com.example.algorithm.ai;

import java.util.PriorityQueue;

/**
 * 101. 数据流中的第 K 大元素
 * 设计一个找到数据流中第 k 大元素的类（class）。
 * 注意是排序后的第 k 大元素，而不是第 k 个不同的元素。
 * 
 * 示例:
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3); // return 4
 * kthLargest.add(5); // return 5
 */
public class Problem101_KthLargestStream {
    
    class KthLargest {
        private int k;
        private PriorityQueue<Integer> minHeap;
        
        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.minHeap = new PriorityQueue<>(k);
            
            for (int num : nums) {
                add(num);
            }
        }
        
        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.offer(val);
            } else if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(val);
            }
            return minHeap.peek();
        }
    }
}
