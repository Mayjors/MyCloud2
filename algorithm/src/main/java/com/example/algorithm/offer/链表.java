package com.example.algorithm.offer;

import com.example.algorithm.base.List;

import com.example.algorithm.base.ArrayList;
import com.example.algorithm.base.List;

import static com.example.algorithm.offer.ClassicListNodeTest.reverse;

public class 链表 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        System.out.println(reverseBetween(listNode, 2, 4));
    }

    /**
     * 234.回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            vals.add(cur.val);
            cur = cur.next;
        }
        // 使用双指针判断是否回文
        int left = 0, right = vals.size() - 1;
        while (left < right) {
            if (!vals.get(left).equals(vals.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }
        // 找到前半部分链表的尾结点并反转后半部分链表
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        slow.next = reverse(slow.next);
        while (fast != slow) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
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

    public ListNode partition3( ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode l = dummy1, r = dummy2;
        while (head!= null) {
            if (head.val < x) {
                l.next = head;
                l = l.next;
            } else {
                r.next = head;
                r = r.next;
            }
            head = head.next;
        }
        l.next = dummy2.next;
        r.next = null;
        return dummy1.next;
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

    public static ListNode partition2( ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode pre1 = dummy1;
        ListNode pre2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                pre1.next = head;
                pre1 = pre1.next;
            } else {
                pre2.next = head;
                pre2 = pre2.next;
            }
            head = head.next;
        }
        pre1.next = dummy2.next;
        pre2.next = null;
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
     * 61.旋转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//        ListNode pre = dummy;
//        for (int i = 0; i< k; i++) {
//            pre = pre.next;
//        }
//        // head指向需要反转的链表节点
//        head = pre.next;
//        // 开始反转，每次需要改变三个节点的引用关系，同时已经反转了的节点的关系不变
//        for (int i = 0; i < k;i++) {
//            ListNode next = head.next;
//            head.next = next.next;
//            next.next = pre.next;
//            pre.next = next;
//        }
//        return dummy.next;
        if (head == null || k ==0) {
            return head;
        }
        int n = 0;
        ListNode tail = null;
        for (ListNode p = head; p != null; p = p.next) {
            n++;
            tail = p;
        }
        k = k % n;
        ListNode p = head;
        for (int i = 0; i < n - k - 1; i++) {
            p = p.next;
        }
        ListNode newHead = p.next;
        p.next = null;
        tail.next = head;
        return newHead;
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        while (listNode!= null) {
            res.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> ress = new ArrayList();
        for(int i = res.size()-1; i >= 0; i--){
            ress.add(res.get(i));
        }
        return res;
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
        // n1 →…→ nk−1 →nk →nk+1 ←…←nm
        // 我们希望 nk+1的下一个节点指向 nk,所以nk.next.next = nk
        ListNode next = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }


    /**
     * 92.反转链表 II
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
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
     * 24.两两交换链表中的节点
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next!= null && pre.next.next!= null) {
            ListNode cur = pre.next;
            ListNode next = cur.next;
            pre.next = next;
            cur.next = next.next;
            next.next = cur;
            pre = cur;
        }
        return dummy.next;
    }

    /**
     * 24.两两交换链表中的节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode temp = head.next;
//        head.next = swapPairs(temp.next);
//        temp.next = head;
//        return temp;
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
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
    public ListNode deleteDuplicates(ListNode head) {
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

    /**
     * 82.删除排序链表中的重复元素 II
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (head != null) {
            if (head.next == null || head.val != head.next.val) {
                tail.next = head;
                tail = head;
            }
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            head = head.next;
        }
        tail.next = null;
        return dummy.next;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (tail.next != cur) {
                tail.next = cur;
                tail = cur;
            } else {
                tail = tail.next;
            }
            cur = cur.next;
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

    /**
     * 143.重排链表
     * @param head
     */
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

    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        // 存到list中
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        // 头尾指针依次取元素
        int i = 0, j = list.size() -1;
        while (i < j ) {
            list.get(i).next = list.get(j);
            i++;
            // 偶数个节点的情况，会提前相遇
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

}