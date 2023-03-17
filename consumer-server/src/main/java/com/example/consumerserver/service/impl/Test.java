package com.example.consumerserver.service.impl;

public class Test {
    public static void main(String[] args) {
        hexTo("12", 16, 2);
        hexTo("15", 10, 16);

        long tmp = N2teen("12", 16);
        String tmp2 = teen2N(tmp, 2);

        String newStr = tmp2.replaceFirst("^0*", "");
        System.out.println(newStr);

        System.out.println("xxxxxxxx");
        findPrime(100);

    }

    public static void hexTo(String str, int hexM, int hexN) {
        System.out.println(Integer.parseInt(str, hexM));
        System.out.println(Integer.toString(Integer.parseInt(str, hexM), hexN));
    }

    private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();
    private static String numStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 其它进制转为10进制
     *
     * @param str
     * @param m
     * @return
     */
    public static long N2teen(String str, int m) {
        char[] ch = str.toCharArray();
        int len = ch.length;
        long result = 0;
        if (m == 10) return Long.parseLong(str);
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= m;
        }
        return result;
    }

    /**
     * 十进制转其它进制
     *
     * @param number
     * @param n
     * @return
     */
    public static String teen2N(long number, int n) {
        StringBuilder result = new StringBuilder();
        while (number != 0) {
            result.insert(0, array[Long.valueOf((number % n)).intValue()]);
            number /= n;
        }
        return result.length() == 0 ? "0" : result.toString();
    }

    /**
     * 查找质数
     * @param maxn
     */
    public static void findPrime(int maxn) {
        boolean[] judge = new boolean[maxn];
        int pNum = 0;

        for (int i=0; i<maxn; i++) {
            judge[i] = false;
        }
        for (int i=2; i<maxn; i++) {
            if (!judge[i]) {
              pNum++;
              for (int j=i+i; j<maxn; j+=i) {
                  judge[j] = true;
              }
              System.out.println(i);
            }
        }
        System.out.println(pNum);
    }
}