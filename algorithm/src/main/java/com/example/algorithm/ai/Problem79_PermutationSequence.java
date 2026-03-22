package com.example.algorithm.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * 79. 第 k 个排列
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记。
 * 给定 n 和 k，返回第 k 个排列。
 * 
 * 示例:
 * 输入: n = 3, k = 3
 * 输出: "213"
 */
public class Problem79_PermutationSequence {
    
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
            factorial[i] = factorial[i - 1] * i;
        }
        
        k--; // 转换为 0-based
        StringBuilder sb = new StringBuilder();
        
        for (int i = n; i >= 1; i--) {
            int index = k / factorial[i - 1];
            sb.append(numbers.get(index));
            numbers.remove(index);
            k %= factorial[i - 1];
        }
        
        return sb.toString();
    }
}
