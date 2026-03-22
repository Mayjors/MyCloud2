package com.example.algorithm.ai;

/**
 * 95. 两个有序数组的中位数（简化版）
 * 更直观的二分查找实现
 * 
 * 示例:
 * 输入: nums1 = [1,3], nums2 = [2]
 * 输出: 2.00000
 */
public class Problem95_MedianOfTwoSortedArrays {
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 确保 nums1 是较短的数组
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;
        
        int left = 0, right = m;
        
        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = totalLeft - i;
            
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }
        
        int i = left;
        int j = totalLeft - i;
        
        int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];
        
        if ((m + n) % 2 == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (Math.max(nums1LeftMax, nums2LeftMax) + 
                   Math.min(nums1RightMin, nums2RightMin)) / 2.0;
        }
    }
}
