package com.example.algorithm.offer;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SlideWindow2 {
    /** 时间戳 **/
    private long timestamp;
    /** 滑动窗口数组,每个窗口统计本窗口的请求数 **/
    private long[] windows;
    /** 滑动窗口个数 **/
    private int windowCount;
    /** 窗口的size 用于计算总的流量上限 **/
    private long windowSize;
    /** 周期起始的窗口下标 **/
    private int start;
    /** 统计周期内总请求数 **/
    private long count;
    /** 流量限制 **/
    private long limit;

    public SlideWindow2(int windowCount, int windowSize, long limit) {
        this.windowCount = windowCount;
        this.windowSize = windowSize;
        this.windows = new long[windowSize];
        this.timestamp = System.currentTimeMillis();
        this.start = 0;
        this.limit = windowCount * windowCount;
    }

    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        long time = now-timestamp;
        if (time <= limit) {
            if (count < limit) {
                count++;
                int offset = start + (int) ((time /windowSize) % windowCount);
                windows[offset]++;
                return true;
            } else {
                return false;
            }
        } else {
            long diffWindow = time / windowSize;
            timestamp = now;
            if (diffWindow < windowCount *2) {
                int i;
                for (i=0; i<diffWindow-windowCount; i++) {
                    int index = start + i;
                    if (index > windowCount) {
                        index %= windowCount;
                    }
                    count += ((-1) * windows[index]);
                    windows[index] = 0L;
                }if (i >= windowCount) {
                    i = i % windowCount;
                }
                windows[i]++;
                return true;
            } else {
                for (int i = 0; i < windows.length; i++) {
                    windows[i] = 0L;
                }
                count = 0L;
                return true;
            }
        }
    }
}
