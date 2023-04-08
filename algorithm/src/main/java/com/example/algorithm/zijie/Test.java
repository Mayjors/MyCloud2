package com.example.algorithm.zijie;

public class Test {
    public static void main(String[] args) {


    }





    /**
     * x 的平方根
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 0 ) return 0;
        if (x == 1) return 1;
        int left = 1;
        int right = x /2;
        while (left < right) {
            int mid = left + (right -left + 1) /2;
            if (mid > x/mid) {
                // 下轮的搜索区为[left, mid-1]
                right = mid-1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * x 的平方根
     * 牛顿迭代法 x + a/x >= 2
     * @param x
     * @return
     */
    public static int mySqrt2(int x) {
        long a = x;
        while (a * a > x ) {
            a = (x + x/a) /2;
        }
        return (int) a;
    }
}
