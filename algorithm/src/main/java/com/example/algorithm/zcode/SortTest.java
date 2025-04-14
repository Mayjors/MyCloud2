package com.example.algorithm.zcode;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 9, 3, 7, 2, 8, 6, 5};
        selectionSort(arr);
        System.out.println("args = " + Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        for (int i =0; i< arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j<arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        for (int e = arr.length-1; e>0; e--) {
            for (int i = 0; i<e; i++) {
                if (arr[i] > arr[i+1]) {
                    swap(arr, i, i+1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
