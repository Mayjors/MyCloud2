package com.example.algorithm.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 动态 {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn2(new int[]{3, 4, 2}));
        System.out.println(longestPalindrome("babad"));
        System.out.println(String.valueOf(generateParenthesis(5)));
        System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(findMaxAverage(new int[]{5}, 1));
    }

    /**
     * 2379. 得到 K 个黑块的最少涂色次数
     * @param blocks
     * @param k
     * @return
     */
    public int minimumRecolors(String blocks, int k) {
//        int left = 0, right = 0;
//        int cnt = 0;
//        char[] chars = blocks.toCharArray();
//        while (right < k) {
//            cnt += blocks.charAt(right) == 'W' ? 1 : 0;
//            right++;
//        }
//        int res = cnt;
//        while (right < blocks.length()) {
//            cnt += blocks.charAt(right) == 'W' ? 1 : 0;
//            cnt -= blocks.charAt(left) == 'W' ? 1 : 0;
//            res = Math.min(res, cnt);
//            left++;
//            right++;
//        }
//        return res;
        char[] chars = blocks.toCharArray();
        int cnt = 0;
        for (int i = 0; i<k; i++) {
            cnt += chars[i] & 1;
        }
        int ans = cnt;
        for (int i = k; i< chars.length; i++) {
            cnt += (chars[i] & 1) - (chars[i-k] &1);
            ans = Math.min(ans, cnt);
        }
        return ans;
    }

    /**
     * 1423. 可获得的最大点数
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        // 华东窗口大小为n-k
        int windowSize = len-k;
        // 选前n-k个作为初始值
        int sum = 0;
        for (int i = 0; i< windowSize; i++) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < len; i++) {
            // 华东窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i-windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }

    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        int r = k > 0 ? k+1 : n; // 第一个窗口的右开端点
        k = Math.abs(k);
        int s = 0;
        for (int i= r-k; i < r; i++) {
            // 计算ans[0]
            s += code[i];
        }
        for (int i = 0; i< n; i++) {
            ans[i] = s;
            s += code[r%n] - code[(r-k) % n];
            r++;
        }
        return ans;
    }


    /**
     * 643. 子数组最大平均数 I
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i< k; i++) {
            sum += nums[i];
        }
        int len = nums.length;

        int max = sum;
        for (int i = k; i < len; i++) {
            sum = sum - nums[i -k] + nums[i];
            max = Math.max(max, sum);
        }
        return (double) max /k;
    }

    public static double findMaxAverage2(int[] nums, int k) {
        int maxS = Integer.MIN_VALUE;
        int s = 0;
        for (int i = 0; i< nums.length; i++) {
            // 进入窗口
            s+= nums[i];
            if (i < k -1) {
                continue;
            }
            // 更新答案
            maxS = Math.max(maxS, s);
            // 离开窗口
            s -= nums[i-k+1];
        }
        return (double) maxS /k;
    }

    /**
     * 62. 不同路径
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 42. 接雨水
     * 双指针做法
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int n = height.length;
        int res = 0;
        int left = 0, right = n - 1;
        // 左指针的左边最大高度/右指针的右边最大高度
        int leftMax = height[left], rightMax = height[right];
        // 最两边的列存不了水
        left++;
        right--;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                // 左指针的leftMax比右指针的rightMax矮
                // 说明：左指针的右边至少有一个板子 > 左指针左边所有板子
                // 根据水桶效应，保证了左指针当前列的水量决定权在左边
                // 那么可以计算左指针当前列的水量：左边最大高度-当前列高度
                res += leftMax - height[left];
                left++;
            } else {
                // 右边同理
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }

    /**
     * 22. 括号生成
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        getParenthesis("", n, n, res);
        return res;
    }

    private static void getParenthesis(String n, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(n);
            return;
        }
        if (left == right) {
            getParenthesis(n + "(", left - 1, right, res);
        } else if (left < right) {
            if (left > 0) {
                getParenthesis(n + "(", left - 1, right, res);
            }
            getParenthesis(n + ")", left, right -1, res);
        }
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

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() <2) {
            return s;
        }
        int len = s.length();
        int maxStart = 0, maxEnd = 0, maxLen = 1;
        boolean[][] dp = new boolean[len][len];
        // dp[l][r] = num[l] == num[r] && dp[l+1][r-1]
        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && ((r-l) <= 2 || dp[l+1][r-1])) {
                    dp[l][r] = true;
                    if (r - 1 + 1 > maxLen) {
                        maxLen = r -l +1;
                        maxEnd = r;
                        maxStart = 1;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd +1);
    }

    public static String longestPalindrome3(String s) {
        if (s == null || s.length() <2) {
            return s;
        }
        int len = s.length();
        int start = 0, end = 0;
        for (int i =0; i<s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            int maxLen = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (maxLen -1)/2;
                end = i + maxLen /2;
            }
        }
        return s.substring(start, end +1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >=0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r-l -1;
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
