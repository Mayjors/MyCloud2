package com.example.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

public class LruCache<K, V>{
    static class LinkedNode<K, V> {
        K key;
        V value;
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;
        public LinkedNode() {}

        public LinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<K, LinkedNode<K, V>> cache;
    private final int capacity;
    private final LinkedNode<K, V> head;
    private final LinkedNode<K, V> tail;

    public LruCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        head = new LinkedNode<>();
        tail = new LinkedNode<>();
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        LinkedNode<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        // 如果key存在，移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        LinkedNode<K, V> node = cache.get(key);
        if (node == null) {
            // key不存在则创建一个新节点
            LinkedNode<K, V> newNode = new LinkedNode<>(key, value);
            if (cache.size() == capacity) {
                cache.remove(tail.prev.key);
                removeTail();
            }
            cache.put(key, newNode);
            addToHead(newNode);
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(LinkedNode<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(LinkedNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(LinkedNode<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    private LinkedNode<K, V> removeTail() {
        LinkedNode<K, V> res = tail.prev;
        removeNode(tail.prev);
        return res;
    }

    public static void main(String[] args) {
        LruCache<Integer, Integer> cache = new LruCache<>(3);
        System.out.println(cache);
        cache.put(5, 55);
        cache.put(5, 60);
        System.out.println(cache);
        cache.put(4, 44);
        System.out.println(cache);
        cache.put(10, 0);
        cache.get(5);
        System.out.println(cache.get(5));
        System.out.println(cache);
        cache.put(2, 22);
        System.out.println(cache);
        cache.put(5, 199);

        System.out.println(cache.get(4));
        System.out.println(cache.get(10));

        System.out.println(cache);
    }
}
