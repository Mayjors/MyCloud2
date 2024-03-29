package com.example.algorithm.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ClassicOfferHot4String {
    public static void main(String[] args) {
        maxProfit(new int[] {7,1,5,3,6,4});
        jump(new int[] {2, 3, 1, 1, 2, 2, 2});
        minSubArrayLen(7, new int[]{2,3,1,2,4,3});
        System.out.println(restoreIpAddresses("255255255255"));
    }

    /**
     * 88. 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n;
        while (n > 0) {
            if (m > 0 && nums1[m - 1] > nums2[n - 1]) {
                nums1[i] = nums1[m - 1];
                m--;
            } else {
                nums1[i] = nums2[n - 1];
                n--;
            }
            i--;
        }
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int n : nums) {
            if (n != val) {
                nums[idx++] = n;
            }
        }
        return idx;
    }

    /**
     * 26. 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }

    /**
     * 169. 多数元素
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int n = nums[0], count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (n == nums[i]) {
                ++count;
            } else if (--count == 0) {
                n = nums[i];
                count = 1;
            }
        }
        return n;
    }

    /**
     * 189. 轮转数组
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = nums[start];
            start++;
            end--;
        }
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int cost = prices[0], max = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            max = Math.max(max, price - cost);
        }
        return max;
    }

    /**
     * 55. 跳跃游戏
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    /**
     * 209. 长度最小的子数组
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r];
            r++;
            while (sum >= target) {
                min = Math.min(min, r - l);
                sum = sum - nums[l];
                l++;
            }
        }
        return min;
    }

    /**
     * 9.回文数
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int cur = 0;
        int num = x;
        while (num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }

    /**
     * 66.加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 191. 位1的个数
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (matrix[i][j] == target) return true;
//            }
//        }
        // 从右上角开始找
        int i=0, j=0;
        for (i=0, j=matrix[0].length-1; i<matrix.length && j>=0;) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) j--;
            else i++;
        }
        return false;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int low = 0;
        int hight = nums.length - 1;
        while (low < hight) {
            int mid = low + (hight - low) / 2;
            if (nums[mid] < nums[hight]) {
                low = mid + 1;
            } else {
                hight = mid;
            }
        }
        return nums[low];
    }

    public static List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len < 4 || len > 12) {
            return res;
        }
        Deque<String> path = new ArrayDeque<>();
        restoreIpDfs(s, len, 0, 4, path, res);
        return res;
    }

    private static void restoreIpDfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {
        // 如果字符串已经遍历到最后，且已经切分成4段
        // 就把当前路径上的元素加入到返回的结果集中
        if (begin == len && residue == 0) {
            res.add(String.join(".", path));
            return;
        }
        // begin表示遍历字符串从哪开始
        for (int i = begin; i<begin+3; i++) {
            // 如果遍历到的子串长度大于字符串长度,或者子串的值大于255,则直接返回
            // 如果超过字符串长度，就直接退出
            // begin每次选择都是从之前选择的元素的下一个元素开始
            if (i>=len) {
                break;
            }
            // 如果剩余元素大于ip最多能容纳的个数就剪枝
            if (len - i > residue *3) {
                continue;
            }
            // 判断当前截取字符串是否小妤0或大于255
            // 这里的begin和i代表的事，这时候截取了几个字符
            if (judgeIpSegment(s, begin, i)) {
                String currentIpSegment = s.substring(begin, i + 1);
                path.addLast(currentIpSegment);
                restoreIpDfs(s, len, i + 1, residue - 1, path, res);
                // 剪枝
                path.removeLast();
            }
        }
    }

    private static boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        // 如果截取的大于等于2的字符串的开始为0，就直接false
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }
        // 定义返回结果的集合
        int res = 0;
        while (left <= right) {
            res = res * 10 + (s.charAt(left) - '0');
            left++;
        }
        return res >= 0 && res <= 255;
    }

}