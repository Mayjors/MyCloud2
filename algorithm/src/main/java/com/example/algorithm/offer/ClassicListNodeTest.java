package com.example.algorithm.offer;

public class ClassicListNodeTest {
    public static void main(String[] args) {

    }

    /**
     * 反转链表2
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }
        ListNode rightNode = pre;
        for (int i = 0; i < right- left + 1; i++) {
            rightNode = rightNode.next;
        }
        // 切断一个子链表(截断链表)
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        pre.next = null;
        rightNode.next = null;


        reverse(leftNode);

        pre.next = rightNode;
        leftNode.next = curr;
        return dummy.next;
    }

    public static ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right- left; i++) {
            next = cur.next;
            // 当前节点指向后续节点的的后续
            cur.next = pre.next;
            // 后续节点指向前驱节点的后续
            next.next = pre.next;
            // 前驱节点指向当前节点的后续
            pre.next = next;
        }
        return dummy.next;
    }

    /**
     * K个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next !=null) {
            for (int i = 0; i < k && end!= null; i++) {
                end = end.next;
            }
            if (end == null) break;
            // 记录原始未反转段的起始节点
            ListNode start = pre.next;
            // 记录下一个截断的起始点
            ListNode next = end.next;
            // 为了进行后面的反转操作，断开此处链接，让后面反转操作知道截断点在哪
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    /**
     * 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists( ListNode list1, ListNode list2 ){
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public ListNode mergeTwoLists2( ListNode list1, ListNode list2 ){
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        while (list1!= null && list2!= null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }
        pre.next = list1 == null? list2 : list1;
        return dummy.next;
    }

    /**
     * 环形链表
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 相交链表
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : b.next;
            b = b == null? headA : a.next;
        }
        return a;
    }

    /**
     * 环形链表
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * 重排链表
     * @param head
     * @return
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast!= null && fast.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode pre = slow.next;
        slow.next = null;

        pre = reverse(pre);

        while (pre!= null) {
            ListNode next = pre.next;
            pre.next = head.next;
            head.next = pre;
            head = pre.next;
            pre = next;
        }
//        fast = pre;
//        while (pre!= null) {
//            ListNode next = pre.next;
//            pre.next = slow;
//            slow = pre;
//            pre = next;
//        }
//        while (fast!= null) {
//            ListNode next = fast.next;
//            fast.next = head.next;
//            head.next = fast;
//            head = fast.next;
//            fast = next;
//        }
    }

    /**
     * 删除链表倒数第n个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd( ListNode head, int n ){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next!= null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
