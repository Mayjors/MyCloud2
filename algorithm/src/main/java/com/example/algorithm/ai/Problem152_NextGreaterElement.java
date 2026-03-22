package com.example.algorithm.ai;

/**
 * 152. 下一个更大元素
 * 给定两个没有重复元素的数组 nums1 和 nums2，其中 nums1 是 nums2 的子集。
 * 找到 nums1 中每个元素在 nums2 中的下一个更大元素。
 * 
 * 示例:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * 输出: [-1,3,-1]
 */
public class Problem152_NextGreaterElement {
    
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = -1;
            boolean found = false;
            for (int num : nums2) {
                if (num == nums1[i]) found = true;
                if (found && num > nums1[i]) {
                    result[i] = num;
                    break;
                }
            }
        }
        return result;
    }
    
    // 优化：单调栈
    public int[] nextGreaterElementOptimized(int[] nums1, int[] nums2) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return result;
    }
}
