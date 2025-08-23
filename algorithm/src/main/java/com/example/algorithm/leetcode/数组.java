package com.example.algorithm.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 数组 {
    public static void main(String[] args) {

    }

    /**
     * 1.两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i< nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j]  == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    public List<List<Integer>> twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            int leftVal = nums[left];
            int rightVal = nums[right];
            if (sum == target) {
                result.add(new ArrayList<>(Arrays.asList(nums[left], nums[right])));
                while (left < right && nums[left] == leftVal) {
                    left++;
                }
                while (left < right && nums[right] == rightVal) {
                    right--;
                }
            } else if (sum < target) {
                while (left < right && nums[left] == leftVal) {
                    left++;
                }
            } else {
                while (left < right && nums[right] == rightVal) {
                    right--;
                }
            }
        }
        return result;
    }

}
