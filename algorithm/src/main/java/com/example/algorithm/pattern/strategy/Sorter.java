package com.example.algorithm.pattern.strategy;

public class Sorter {

    public void sort(Cat[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
//                if (arr[j] > arr[j+1]) {
//                    swap(arr, j, j+1);
//                }
                if (arr[j].compareTo(arr[j+1]) == 1) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private static void swap(Cat[] arr, int i, int j) {
        Cat tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
