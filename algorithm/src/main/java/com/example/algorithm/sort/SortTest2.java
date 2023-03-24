package com.example.algorithm.sort;

import java.util.Arrays;

public class SortTest2 {
    public static void main(String[] args) {
        int[] arr = {4, 6, 5, 3, 2, 1};
//        bubbleSort2(arr);
//        selectSort(arr);
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] nums) {
        for (int i=0; i<nums.length-1; i++) {
            for (int j=0; j<nums.length-1-i; j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums, j, j+1);
                }
            }
        }
    }

    public static void bubbleSort2(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length-1-i; j++) {
                if (nums[j]>nums[j+1]) {
                    swap(nums, j, j+1);
                    flag=false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public static void selectSort(int[] nums) {
        for (int i=0; i<nums.length-1; i++) {
            int index = i;
            for (int j=i+1; j<nums.length; j++) {
                if (nums[j] < nums[index]) {
                    index = j;
                }
            }
            if (index != i) {
                swap(nums, i, index);
            }
        }
    }

    public static void insertSort(int[] nums) {
        for (int i=1; i<nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                int temp = nums[i];
                int j;
                for (j=i-1; j>=0&&temp < nums[j];j--) {
                    nums[j+1] = nums[j];
                }
                nums[j+1] = temp;
            }
        }
    }

    public static void shellSort(int[] nums) {
        for (int gap = nums.length/2; gap >0; gap = gap/2) {
            for (int i= gap; i<nums.length; i++) {
                for (int j=i-gap; j>=0; j = j-gap) {
                    if (nums[j] > nums[j+gap]) {
                        swap(nums, j, j+gap);
                    }
                }
            }
        }
    }

    public static void quickSort(int[] nums, int left, int right) {
        int i, j;
        if (left > right) return;
        i = left;
        j = right;
        int temp = nums[left];
        while (i<j) {
            while (temp <= nums[j] && i<j) {
                j--;
            }
            while (temp >= nums[i] && i<j) {
                i++;
            }
            if (i<j) {
                swap(nums, i, j);
            }
        }
        nums[left] = nums[i];
        nums[i] = temp;
        quickSort(nums, left, j-1);
        quickSort(nums, j+1, right);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
