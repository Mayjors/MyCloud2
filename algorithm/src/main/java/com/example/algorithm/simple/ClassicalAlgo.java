package com.example.algorithm.simple;

/**
 * 十大经典算法
 */
public class ClassicalAlgo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 111);
        System.out.println(index);
    }

    /**
     * 二分查找
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        while (left <= right) {
            int mid = (left + right) /2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid -1;
            } else {
                left = mid +1;
            }
        }
        return -1;
    }


}
