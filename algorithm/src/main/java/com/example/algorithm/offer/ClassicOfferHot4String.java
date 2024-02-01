package com.example.algorithm.offer;

public class ClassicOfferHot4String {
    public static void main(String[] args) {
        maxProfit(new int[] {7,1,5,3,6,4});
        jump(new int[] {2, 3, 1, 1, 2, 2, 2});
        minSubArrayLen(7, new int[]{2,3,1,2,4,3});
    }

    /**
     * 88. 合并两个有序数组
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m+n;
        while (n > 0) {
            if (m>0 && nums1[m-1] > nums2[n-1]) {
                nums1[i] = nums1[m-1];
                m--;
            } else {
                nums1[i] = nums2[n-1];
                n--;
            }
            i--;
        }
    }

    /**
     * 27. 移除元素
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
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q-p >1) {
                    nums[p+1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p+1;
    }

    /**
     * 169. 多数元素
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int n = nums[0], count = 1;
        for (int i=0; i<nums.length; i++) {
            if (n == nums[i]) {
                ++count;
            } else if (--count==0) {
                n = nums[i];
                count = 1;
            }
        }
        return n;
    }

    /**
     189. 轮转数组
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
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
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int k = 0;
        for (int i=0; i<nums.length; i++) {
            if (i>k) return false;
            k = Math.max(k, i+nums[i]);
        }
        return true;
    }

    /**
     * 45. 跳跃游戏 II
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i=0; i<nums.length-1; i++) {
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
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (r<nums.length) {
            sum += nums[r];
            r++;
            while (sum>=target) {
                min = Math.min(min, r-l);
                sum = sum - nums[l];
                l++;
            }
        }
        return min;
    }

    /**
     * 9.回文数
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x <0) return false;
        int cur = 0;
        int num = x;
        while (num !=0) {
            cur = cur * 10 + num %10;
            num /=10;
        }
        return cur == x;
    }

    /**
     * 66.加一
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for (int i= digits.length-1; i>=0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length +1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 191. 位1的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n!=0) {
            count += n&1;
            n>>>=1;
        }
        return count;
    }
}
