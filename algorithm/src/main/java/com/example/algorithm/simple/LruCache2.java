package com.example.algorithm.simple;

import java.util.HashMap;

public class LruCache2 {
    private Node head;
    private Node tail;
    private int capacity;
    private HashMap<Integer, Node> hashMap;

    public LruCache2(int capacity) {
        this.capacity = capacity;
        hashMap = new HashMap<Integer, Node>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return -1;
        }

        // 移除当前node
        if (node.key == tail.key) {
            node.prev.next = tail;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        // moveToHead
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;

//        head.next = node;
//        node.prev = head;
//        node.next = head.next;
//        head.next.prev = node;

        return node.value;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private Node removeTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(Integer key, Integer value) {
        Node node = hashMap.get(key);
        if (node == null) {
            if (hashMap.size() == capacity) {
                // 超过容量，首先移除尾部的节点
//                Node tailNode = removeTail();
                Node tailNode = tail.prev;
                tailNode.prev.next = tailNode.next;
                tailNode.next.prev = tailNode.prev;
                hashMap.remove(tailNode.key);
            }
            node = new Node(key, value);
            hashMap.put(key, node);
        } else {
            removeNode(node);
        }
        addToHead(node);
    }

    public static void main(String[] args) {
        LruCache2 cache = new LruCache2(4);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.get(2);
        cache.put(5, 5);
        cache.put(5, 5);
        cache.get(2);
        System.out.println(cache.get(1));
    }
    static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        Node() {

        }
    }
}
