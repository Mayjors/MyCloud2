package com.example.algorithm.lfu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LFU<k, v> {
    private final int capcity;

    private Map<k, v> cache = new HashMap<>();

    private Map<k, HitRate> count = new HashMap<>();

    public LFU(int capcity) {
        this.capcity = capcity;
    }

    public void put(k key, v value) {
        v v = cache.get(key);
        if (v == null) {
            if (cache.size() == capcity) {
                removeElement();
            }
            count.put(key, new HitRate(key, 1, System.nanoTime()));
        } else {
            addHitCount(key);
        }
        cache.put(key, value);
    }

    public v get(k key) {
        v value = cache.get(key);
        if (value != null) {
            addHitCount(key);
            return value;
        }
        return null;
    }

    private void removeElement() {
        HitRate hr = Collections.min(count.values());
        cache.remove(hr.key);
        count.remove(hr.key);
    }

    private void addHitCount(k key) {
        HitRate hitRate = count.get(key);
        hitRate.hitCount = hitRate.hitCount +1;
        hitRate.lastTime = System.nanoTime();
    }

    class HitRate implements Comparable<HitRate> {
        private k key;
        private int hitCount;
        private long lastTime;

        public HitRate(k key, int hitCount, long lastTime) {
            this.key = key;
            this.hitCount = hitCount;
            this.lastTime = lastTime;
        }

        @Override
        public int compareTo(HitRate o) {
            return 0;
        }
    }

    public static void main(String[] args) {
        LFU<Integer, Integer> cache = new LFU<>(3);
        cache.put(2, 2);
        cache.put(1, 1);

        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

        cache.put(3, 3);
        cache.put(4, 4);

        //1、2元素都有访问次数，放入3后缓存满，加入4时淘汰3
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        //System.out.println(cache.get(1));
        System.out.println(cache.get(4));

        cache.put(5, 5);
        //目前2访问2次，1访问一次，4访问一次，由于4的时间比较新，放入5的时候移除1元素。
        System.out.println("-=-=-=-");
        cache.cache.entrySet().forEach(entry -> {
            System.out.println(entry.getValue());
        });

    }
}
