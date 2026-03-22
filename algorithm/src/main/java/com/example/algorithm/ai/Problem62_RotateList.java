package com.example.algorithm.ai;

/**
 * 62. 旋转链表
 * 给你一个链表的头节点 head，旋转链表，将链表每个节点向右移动 k 个位置。
 * 
 * 示例:
 * 输入: head = [1,2,3,4,5], k = 2
 * 输出: [4,5,1,2,3]
 */
public class Problem62_RotateList {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        
        // 计算链表长度
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // 计算实际需要移动的位置
        k = k % length;
        if (k == 0) return head;
        
        // 找到新的尾节点
        int stepsToNewTail = length - k;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
        
        // 重新连接
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        
        return newHead;
    }
}
