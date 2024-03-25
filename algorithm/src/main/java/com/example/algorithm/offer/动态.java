package com.example.algorithm.offer;

import java.util.Arrays;

public class 动态 {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn2(new int[]{3, 4, 2}));
        System.out.println(longestPalindrome("babad"));
    }

    /**
     * 64. 最小路径和
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) {
                    grid[i][j] = grid[i][j-1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j-1] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    /**
     * 最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int begin = 0, max =1;

        int len = s.length();
        char[] charArray = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        //这里将单字符的判断放到主循环中了，即更改了j和i的取值，使其能判断到单字符
        for(int j = 0; j < len; j++) {
            for(int i = 0; i <= j; i++) {
                if(charArray[i] == charArray[j]) {		// 不用对dp数组填充false
                    if(j - i < 3) dp[i][j] = true;
                    else dp[i][j] = dp[i + 1][j - 1];
                }

                if(dp[i][j] && j - i + 1 > max) {
                    begin = i;
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    /**
     * 740.删除并获得点数
     * @param nums
     * @return
     */
    public static int deleteAndEarn2(int[] nums) {
        int[] help = new int[10001];
        int limit = 0;
        for (int i : nums) {
            help[i] += i;
            limit = Math.max(limit,i);
        }
        for (int i = 2; i <= limit; i++) {
            help[i] = Math.max(help[i - 2] + help[i], help[i - 1]);
        }
        return help[limit];
    }

    /**
     * 01背包
     */
    public static void backpack() {
        int[] w = {1, 4, 3};    // 物品的重量
        int[] v = {1500, 3000, 2000};    // 物品的价值
        int bag = 4;    // 背包的容量
        int n = v.length; // 物品的数量

        // 创建二维数组
        // dp[i][j]表示前i个物品，体积不超过j的情况下，最大价值
        int[][] dp = new int[n + 1][bag + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        Arrays.fill(dp[0], 0);

        // 动态规划处理
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 体积不超过j
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int maxValue(int N, int C, int[] v, int[] w) {
        int[]dp = new int[C+1];
        for (int i = 0; i < N; i++) {
            for (int j = C; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j],dp[j-w[i]]+v[i]);
            }
        }
        return dp[C];
    }

    /**
     * 300. 最长递增子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }

    /**
     * 198. 打家劫舍
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    /**
     * 740. 删除并获得点数
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int n = 10001;
        int[] bucket = new int[n];
        int max = 0;
        for(int num : nums) {
            bucket[num]++;
            max = Math.max(max, num);
        }
        n = max +1;
        int[] dp = new int[n];
        dp[n - 1] = bucket[n - 1] * (n-1);
        dp[n - 2] = Math.max(bucket[n - 2] * (n -2), bucket[n - 1] * (n - 1));

        for (int num = n-3; num >= 1; num--) {
            int p1 = dp[num - 1];
            int p2 = bucket[num] * num + dp[num +2];
            dp[num] = Math.max(p1, p2);
        }
        return dp[1];
    }

    /**
     * 416.分割等和子集
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 2!= 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        Arrays.fill(dp, true);
        for(int i = 0; i < len; i++) {
            for(int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    public boolean canPartition2(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 2!= 0) return false;
        int target = sum / 2;
        int[][] dp = new int[len + 1][target + 1];
        Arrays.fill(dp[0], 1);
        for(int i = 1; i < len; i++) {
            for(int j = 1; j <= target; j++) {
                if(j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
//                    dp[i][j] = dp[i - 1][j] + dp[i][j - nums[i]];
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i]]+nums[i]);
                }
            }
        }
        return dp[len-1][target] == target;
    }

}
