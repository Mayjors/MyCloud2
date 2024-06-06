package com.example.algorithm.offer.limiter;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口限流
 */
public class SlidingWindowRateLimiter2 {
    // 阈值
    private Integer qps = 2;
    // 时间窗口总大小(毫秒)
    private long windowSize = 1000;
    // 多少个子窗口
    private Integer windowCount = 10;
    // 窗口列表
    private WindowInfo[] windowArray = new WindowInfo[windowCount];

    public SlidingWindowRateLimiter2(int qps) {
        this.qps = qps;
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < windowArray.length; i++) {
            windowArray[i] = new WindowInfo(currentTimeMillis, new AtomicInteger(0));
        }
    }

    public synchronized boolean tryAcquire() {
        long currentTimeMillis = System.currentTimeMillis();
        // 1. 计算当前时间窗口
        int currentIndex = (int) (currentTimeMillis % windowSize /(windowSize /windowCount));
        // 2. 更新当前窗口计数 & 重置过期窗口计数
        int sum = 0;
        for (int i = 0; i < windowArray.length; i++) {
            WindowInfo windowInfo = windowArray[i];
            if ((currentTimeMillis - windowInfo.getTime()) > windowSize) {
                windowInfo.getNumber().set(0);
                windowInfo.setTime(currentTimeMillis);
            }
            if (currentIndex == i && windowInfo.getNumber().get() < qps) {
                windowInfo.getNumber().incrementAndGet();
            }
            sum = sum + windowInfo.getNumber().get();
        }
        // 3. 当前QPS是否超过限制
        return sum < qps;
    }


    @Data
    private class WindowInfo {
        // 窗口开始时间
        private Long time;
        // 当前窗口的计数器
        private AtomicInteger number;

        public WindowInfo(Long time, AtomicInteger number) {
            this.time = time;
            this.number = number;
        }
    }
}
