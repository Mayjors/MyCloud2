package com.example.algorithm.simple;

import java.util.HashMap;

public class XLruCache<K, V> {
    private int size;

    private HashMap<K, Node> map;
    private Node head;
    private Node tail;

    public XLruCache(int size) {
        this.size = size;
        map = new HashMap<>();
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
    }

    public void put(K key, V value) {
        Node node = map.get(key);
        if (node == null) {
            if (map.size() >= size) {
                Node delHead =removeHead();
                map.remove(delHead.k);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addLast(newNode);
        } else {
            node.v = value;
            moveNodeToTail(node);
        }
    }

    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        moveNodeToTail(node);
        return node.v;
    }

    public void moveNodeToTail(Node node) {
        if (tail == node) {
            return;
        }
        if (head == node) {
            head = node.next;
            head.pre = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        node.pre = tail;
        node.next = null;
        tail.next = node;
        tail = node;
    }

    public void addLast(Node newNode) {
        if (newNode == null) {
            return;
        }
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.pre = tail;
            tail = newNode;
        }
    }

    private Node removeHead() {
        if (head == null) {
            return null;
        }
        Node res = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = res.next;
            head.pre = null;
            res.next = null;
        }
        return res;
    }


    class Node {
        K k;
        V v;
        Node pre;
        Node next;

        Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }
}
