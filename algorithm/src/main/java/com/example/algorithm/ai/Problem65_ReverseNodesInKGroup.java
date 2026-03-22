package com.example.algorithm.ai;

/**
 * 65. K 个一组翻转链表
 * 给你链表的头节点 head，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 
 * 示例:
 * 输入: head = [1,2,3,4,5], k = 2
 * 输出: [2,1,4,3,5]
 */
public class Problem65_ReverseNodesInKGroup {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (true) {
            // 检查是否有 k 个节点
            ListNode tail = prev;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) return dummy.next;
            }
            
            // 翻转 k 个节点
            ListNode next = tail.next;
            ListNode[] reversed = reverse(prev.next, k);
            
            // 重新连接
            prev.next = reversed[0];
            reversed[1].next = next;
            prev = reversed[1];
        }
    }
    
    private ListNode[] reverse(ListNode head, int k) {
        ListNode prev = null;
        ListNode current = head;
        for (int i = 0; i < k; i++) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return new ListNode[]{prev, head};
    }
}
