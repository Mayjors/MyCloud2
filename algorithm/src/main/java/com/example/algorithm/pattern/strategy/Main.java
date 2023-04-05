package com.example.algorithm.pattern.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        test();
        Cat[] a = {new Cat(3, 3), new Cat(5, 5), new Cat(1, 1)};
        Sorter sorter = new Sorter();
        sorter.sort(a);
        System.out.println(Arrays.toString(a));

        Sorter2 sorter2 = new Sorter2();
        sorter2.sort(a, new CatWeightComparator());
        System.out.println(Arrays.toString(a));
    }

    private static void test() {
        int[] a = {9, 2, 3, 5, 7, 1, 4};
        DefaultSorter sorter = new DefaultSorter();
        sorter.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
