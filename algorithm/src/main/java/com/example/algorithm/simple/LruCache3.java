package com.example.algorithm.simple;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LruCache3<V> {

    private int capacity = 1024;
    /**
     * Node 记录表
     */
    private Map<String, ListNode<String, V>> table = new ConcurrentHashMap<>();
    private ListNode<String, V> head;
    private ListNode<String, V> tail;

    public LruCache3(int capacity) {
        this();
        this.capacity = capacity;
    }
    public LruCache3() {
        head = new ListNode<>();
        tail = new ListNode<>();
        head.next = tail;
        head.prev = null;
        tail.prev = head;
        tail.next = null;
    }

    public V get(String key) {
        ListNode<String, V> node = table.get(key);
        // 如果node不在表中，代表缓存中没有
        if (node == null) {
            return null;
        }
        // 如果存在，则需要移动node结点到表头
        // 截断链表
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // 移动节点到表头
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
        // 存在缓存表
        table.put(key, node);
        return node.value;
    }

    public void put(String key, V value) {
        ListNode<String, V> node = table.get(key);
        // 如果node不在表中，代表缓存中并没有
        if (node == null) {
            if (table.size() == capacity) {
                // 超过容量，首先移除尾部的节点
                table.remove(tail.prev.key);
                tail.prev = tail.next;
                tail.next = null;
                tail = tail.prev;
            }
            node = new ListNode<>(key, value);
            table.put(key, node);
        }
        // 如果存在，则需要移动node节点到表头
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public static class ListNode<K, V> {
        private K key;
        private V value;
        ListNode<K, V> prev;
        ListNode<K, V> next;

        public ListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public ListNode() {}
    }
    public static void main(String[] args) {
        LruCache3<ListNode> cache = new LruCache3<>(4);
        ListNode<String, Integer> node1 = new ListNode<>("key1", 1);
        ListNode<String, Integer> node2 = new ListNode<>("key2", 2);
        ListNode<String, Integer> node3 = new ListNode<>("key3", 3);
        ListNode<String, Integer> node4 = new ListNode<>("key4", 4);
        ListNode<String, Integer> node5 = new ListNode<>("key5", 5);
        cache.put("key1", node1);
        cache.put("key2", node2);
        cache.put("key3", node3);
        cache.put("key4", node4);
        cache.get("key2");
        cache.put("key5", node5);
        cache.put("key5", node5);
        cache.get("key2");
        System.out.println(cache.get("key1"));
    }
}