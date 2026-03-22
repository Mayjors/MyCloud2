package com.example.algorithm.ai;

/**
 * 11. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 
 * 示例:
 * 输入: head = [1,2,3,4,5], n = 2
 * 输出: [1,2,3,5]
 */
public class Problem11_RemoveNthNodeFromEnd {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // fast 先走 n+1 步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // fast 和 slow 同时走
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // 删除倒数第 n 个节点
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}
