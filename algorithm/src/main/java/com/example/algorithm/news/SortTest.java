package com.example.algorithm.news;

import com.example.algorithm.offer.排序;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        int[] nums = new int[]{7,1,3,5,1,6,8,1,3,5,7,5,6};
//        selectSort(nums);
//        bubbleSort(nums);
//        insertSort2(nums);
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 插入排序，每次和前面的比较，进行排序
     * @param nums
     */
    public static void insertSort(int[] nums) {
        if (nums == null || nums.length <=2) {
            return;
        }
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int newNumIndex = i;
            while (newNumIndex -1 >= 0 && nums[newNumIndex - 1] > nums[newNumIndex]) {
                swap(nums, newNumIndex-1, newNumIndex);
                newNumIndex--;
            }
        }
    }

    public static void insertSort2(int[] nums) {
        if (nums == null || nums.length <=2) {
            return;
        }
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >=0 && nums[j] > nums[j +1]; j--) {
                swap(nums, j, j+1);
            }
        }
    }

    /**
     * 冒泡算法
     * 每次计算 0-n中，前两个的大小，得到最大的那个为n
     * @param nums
     */
    public static void bubbleSort(int[] nums) {
        int len = nums.length;
//        for (int i = len - 1; i >=0; i--) {
//            for (int j = 1; j<=i; j++) {
//                // 第一个数比第二个大， 就交换
//                if (nums[j -1] > nums[j]) {
//                    swap(nums, j-1, j);
//                }
//            }
//        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len -1-i; j++) {
                if (nums[j+1] < nums[j]) {
                    swap(nums, j+1, j);
                }
            }
        }
    }

    /**
     * 选择算法
     * @param nums
     */
    public static void selectSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < len; j++) {
                minValueIndex = nums[j] < nums[minValueIndex] ? j : minValueIndex;
            }
            swap(nums, i, minValueIndex);
        }
    }

    public static void quickSort(int[] nums) {
        quickSortLomuto(nums, 0, nums.length - 1);
    }

    /**
     * 快速排序主函数
     * @param nums
     * @param low
     * @param hight
     */
    public static void quickSortLomuto(int[] nums, int low, int hight) {
        if (low < hight) {
            int pi = partitionLomuto(nums, low, hight);
            quickSortLomuto(nums, low, pi -1);
            quickSortLomuto(nums, pi + 1, hight);
        }
    }

    /**
     * 选择最右元素作为基准(pivot)
     * 将所有小于基准的元素放到数组左侧
     * 最后将基准放到正确的位置并返回该索引
     * @param nums
     * @param low
     * @param hight
     * @return
     */
    private static int partitionLomuto(int[] nums, int low, int hight) {
        int pivot = nums[hight];

        // i指向比基准小的区域的最后一个元素
        // 初始化low -1，表示小元素区域开始时为空
        int i = low -1;
        // j从左到右遍历数组
        for (int j = low; j < hight; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, hight);
        return i +1;
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
