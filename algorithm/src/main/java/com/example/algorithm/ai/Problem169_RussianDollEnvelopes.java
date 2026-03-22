package com.example.algorithm.ai;

/**
 * 169. 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes，其中 envelopes[i] = [wi, hi]，
 * 表示第 i 个信封的宽度和高度。当另一个信封的宽度和高度都比这个信封大的时候，
 * 这个信封就可以放进另一个信封里。请计算最多能有多少个信封能组成一组"俄罗斯套娃"信封。
 * 
 * 示例:
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 */
public class Problem169_RussianDollEnvelopes {
    
    public int maxEnvelopes(int[][] envelopes) {
        // 按宽度升序，宽度相同时按高度降序
        java.util.Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        
        // 在高度上找 LIS
        int[] dp = new int[envelopes.length];
        int len = 0;
        
        for (int[] envelope : envelopes) {
            int left = 0, right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (dp[mid] < envelope[1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            dp[left] = envelope[1];
            if (left == len) len++;
        }
        
        return len;
    }
}
