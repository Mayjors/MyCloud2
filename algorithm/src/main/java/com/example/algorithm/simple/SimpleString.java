package com.example.algorithm.simple;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class SimpleString {
    public static void main(String[] args) {
        String s = minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
        isHappy(2);
        longestPalindrome2("babad");
        longestPalindrome3("ac");
        minSubArrayLen2(7, new int[]{2,3,1,2,4,3});
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            res = Math.max(res, area);
            if (height[j] < height[i]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    /**
     * leetcode76
     * 最小覆盖字串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * @return
     */
    public static String minWindow(String s, String t) {
        // 首先创建的是need数组表示每个字符在t中需要的数量，
        // 加入need[76] = 2,表明ASCII码为76的这个字符在目标字符串t中需要两个，如果是负数表明当前字符串在窗口中是多余的，需要过滤掉
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        /**
         * l: 滑动窗口左边界
         * r：滑动窗口右边界
         * size: 窗口长度
         * count：当次遍历中还需要几个字符才能满足包含t中所有字符的条件，最大也就是t的长度
         * start：如果有效更新滑动窗口，记录这个窗口的起始位置，方便后续找子串
         */
        int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            if (need[c] > 0) {
                count--;
            }
            // 无论这个字符是否包含在t中，need[]数组中对应那个字符的计数都减1，利用正负区分这个字符是多余还是有用的
            need[c]--;
            // count==0 说明当前的窗口已经满足了条件
            if (count == 0) {
                // 如果左边界这个字符对应的值在need[]数组中小于0，说明他是这个多余元素，不包含在t内
                while (l < r && need[s.charAt(l)] < 0) {
                    // 在need[]数组中维护更新这个值，增加1
                    need[s.charAt(l)]++;
                    l++;
                }
                //不能右移时候挑战最小窗口大小，更新最小窗口开始的start
                if (r - l + 1 < size) {
                    size = r - l + 1;
                    start = l;
                }
                //l向右移动后窗口肯定不能满足了 重新开始循环
                need[s.charAt(l)]++;
                l++;
                count++;
            }
            r++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }

    public static String minWindow2(String s, String t) {
        int[] need = new int[128];//need[i]表示需要该元素的数量,0代表不需要, <0代表多余的数量
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        int i = 0, j = 0;//i滑动窗口左端，j右端
        int minSize = Integer.MAX_VALUE;//当前发现的满足要求的最小窗口长度
        int needCnt = t.length();//所需元素的总数量
        int start = 0;
        while (j < s.length()) {
            //步骤一：不断增加j使滑动窗口增大，直到窗口包含了T的所有元素
            char jChar = s.charAt(j);
            if (need[jChar] > 0) {//新加入的字符使我们需要的元素
                needCnt--;
            }
            need[jChar]--;

            //此时窗窗口包含了T的所有元素，进入步骤二，增加i，扔掉不需要的元素
            if (needCnt == 0) {
                char iChar = s.charAt(i);
                while (need[iChar] < 0) {
                    //向右移动一格
                    need[iChar]++;
                    i++;
                    iChar = s.charAt(i);
                }
                if (j - i + 1 < minSize) {
                    minSize = j - i + 1;
                    start = i;//只有发现最小值时才更新start
                }
                //步骤三：让i再向右移动一个位置，使窗口不再满足条件，让其再次寻找，进入步骤一

                need[s.charAt(i)]++;
                i++;
                needCnt++;
            }
            j++;
        }
        return minSize == Integer.MAX_VALUE ? "" : s.substring(start, start + minSize);
    }

    /**
     * 349. 两个数组的交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    /**
     * 最长回文子串
     * dp[i][j]= true 表示i-j 是回文子串
     * dp[i][j] = (s[i] == s[j] and dp[i+1][j-1])
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int len = s.length();
        // 最长回文串起点，终点，长度
        int maxStart = 0, maxEnd = 0, maxLen = 0;
        boolean[][] dp = new boolean[len][len];
        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - 1 <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > len) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

    /**
     * 最长回文子串
     * dp[i][j]= true 表示i-j 是回文子串
     * dp[i][j] = (s[i] == s[j] and dp[i+1][j-1])
     * dp[i][j] 如果去掉s[i]和s[j] 那么d[i+1][j-1]的长度小于2的时候，[i][j]本身就是回文子串，即j-1 -(i+1) <2 --> j-i<3
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int len = s.length();
        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < len; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 最长回文子串
     * 中心扩散算法
     *
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int len = s.length();
        int maxLen = 0;
        // 数组第一位记录其实位置，第二位记录长度
        int[] res = new int[2];
        for (int i = 0; i < len - 1; i++) {
            int[] odd = centerSpread(s, i, i);
            int[] even = centerSpread(s, i, i + 1);
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }

        return s.substring(res[0], res[0] + res[1]);
    }

    private static int[] centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right - left - 1};
    }


    /**
     * 快乐数
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private static int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }

    /**
     * 209. 长度最小的子数组
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i=0; i< nums.length; i++) {
            int sum = nums[i];
            if (sum>=target) {
                return 1;
            }
            for (int j=i+1; j<nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    min = Math.min(min, j-i+1);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0:min;
    }

    public static int minSubArrayLen2(int target, int[] nums) {
        int l = 0, r=0, sum=0, min = Integer.MAX_VALUE;
        while (r<nums.length) {
            sum += nums[r++];
            while (sum >= target) {
                min = Math.min(min, r-l);
                sum-= nums[l++];
            }
        }
        return min== Integer.MAX_VALUE ? 0 : min;
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length ==0) {
            return new int[0];
        }
        quickSort(arr, 0, arr.length-1);
        return arr;
    }

    public static int[] getLeastNumbers2(int[] arr, int k) {
        if (k == 0 || arr.length ==0) {
            return new int[0];
        }
        Queue<Integer> heap = new PriorityQueue<>(k, (i1, i2)-> Integer.compare(i2, i1));
        for (int e : arr) {
            // 当前数字小于堆顶元素才入堆
            if (heap.isEmpty() || heap.size() <k || e < heap.peek()) {
                heap.offer(e);
            }
            if (heap.size() > k) {
                heap.poll();// 删除堆顶最大元素
            }
        }
        int[] res= new int[heap.size()];
        int j=0;
        for (int e : heap) {
            res[j++] = e;
        }
        return res;
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int i=l, j=r;
        while (i<j) {
            while (i<j && arr[j]>= arr[l]) j--;
            while (i<j && arr[i]<= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, j);
        quickSort(arr, l, i-1);
        quickSort(arr, i+1, r);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}