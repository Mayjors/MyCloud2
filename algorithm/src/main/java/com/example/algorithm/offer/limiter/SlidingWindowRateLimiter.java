package com.example.algorithm.offer.limiter;

public class SlidingWindowRateLimiter {
    // 时间窗口大小，单位毫秒
    long windowSize;
    // 分片窗口数
    int shardNum;
    // 运行通过的请求数
    int maxRequestCount;
    // 各个窗口内请求计数
    int[] shardRequestCount;
    // 请求总数
    int totalCount;
    // 当前窗口下标
    int shardId;
    // 每个小窗口大小，毫秒
    long tinyWindowSize;
    // 窗口右边界
    long windowBorder;

    public SlidingWindowRateLimiter(long windowSize, int shardNum, int maxRequestCount) {
        this.windowSize = windowSize;
        this.shardNum = shardNum;
        this.maxRequestCount = maxRequestCount;
        this.shardRequestCount = new int[shardNum];
        this.totalCount = 0;
        this.shardId = 0;
        this.tinyWindowSize = windowSize / shardNum;
        this.windowBorder = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now < windowBorder) {
            return false;
        }
        do {
            shardId = (++shardId) % shardNum;
            totalCount -=shardRequestCount[shardId];
            shardRequestCount[shardId] = 0;
            windowBorder += tinyWindowSize;
        } while (windowBorder < now);
        if (totalCount < maxRequestCount) {
            shardRequestCount[shardId]++;
            totalCount++;
            return true;
        } else {
            return false;
        }
    }


}
