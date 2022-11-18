package com.example.algorithm.zijie;

import java.util.*;

public class ListTest {
    public static void main(String[] args) {
        findKthLargest(new int[]{3,2,1,5,6,4}, 2);
    }

    /**
     * 两数相加
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode cur = prev;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 如果l1!= null,取他的值，等于null 赋值0，保持两个链表具有相同位数
            int x = l1 != null ? l1.val :0;
            int y = l2 != null ? l2.val :0;
            // 将两个链表的值，相加，并加上进位数
            int sum = x+y+carry;
            carry = sum / 10;
            // 计算两数和，此时排除超过10的情况
            sum = sum %10;
            // 将求和数赋值给新链表的节点
            // 注意这时候不能直接将sum赋值给cur.next
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 如果最后两位数，相加时有进位数，将进位数赋予链表的新节点
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return prev.next;
    }

    /**
     * 链表排序
     * 剑指 Offer II 077. 链表排序
     * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return null;
    }

    /**
     * 三数之和
     * 15. 三数之和
     * 判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 响应国家号召，生三胎，领百万大奖
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length <3) {
            return list;
        }
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++) {
            // 如果老大都大于0, 后面的都大于0
            if (nums[i] >0) break;
            int first = nums[i];    // 老大出列，站好别动
            // 老大想再往后占个位，多领一次奖，这不行
            if (i>0 && nums[i] == nums[i-1]) continue;

            // 画个圈 让各家老二在里面呆着
            Set<Integer> set = new HashSet<>();
            for (int j = i+1; j< nums.length; j++) {
                // 老三出列，一会你和老大一块到圈里找老二
                int third = nums[j];
                int second = - (first + third);
                // 找到老二，记到中奖名单上
                if (set.contains(second)) {
                    list.add(new ArrayList<>(Arrays.asList(first, second, third)));
                    // 老三想多领奖， 不行
                    while (j<nums.length-1 && nums[j] == nums[j+1]) j++;
                }
                set.add(third);
            }
        }
        return list;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        // 枚举a
        for (int first = 0; first<n; first++) {
            // 需要和上次枚举的数不相同
            if (first>0 && nums[first] == nums[first-1]) {
                continue;
            }
            // c 对应的指针初始指向数组最后端
            int third = n-1;
            int target = -nums[first];
            // 枚举b
            for (int second = first+1; second<n; ++second) {
                // 需要和上次的枚举数不相同
                if (second > first+1 && nums[second] == nums[second-1]) {
                    continue;
                }
                // 需要保证b的指针在c的指针左侧
                while (second<third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着b后续的增加
                // 就不会满足 a+b+c=0 且 b<c的c了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[first]);
                    l.add(nums[second]);
                    l.add(nums[third]);
                    list.add(l);
                }
            }
        }
        return list;
    }

    /**
     * 剑指 Offer II 076. 数组中的第 k 大的数字
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        for (int i= heapSize/2; i>=0; i--) {
            // 建立堆
            maxHeap(nums, i, heapSize);
        }
        for (int i= nums.length-1; i>= nums.length -k +1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeap(nums, 0, heapSize);
        }
        return nums[0];
    }
    public static void maxHeap(int[] a, int i, int heapSize) {
        int temp = a[i];
        for (int k = i*2 +1; k < heapSize; k= k*2+1) {
            if (k+1 < heapSize && a[k] < a[k+1]) {
                k++;
            }
            if (a[k] > temp) {
                a[i] = a[k];
                i=k;
            } else {
                break;
            }
        }
        a[i] = temp;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
