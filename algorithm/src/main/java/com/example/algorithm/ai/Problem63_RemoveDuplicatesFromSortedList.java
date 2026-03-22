package com.example.algorithm.ai;

/**
 * 63. 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head，删除所有重复的元素，使每个元素只出现一次。
 * 
 * 示例:
 * 输入: head = [1,1,2]
 * 输出: [1,2]
 */
public class Problem63_RemoveDuplicatesFromSortedList {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return head;
    }
    
    // 删除所有重复的节点（保留不重复的）
    public ListNode deleteDuplicatesAll(ListNode head) {
        if (head == null) return null;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head;
        
        while (current != null) {
            boolean isDuplicate = false;
            while (current.next != null && current.val == current.next.val) {
                isDuplicate = true;
                current = current.next;
            }
            
            if (isDuplicate) {
                prev.next = current.next;
            } else {
                prev = prev.next;
            }
            current = current.next;
        }
        
        return dummy.next;
    }
}
