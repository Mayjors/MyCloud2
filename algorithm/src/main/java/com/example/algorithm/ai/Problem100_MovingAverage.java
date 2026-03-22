package com.example.algorithm.ai;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 100. 数据流中的移动平均值
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 * 
 * 示例:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1.0
 * m.next(10) = 5.5
 * m.next(3) = 4.66667
 * m.next(5) = 6.0
 */
public class Problem100_MovingAverage {
    
    class MovingAverage {
        private Queue<Integer> queue;
        private int size;
        private double sum;
        
        public MovingAverage(int size) {
            this.queue = new LinkedList<>();
            this.size = size;
            this.sum = 0;
        }
        
        public double next(int val) {
            if (queue.size() >= size) {
                sum -= queue.poll();
            }
            queue.offer(val);
            sum += val;
            return sum / queue.size();
        }
    }
}
