package com.example.algorithm.ai;

import java.util.*;

/**
 * 83. O(1) 时间插入、删除和获取随机元素
 * 实现 RandomizedSet 类：
 * - RandomizedSet() 初始化 RandomizedSet 对象
 * - bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true；否则，返回 false。
 * - bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true；否则，返回 false。
 * - int getRandom() 随机返回现有集合中的一项
 * 
 * 示例:
 * RandomizedSet randomSet = new RandomizedSet();
 * randomSet.insert(1); // true
 * randomSet.remove(2); // false
 * randomSet.insert(2); // true
 * randomSet.getRandom(); // 1 or 2
 * randomSet.remove(1); // true
 * randomSet.insert(2); // false
 * randomSet.getRandom(); // 2
 */
public class Problem83_InsertDeleteGetRandomO1 {
    
    class RandomizedSet {
        private List<Integer> list;
        private Map<Integer, Integer> map;
        private Random random;
        
        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }
        
        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        
        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            
            int index = map.get(val);
            int lastElement = list.get(list.size() - 1);
            
            list.set(index, lastElement);
            map.put(lastElement, index);
            
            list.remove(list.size() - 1);
            map.remove(val);
            
            return true;
        }
        
        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
