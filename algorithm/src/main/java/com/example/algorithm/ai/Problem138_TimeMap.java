package com.example.algorithm.ai;

import java.util.*;

/**
 * 138. 时间地图
 * 设计一个基于时间的键值数据结构，该结构可以在不同时间戳存储对应同一个键的多个值，
 * 并针对特定时间戳返回键对应的值。
 * 
 * 示例:
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);
 * timeMap.get("foo", 1); // "bar"
 * timeMap.get("foo", 3); // "bar"
 * timeMap.set("foo", "bar2", 4);
 * timeMap.get("foo", 4); // "bar2"
 */
public class Problem138_TimeMap {
    
    class TimeMap {
        class TimeValue {
            int timestamp;
            String value;
            TimeValue(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }
        }
        
        private Map<String, List<TimeValue>> map;
        
        public TimeMap() {
            map = new HashMap<>();
        }
        
        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(new TimeValue(timestamp, value));
        }
        
        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            
            List<TimeValue> values = map.get(key);
            int left = 0, right = values.size() - 1;
            String result = "";
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                TimeValue tv = values.get(mid);
                
                if (tv.timestamp <= timestamp) {
                    result = tv.value;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
            return result;
        }
    }
}
