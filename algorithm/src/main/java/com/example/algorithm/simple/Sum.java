package com.example.algorithm.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sum {
    public static void main(String[] args) {
        int[] s = {2, 15, 11, 7};
        int[] ss = twoSum2(s, 9);
        System.out.println(Arrays.toString(ss));
    }

    /**
     * 两数之和
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> ha = new HashMap<Integer, Integer>();
        for (int i=0; i<nums.length; i++) {
            if (ha.containsKey(target-nums[i])) {
                return new int[]{ha.get(target-nums[i]), i};
            }
            ha.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 两数相加
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry >0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

}

