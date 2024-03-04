package com.example.algorithm.offer;

import com.example.algorithm.base.List;

public class 链表 {
    public static void main(String[] args) {

    }


    /**
     * 21.合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists3(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }

    /**
     * 109. 有序链表转换二叉搜索树
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode fast = head, slow = head, pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;

    }

    public ListNode oddEvenList1( ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even!= null && even.next!= null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 328. 奇偶链表
     * @param head
     * @return
     */
    public ListNode oddEvenList( ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next;
        while (even!= null && even.next!= null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = null;
        return head;
    }


    /**
     * 876. 链表的中间结点
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        return slow;
    }

    /**
     * 86. 分隔链表
     * @param head
     * @param x
     * @return
     */
    public ListNode partition( ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode pre1 = dummy1;
        ListNode pre2 = dummy2;
        while (head != null) {
            ListNode tmp = head.next;
            // 大于等于x的节点
            if (head.val >= x) {
                pre2.next = head;
                pre2 = pre2.next;
            } else {
                pre1.next = head;
                pre1 = pre1.next;
            }
            head.next = null;
            head = tmp;
        }
        pre1.next = dummy2.next;
        return dummy1.next;
    }


    /**
     *  22. 链表中倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd( ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 0) {
            return head;
        }
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast!= null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    public static ListNode addTwoNumbers2( ListNode l1, ListNode l2 ) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum %10);
            node.next = pre.next;
            pre.next = node;
            pre = node;
        }
        if (carry != 0) {
            pre.next = new ListNode(carry);
        }
        return dummy.next;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        count /= k;
        head = pre.next;
        for (int i = 0; i < count; i++) {
            //内层循环对当前轮次范围内的部分链表节点进行反转，这一部分的思路同 Leetcode 92. 反转链表 II
            //注意，k个组的节点，需要翻转k-1次，因此这里的j从1开始
            for (int j = 0; j < k; j++) {
                ListNode next = head.next;
                head.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            pre = head;
            head = head.next;
        }
        return dummy.next;
    }

    /**
     * 合并两个排序的链表
     *
     * @param list1
     * @param list2
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }
        pre.next = list1 == null ? list2 : list1;
        return dummy.next;
    }


    /**
     * 141. 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * https://leetcode-cn.com/problems/linked-list-cycle/
     * 简单
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 206. 反转链表
     * 反转一个单链表。
     * https://leetcode-cn.com/problems/reverse-linked-list/
     * 简单
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
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

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // head指向需要反转的链表节点
        head = pre.next;
        // 开始反转，每次需要改变三个节点的引用关系，同时已经反转了的节点的关系不变
        for (int i = 0; i < right - left; i++) {
            // 获取当前需要反转的节点的后续
            ListNode next = head.next;
            // 当前节点的后续指向后续节点的后续
            head.next = next.next;
            // 后续节点的后续指向前驱节点的后续
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n ){
        ListNode slow = head, fast = head;
        while (n-- >0) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 删除倒数第n个节点
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     */
    public ListNode deleteDuplicates(ListNode head ){
        if (head == null) return null;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head ){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                pre.next = head.next;
            } else {
                pre = pre.next;
            }
            head = head.next;
        }
        return dummy.next;
    }

    /**
     * 2. 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }


    /**
     * 160. 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode mid = getMiddleNode(head);
        ListNode right = reverseList(mid.next);
        mid.next = null;
        mergeList(head, right);
    }

    private ListNode getMiddleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private void mergeList(ListNode left, ListNode right) {
        while (left != null && right != null) {
            ListNode next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
    }


}