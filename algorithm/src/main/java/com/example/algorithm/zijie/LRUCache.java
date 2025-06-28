package com.example.algorithm.zijie;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Entry head, tail;
    int capacity;
    int size;
    Map<Integer, Entry> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        initLinkedList();
        size = 0;
        cache = new HashMap<>(capacity);
    }

    public void put(int key, int value) {
        Entry node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
            return;
        }
        if (size == capacity) {
            Entry lastNode = tail.pre;
            deleteNode(lastNode);
            cache.remove(lastNode.key);
            size--;
        }

        Entry newNode = new Entry(key, value);
        addNode(newNode);
        cache.put(key, newNode);
        size++;
    }

    public Integer get(int key) {
        Entry node = cache.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(Entry node) {
        deleteNode(node);
        addNode(node);
    }

    public void addNode(Entry node) {
        head.next.pre = node;
        node.next = head.next;

        node.pre = head;
        head.next = node;
    }

    private void deleteNode(Entry node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void initLinkedList() {
        head = new Entry();
        tail = new Entry();

        head.next = tail;
        tail.pre = head;
    }


    private static class Entry {
        int key;
        int value;
        Entry pre;
        Entry next;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Entry() {
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        LRUCache cache1 = new LRUCache(2);
        cache1.put(1, 1);
        cache1.put(2, 2);

        System.out.println("get(1)之后的头结点之前的节点: "+cache1.head.next);
        System.out.println("get(1)节点: "+cache1.get(1));
        System.out.println("get(1)之后的头结点之后的节点: "+cache1.head.next);

        cache1.put(3, 3);
        System.out.println("put(3)之后的头结点之后的节点: "+cache1.head.next);

        System.out.println("get(2)节点: "+cache1.get(2));
        System.out.println("get(2)之后的头结点之后的节点: "+cache1.head.next);
    }
}