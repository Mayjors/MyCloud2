package com.example.algorithm.ai;

/**
 * 15. 环形链表
 * 给你一个链表的头节点 head，判断链表中是否有环。
 * 
 * 示例:
 * 输入: head = [3,2,0,-4], pos = 1
 * 输出: true
 */
public class Problem15_LinkedListCycle {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; next = null; }
    }
    
    // 快慢指针法
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return true;
    }
    
    // 找到环的入口点
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        // 判断是否有环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // 找到环的入口
                ListNode entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        
        return null;
    }
}
