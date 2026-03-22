package com.example.algorithm.ai;

/**
 * 12. 反转链表
 * 给你单链表的头节点 head，请你反转链表，并返回反转后的链表。
 * 
 * 示例:
 * 输入: head = [1,2,3,4,5]
 * 输出: [5,4,3,2,1]
 */
public class Problem12_ReverseLinkedList {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    // 迭代法
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        return prev;
    }
    
    // 递归法
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
}
