package com.example.algorithm.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * 48. LRU 缓存
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存约束的数据结构。
 * 
 * 示例:
 * LRUCache cache = new LRUCache(2);
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);    // 返回 1
 * cache.put(3, 3); // 该操作会使得关键字 2 作废
 * cache.get(2);    // 返回 -1 (未找到)
 * cache.put(4, 4); // 该操作会使得关键字 1 作废
 * cache.get(1);    // 返回 -1 (未找到)
 * cache.get(3);    // 返回 3
 * cache.get(4);    // 返回 4
 */
public class Problem48_LRU_Cache {
    
    class DListNode {
        int key;
        int value;
        DListNode prev;
        DListNode next;
        
        DListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    class LRUCache {
        private int capacity;
        private int size;
        private Map<Integer, DListNode> map;
        private DListNode head;
        private DListNode tail;
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.map = new HashMap<>();
            
            // 初始化双向链表
            head = new DListNode(0, 0);
            tail = new DListNode(0, 0);
            head.next = tail;
            tail.prev = head;
        }
        
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            
            DListNode node = map.get(key);
            moveToHead(node);
            return node.value;
        }
        
        public void put(int key, int value) {
            DListNode node = map.get(key);
            
            if (node != null) {
                node.value = value;
                moveToHead(node);
            } else {
                DListNode newNode = new DListNode(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                size++;
                
                if (size > capacity) {
                    DListNode removed = removeTail();
                    map.remove(removed.key);
                    size--;
                }
            }
        }
        
        private void addToHead(DListNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
        
        private void removeNode(DListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        private void moveToHead(DListNode node) {
            removeNode(node);
            addToHead(node);
        }
        
        private DListNode removeTail() {
            DListNode node = tail.prev;
            removeNode(node);
            return node;
        }
    }
}
