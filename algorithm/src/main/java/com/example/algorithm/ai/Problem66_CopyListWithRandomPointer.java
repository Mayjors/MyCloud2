package com.example.algorithm.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * 66. 复制带随机指针的链表
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random，
 * 该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的深拷贝。
 * 
 * 示例:
 * 输入: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 */
public class Problem66_CopyListWithRandomPointer {
    
    class Node {
        int val;
        Node next;
        Node random;
        
        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    
    // 方法 1: 哈希表
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        
        // 第一次遍历：创建所有节点
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }
        
        // 第二次遍历：设置 next 和 random
        current = head;
        while (current != null) {
            map.get(current).next = map.get(current.next);
            map.get(current).random = map.get(current.random);
            current = current.next;
        }
        
        return map.get(head);
    }
    
    // 方法 2: 原地复制
    public Node copyRandomListOptimized(Node head) {
        if (head == null) return null;
        
        // 第一步：复制每个节点并放在原节点后面
        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }
        
        // 第二步：设置 random 指针
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
        
        // 第三步：分离原链表和复制链表
        Node dummy = new Node(0);
        Node copyCurrent = dummy;
        current = head;
        
        while (current != null) {
            copyCurrent.next = current.next;
            copyCurrent = copyCurrent.next;
            current.next = current.next.next;
            current = current.next;
        }
        
        return dummy.next;
    }
}
