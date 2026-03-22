package com.example.algorithm.ai;

/**
 * 64. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x，请你对链表进行分隔，
 * 使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * 
 * 示例:
 * 输入: head = [1,4,3,2,5,2], x = 3
 * 输出: [1,2,2,4,3,5]
 */
public class Problem64_PartitionList {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode smallPtr = small;
        ListNode largePtr = large;
        
        while (head != null) {
            if (head.val < x) {
                smallPtr.next = head;
                smallPtr = smallPtr.next;
            } else {
                largePtr.next = head;
                largePtr = largePtr.next;
            }
            head = head.next;
        }
        
        largePtr.next = null;
        smallPtr.next = large.next;
        
        return small.next;
    }
}
