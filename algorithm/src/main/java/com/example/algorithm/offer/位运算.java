package com.example.algorithm.offer;

public class 位运算 {
    public static void main(String[] args) {

    }


    /**
     * 136.只出现一次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }


    /**
     * 191.位1的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int cur = 0;
//        while (n !=0) {
//            n = n & (n-1);
//            cur++;
//        }
        while (n!= 0) {
            cur += n & 1;
            n>>>=1;
        }
        return cur;
    }

}