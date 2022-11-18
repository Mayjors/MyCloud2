package com.example.algorithm.simple;

public class ArrayTest {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,2,3};
        int a = removeElement(nums, 3);
        System.out.println(a);
    }

    /**
     * leetcode27
     * 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
//        int ans = 0;
//        for (int num : nums) {
//            if (num != val) {
//                nums[ans] = num;
//                ans++;
//            }
//        }
        int ans = nums.length;
        int i=0;
        while (i<ans) {
            if (nums[i] == val) {
                nums[i] = nums[ans-1];
                ans--;
            } else {
                i++;
            }
        }
        return ans;
    }

    /**
     * leetcode26
     *  删除有序数组中的重复项
     *  给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }
}
