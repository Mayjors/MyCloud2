package com.example.algorithm.zijie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringTest {
    public static void main(String[] args) {
        int a = lengthOfLongestSubstring("abcbd");
        System.out.println(a);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = swapPairs(node1);
        System.out.println(node);
    }

    /**
     * 无重复字符的最长子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int end = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int ans =0, n = s.length();
        Set<Character> set = new HashSet<>();
        Integer left = 0, right = 0;
        while (right<n) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            set.add(s.charAt(right++));
            ans = Math.max(ans, right-left);
        }
        return ans;
    }

    /**
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode a = temp.next;
            ListNode b = temp.next.next;
            temp.next = b;
            a.next = b.next;
            b.next = a;
            temp = a;
        }
        return pre.next;
    }
}
