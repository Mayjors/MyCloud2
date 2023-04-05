package com.example.algorithm.pattern.strategy;

public class Sorter2<T> {

    public void sort(T[] arr, Comparator<T> comparator) {
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
//                if (arr[j] > arr[j+1]) {
//                    swap(arr, j, j+1);
//                }
                if (comparator.compare(arr[j], arr[j+1]) == 1) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
