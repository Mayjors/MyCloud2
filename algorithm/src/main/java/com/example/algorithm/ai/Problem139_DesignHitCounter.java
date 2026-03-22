package com.example.algorithm.ai;

import java.util.*;

/**
 * 139. 设计敲击计数器
 * 设计一个敲击计数器，使它可以统计在过去 5 分钟内被敲击次数（即过去 300 秒）。
 * 
 * 示例:
 * HitCounter counter = new HitCounter();
 * counter.hit(1);
 * counter.hit(2);
 * counter.hit(3);
 * counter.getHits(4); // 3
 * counter.hit(300);
 * counter.getHits(300); // 4
 * counter.getHits(301); // 3
 */
public class Problem139_DesignHitCounter {
    
    class HitCounter {
        private Queue<Integer> queue;
        
        public HitCounter() {
            queue = new LinkedList<>();
        }
        
        public void hit(int timestamp) {
            queue.offer(timestamp);
            clean(timestamp);
        }
        
        public int getHits(int timestamp) {
            clean(timestamp);
            return queue.size();
        }
        
        private void clean(int timestamp) {
            while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
                queue.poll();
            }
        }
    }
    
    // 优化版本：使用固定大小数组
    class HitCounterOptimized {
        private int[] times;
        private int[] hits;
        
        public HitCounterOptimized() {
            times = new int[300];
            hits = new int[300];
        }
        
        public void hit(int timestamp) {
            int index = timestamp % 300;
            
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        }
        
        public int getHits(int timestamp) {
            int total = 0;
            
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    total += hits[i];
                }
            }
            
            return total;
        }
    }
}
