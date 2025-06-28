package com.example.algorithm.offer2;

public class Zifu {
    
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len; i > 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[len +1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 14.最长公共前缀
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            int j = 0;
            for (; j < prefix.length() && j < strs[i].length(); j++) {
                if (prefix.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            prefix = prefix.substring(0, j);
            if (prefix.equals("")) {
                return "";
            }
        }
        return prefix;
    }

    public void decimalToBinary(int num) {
        StringBuilder binaryString = new StringBuilder();
        int remainder = num % 2;
        binaryString.append(remainder);
        num = num / 2;
        while (num > 0) {
            remainder = num % 2;
            binaryString.append(remainder);
            num = num / 2;
        }
        System.out.println(binaryString.reverse().toString());
    }

}
