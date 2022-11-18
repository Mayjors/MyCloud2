package com.example.algorithm.simple;

public class ListSolution {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode node = reversal2(node1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    /**
     * 反转数组
     * @param head
     * @return
     */
    public static ListNode reversal(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode reversal2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reversal2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


}
