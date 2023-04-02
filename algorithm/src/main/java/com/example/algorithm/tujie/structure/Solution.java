package com.example.algorithm.tujie.structure;

import com.example.algorithm.tujie.RandomNode;
import com.example.algorithm.tujie.ListNode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println(replaceSpace("We are happy."));
    }

    /**
     * 剑指 Offer 05. 替换空格
     * @param s
     * @return
     */
    public static String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c == ' ') res.append("%20");
            else res.append(c);
        }
        return res.toString();
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * @param head
     * @return
     */
    public static int[] reversePrint(ListNode head) {
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            ++len;
            temp = temp.next;
        }
        int[] res = new int[len];
        temp = head;
        int index = len-1;
        while (temp!=null) {
            res[index--] = temp.val;
            temp = temp.next;
        }
        return res;
    }

    public static int[] reversePrint2(ListNode head) {
        List<Integer> temp = new ArrayList<>();
        recur(head, temp);
        int[] res = new int[temp.size()];
        for (int i=0; i< res.length; i++) {
            res[i] = temp.get(i);
        }
        return res;
    }
    private static void recur(ListNode head, List<Integer> list) {
        if (head == null) return;
        recur(head.next, list);
        list.add(head.val);
    }

    /**
     * 剑指 Offer 24. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * @param head
     * @return
     */
    public RandomNode copyRandomList(RandomNode head) {
        if (head == null) return null;
        RandomNode cur = head;
        Map<RandomNode, RandomNode> map = new HashMap<>();
        // 复制各个节点，并建立"原节点->新节点"的Map映射
        while (cur != null) {
            map.put(cur, new RandomNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        // 构建新链表的next和random指向
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder res = new StringBuilder();
        for (int i=n; i<s.length(); i++) {
            res.append(s.charAt(i));
        }
        for (int i=0; i<n; i++) {
            res.append(s.charAt(i));
        }
        return res.toString();
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int j=0, i=1-k; j<nums.length; i++, j++) {
            // 删除deque中对应的nums[i-1]
            if (i>0 && deque.peekFirst() == nums[i-1]) {
                deque.removeFirst();
            }
            // 保持deque递减
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.addLast(nums[j]);
            if (i>=0) {
                res[i] = deque.peekFirst();
            }
        }
        return res;
    }

}
