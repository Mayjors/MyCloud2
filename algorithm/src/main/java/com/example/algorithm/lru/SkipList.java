package com.example.algorithm.lru;

import java.util.Arrays;

/**
 * 跳表
 */
public class SkipList {
    double factor = 0.5d;
    int maxLevel = 16;
    int currentMaxLevel = 1;
    Node head = new Node(maxLevel, -1);

    public Node find(int value) {
        Node p = head;
        for (int i= maxLevel -1; i>= 0; --i) {
            while (p.nextNodes[i] != null && p.nextNodes[i].value < value) {
                // 找到前一节点
                p = p.nextNodes[i];
            }
        }
        if (p.nextNodes[0] != null && p.nextNodes[0].value == value) {
            return p.nextNodes[0];
        } else {
            return null;
        }
    }

    public boolean insert(int value) {
        // level代表这个节点存在于几层链表中
        int level = randomLevel();
        if (level > currentMaxLevel) {
            level = ++ currentMaxLevel;
        }
        Node newNode = new Node(level, value);
        Node point = head;
        for (int i= currentMaxLevel-1; i>= 0; i--) {
            while (point.nextNodes[i] != null && point.nextNodes[i].value < value) {
                point = point.nextNodes[i];
            }
            if (level > i) {
                Node temp = point.nextNodes[i];
                point.nextNodes[i] = newNode;
                newNode.nextNodes[i] = temp;
            }
        }
        return true;
    }

    private int randomLevel() {
        int level = 1;
        while (level < maxLevel && Math.random() > 0.5) {
            level++;
        }
        return level;
    }

    public void printAll(int level) {
        if (level > currentMaxLevel) {
            throw new RuntimeException("还没有到这层数");
        }
        Node point = head;
        while (point.nextNodes[level-1] != null) {
            System.out.println(point.nextNodes[level-1] + " ");
            point = point.nextNodes[level-1];
        }
        System.out.println();
    }

    static class Node {
        /**
         * 节点的值
         */
        int value;

        /**
         * 这个节点在某一层的下一个节点的集合，比如nextNodes[2]就是node在第二层的下一个节点
         */
        Node[] nextNodes;

        public Node(int level, int value) {
            nextNodes = new Node[level];
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for (int i=0; i< 1000; i++) {
            skipList.insert(i);
        }
        for (int i = skipList.currentMaxLevel; i > 0; i--) {
            skipList.printAll(i);
        }
        skipList.find(945);
    }
}
