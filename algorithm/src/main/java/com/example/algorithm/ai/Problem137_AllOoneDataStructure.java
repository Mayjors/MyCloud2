package com.example.algorithm.ai;

import java.util.*;

/**
 * 137. 全 O(1) 的数据结构
 * 设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * 实现 AllOne 类：
 * - inc(String key): 字符串 key 的计数增加 1
 * - dec(String key): 字符串 key 的计数减少 1
 * - getMaxKey(): 返回任意一个计数最大的字符串
 * - getMinKey(): 返回任意一个计数最小的字符串
 */
public class Problem137_AllOoneDataStructure {
    
    class AllOne {
        class Node {
            int count;
            Set<String> keys;
            Node prev, next;
            
            Node(int count) {
                this.count = count;
                this.keys = new HashSet<>();
            }
        }
        
        private Map<String, Node> keyToNode;
        private Node head, tail;
        
        public AllOne() {
            keyToNode = new HashMap<>();
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;
        }
        
        public void inc(String key) {
            if (!keyToNode.containsKey(key)) {
                // 新 key，插入到 head 后面
                if (head.next == tail || head.next.count > 1) {
                    addNodeAfter(head, new Node(1));
                }
                head.next.keys.add(key);
                keyToNode.put(key, head.next);
            } else {
                Node cur = keyToNode.get(key);
                cur.keys.remove(key);
                
                if (cur.next == tail || cur.next.count > cur.count + 1) {
                    addNodeAfter(cur, new Node(cur.count + 1));
                }
                cur.next.keys.add(key);
                keyToNode.put(key, cur.next);
                
                if (cur.keys.isEmpty()) {
                    removeNode(cur);
                }
            }
        }
        
        public void dec(String key) {
            Node cur = keyToNode.get(key);
            cur.keys.remove(key);
            
            if (cur.count > 1) {
                if (cur.prev == head || cur.prev.count < cur.count - 1) {
                    addNodeAfter(cur.prev, new Node(cur.count - 1));
                }
                cur.prev.keys.add(key);
                keyToNode.put(key, cur.prev);
            } else {
                keyToNode.remove(key);
            }
            
            if (cur.keys.isEmpty()) {
                removeNode(cur);
            }
        }
        
        public String getMaxKey() {
            return tail.prev == head ? "" : tail.prev.keys.iterator().next();
        }
        
        public String getMinKey() {
            return head.next == tail ? "" : head.next.keys.iterator().next();
        }
        
        private void addNodeAfter(Node node, Node newNode) {
            newNode.next = node.next;
            newNode.prev = node;
            node.next.prev = newNode;
            node.next = newNode;
        }
        
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
}
