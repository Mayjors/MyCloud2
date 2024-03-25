package com.example.algorithm.offer;

import java.util.HashMap;
import java.util.Map;

public class 排序 {

    /**
     * 88.合并两个有序数组
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n;
        while (n > 0) {
            if (m > 0 && nums1[m-1] > nums2[n-1]) {
                nums1[i-1] = nums1[m-1];
                m--;
            } else {
                nums1[i-1] = nums2[n-1];
                n--;
            }
            i--;
        }
    }

    /**
     * 147.对链表进行插入排序
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return head;
        // 虚拟头结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 已排序部分的最后一个节点
        ListNode lastSorted = head;
        // 未排序部分的第一个节点
        ListNode cur = head.next;
        while (cur!= null) {
            if (lastSorted.val <= cur.val) {
                // 当前节点已经有序
                lastSorted = lastSorted.next;
            } else {
                // 未排序部分的第一个节点
                ListNode prev = dummy;
                while (prev.next.val <= cur.val) {
                    prev = prev.next;
                }
                lastSorted.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = lastSorted.next;
        }
        return dummy.next;
    }

    public boolean containsDuplicate(int[] nums) {
        boolean flag = false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                flag = true;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return flag;
    }
}
