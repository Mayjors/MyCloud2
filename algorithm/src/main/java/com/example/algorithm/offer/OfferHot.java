package com.example.algorithm.offer;

import java.util.*;

public class OfferHot {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target- nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 49. 字母异位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> temp = map.getOrDefault(key, new ArrayList<>());
            temp.add(str);
            map.put(key, temp);
        }
        list.addAll(map.values());
        return list;
    }

    /**
     * 128. 最长连续序列
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            map.put(num, num);
        }
        int max = 0;
        for (Integer num : nums) {
            // 找连续序列的最小值
            if (!map.containsKey(num - 1)) {
                int count = 1;
                int cur = num;
                while (map.containsKey(cur + 1)) {
                    cur++;
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

    /**
     * 283. 移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int left = 0, right = len -1;
        while (left < right) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        while (left < right) {
            nums[left] = 0;
            left++;
        }
    }

    /**
     * 11. 盛最多水的容器
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0, right = len -1;
        int max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                max = Math.max(max, height[left] * (right - left));
                left++;
            } else {
                max = Math.max(max, height[right] * (right - left));
                right--;
            }
        }
        return max;
    }

    /**
     * 15. 三数之和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i<nums.length; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left = 0, right = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < len) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(map.get(s.charAt(right)) +1, left);
            }
            map.put(s.charAt(right), right);
            max = Math.max(max, right - left +1);
            right++;
        }
        return max;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        return null;
    }


    /**
     * 560. 和为 K 的子数组
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {

        return 0;
    }


    /**
     * 239. 滑动窗口最大值
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 先求出第一个的最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i< k; i++) {
            max = Math.max(nums[i], max);
        }
        int[] res = new int[nums.length - k + 1];
        res[0] = max;
        for (int i = k; i< nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else if (nums[i-k] == max) {
                max = Integer.MIN_VALUE;
                for (int j = i-k+1; j<=i; j++) {
                    max = Math.max(nums[j], max);
                }
            } else {
                max = Math.max(max, nums[i]);
            }
            res[i-k+1] = max;
        }
        return res;
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[]{len - k +1};
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i< len; i++) {
            // 右边入
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            if (i + 1 >= k) {
                ans[i + 1 - k] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }

    /**
     * 76. 最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        return null;
    }

    /**
     * 121. 买卖股票的最佳时机
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i< prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    /**
     * 70. 爬楼梯
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int pre = 0, cur = 1;
        for (int i = 0; i< n; i++) {
            int temp = cur;
            cur = cur + pre;
            pre = temp;
        }
        return cur;
    }

    /**
     * 198. 打家劫舍
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
//        int[] dp = new int[len];
//        dp[0] = nums[0];
//        dp[1] = Math.max(nums[0], nums[1]);
//        for (int i = 2; i< len; i++) {
//            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
//        }
//        return dp[len-1];
        int pre = 0, cur = 0;
        for (int num : nums) {
            int temp = cur;
            cur = Math.max(pre + num, cur);
            pre = temp;
        }
        return cur;
    }

}
