package com.example.algorithm.news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class TopString {

    public static void main(String[] args) {
        TopString topString = new TopString();
        topString.merge(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);
        System.out.println(topString.jump(new int[] {2, 3, 1, 1, 2, 2, 2}));
        System.out.println(topString.hIndex(new int[] {3, 0, 6, 1, 5}));
        System.out.println(topString.candy(new int[] {1, 0, 2}));
        System.out.println(topString.candy2(new int[] {1, 0, 2}));
        int[] nums = new int[] {0, 2, 0, 2, 1, 1, 1, 0};
        topString.sortColors(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(topString.isValid("()[]{}"));
        System.out.println(topString.longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
        System.out.println(topString.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(topString.subsets(new int[] {1, 2, 3}));
        System.out.println(topString.subsets2(new int[] {1, 2, 3}));
        System.out.println(topString.lengthOfLongestSubstring("abcbacbb"));
        
    }

    /**
     * 1. 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            } else{
                map.put(nums[i], i);
            }
        }
        return new int[] {0};
    }

    /**
     * 75. 颜色分类
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0) {
                if (left == i) {
                    left++;
                } else{
                    int temp = nums[i];
                    nums[i] = nums[left];
                    nums[left++] = temp;
                }
            } else if (nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[right];
                nums[right--] = temp;
                i--;
            }
        }
    }

    /**
     * 88. 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int l = m + n;
        while (n > 0) {
            if (m >0 && nums1[m-1] > nums2[n-1]) {
                nums1[l-1] = nums1[m-1];
                m--;
            } else {
                nums1[l-1] = nums2[n-1];
                n--;
            }
            l--;
        }
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i< nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
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
     * 16. 最接近的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * 31. 下一个排列
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i+1]) {
            i--;
        }
        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        int left = i + 1, right = n - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 287. 寻找重复数
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1, right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 49. 字母异位词分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        // int idx = 0;
        // for (int n : nums) {
        //     if (n != val) {
        //         nums[idx++] = n;
        //     }
        // }
        // return idx;
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * 190. 颠倒二进制位
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int t = (n >> i) & 1;
            if (t == 1) {
                ans |= t << (31 - i);
            }
        }
        return ans;
    }

    /**
     * 191.位1的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int ans = 0;
        // while (n != 0) {
        //     ans += n & 1;
        //     n>>>=1;
        // }
        while (n != 0) {
            n = n & (n -1);
            ans++;
        }
        return ans;
    }

    /**
     * 26. 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        // if (nums == null || nums.length == 0) return 0;
        // int p = 0, q = 1;
        // while (q < nums.length) {
        //     if (nums[p] != nums[q]) {
        //         if (q - p > 1) {
        //             nums[p + 1] = nums[q];
        //         }
        //         p++;
        //     }
        //     q++;
        // }
        // return p + 1;
        int n = nums.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
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
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    /**
     * 55. 跳跃游戏
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int max = 0;
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
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
     * 274. H 指数
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i = citations.length - 1; i >= 0; i--) {  
            if (citations[i] >= h + 1) {
                h++;
            } else {
                break;
            }
        }
        return h;
    }

    /**
     * 134. 加油站
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0, total = 0, tank = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return total < 0 ? -1 : start;
    }

    /**
     * 135. 分发糖果
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int res = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            res += Math.max(left[i], right[i]);
        }
        return res + n;
    }

    public int candy2(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i< ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                left[i] = left[i-1] +1;
            }
        }
        int count = left[ratings.length-1];
        for (int i = ratings.length-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                right[i] = right[i+1] +1;
            }
            count+= Math.max(left[i], right[i]);
        }
        return count;
    }

    /**
     * 42. 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int res = 0;
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                res += max - height[left];
                left++;
            } else {
                res += max - height[right];
                right--;
            }
        }
        return res;
    }

    /**
     * 560. 和为 K 的子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * 58. 最后一个单词的长度
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >=0 && s.charAt(end) == ' ') {
            end--;
        }
        int start = end;
        while (start >=0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    /**
     * 151. 反转字符串中的单词
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        int len = s.length();
        int i = len - 1, j = len - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            sb.append(s.substring(i+1, j+1) + " ");
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            j=i;
        }
        
        return sb.toString().trim();
    }

    /**
     * 283. 移动零
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int left = 0, right = 0;
        while (right < len) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        while (left < len) {
            nums[left] = 0;
            left++;
        }
    }

    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.isEmpty()) return true;
        Stack<Character> start = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                start.push(c);
            } else {
                if (start.isEmpty()) return false;
                char top = start.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return start.isEmpty();
    }

    /**
     * 14. 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i< strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }

    /**
     * 28. 实现 strStr()
     * 28. 找出字符串中第一个匹配项的下标
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j = 0;
            for (; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 125. 验证回文串
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        StringBuffer bf = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                bf.append(Character.toLowerCase(ch));
            }
        }
        int n = bf.length();
        int left = 0, right = n -1;
        while (left < right) {
            if (bf.charAt(left) != bf.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 392. 判断子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    /**
     * 896. 单调数列
     *
     * @param nums
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        int len = nums.length;
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > nums[i+1]) {
                increasing = false;
            }
            if (nums[i] < nums[i+1]) {
                decreasing = false;
            }
        }
        return increasing || decreasing;
    }

    /**
     * 1332. 删除回文子序列
     *
     * @param s
     * @return
     */
    public int removePalindromeSub(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                return 2;
            }
        }
        return 1;
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    /**
     * 76. 最小覆盖子串
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int[] need = new int[256];
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            need[c]++;
            if (need[c] == 1) {
                valid++;
            }
            right++;
            while (valid == t.length()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                need[d]--;
                if (need[d] == 0) {
                    valid--;
                }
                left++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    /**
     * 209. 长度最小的子数组
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return right - left + 1;
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        int left = 0, right = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            } else {
                set.add(s.charAt(right));
                max = Math.max(max, set.size());
                right++;
            }
        }
        return max;
    }
    
    /**
     * 5. 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        int max = 0;
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            max = Math.max(max, Math.max(len1, len2));
            if (max > right - left) {
                left = i - (max - 1) / 2;
                right = i + max / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 54. 螺旋矩阵
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;
        int row = 0, col = 0;
        while (row <= rows && col <= cols) {
            for (int i = col; i <= cols; i++) {
                res.add(matrix[row][i]);
            }
            for (int i = row + 1; i <= rows; i++) {
                res.add(matrix[i][cols]);
            }
            if (row < rows && col < cols) {
                for (int i = cols - 1; i > col; i--) {
                    res.add(matrix[rows][i]);
                }
                for (int i = rows; i > row; i--) {
                    res.add(matrix[i][col]);
                }
            }
            row++;
            col++;
            rows--;
            cols--;
        }
    
        return res;
    }

    /**
     * 69. x 的平方根
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 137. 只出现一次的数字 II
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += (num >> i) & 1;
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /**
     * 78. 子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i< size; i++) {
                List<Integer> list = new ArrayList<>(res.get(i));
                list.add(num);
                res.add(list);
            }
        }
        return res;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<(1<<nums.length);i++) {
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<nums.length; j++) {
                if(((i>>j) & 1)==1) {
                    list.add(nums[j]);
                    System.out.println(list);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 39. 组合总和
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] candidates, int target, int start, List<Integer> list, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, list, res);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 22. 括号生成
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, n, "", res);
        return res;
    }

    private void dfs(int left, int right, String s, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) {
            dfs(left - 1, right, s + "(", res);
        }
        if (right > left) {
            dfs(left, right - 1, s + ")", res);
        }
    }

    /**
     * 56. 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] last = list.get(list.size() - 1);
            if (last[1] >= intervals[i][0]) {
                last[1] = Math.max(last[1], intervals[i][1]);
            } else {
                list.add(intervals[i]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    /*************************哈希表*******************************/

    /**
     * 383. 赎金信
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] map = new int[26];
        for (char c : magazine.toCharArray()) {
            map[c- 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            map[c - 'a']--;
        }
        for (int i = 0; i<26; i++) {
            if (map[i] < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 205. 同构字符串
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();   
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                if (map.containsValue(c2)) {
                    return false;
                }
                map.put(c1, c2);
            }
        }
        return true;
    }

    /**
     * 290. 单词规律
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] list = s.split(" ");
        if (pattern.length() != list.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(list[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(list[i])) {
                    return false;
                }
                map.put(c, list[i]);
            }
        }
        return true;
    }

    /**
     * 242. 有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        char[] c = new char[26];
        for (char ch : s.toCharArray()) {
            c[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            c[ch - 'a']--;
        }
        for (int i = 0; i<26; i++) {
            if (c[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 202. 快乐数
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            n = sum;
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
        }
        return true;
    }

    /*****************二分查找******************* */

    /**
     * 35. 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return left;
    }

    /**
     * 74. 搜索二维矩阵
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // int left = 0, right = m * n -1;
        // while (left <= right) {
        //     int mid = left + (right - left) /2;
        //     int row = mid /n, col = mid % n;
        //     if (matrix[row][col] == target) {
        //         return true;
        //     } else if (matrix[row][col] < target) {
        //         left = mid + 1;
        //     } else {
        //         right = mid -1;
        //     }
        // }
        // return false;
        int row = matrix.length - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                row--;
            }
        }
        return false;
    }

    /**
     * 162. 寻找峰值
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                left = mid;
                right = mid;
                while (left > 0 && nums[left] == target) {
                    left--;
                }
                while (right < nums.length && nums[right] == target) {
                    right++;
                }
                res[0] = left;
                res[1] = right;
                return res;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     * 347. 前 K 个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().getKey();
        }
        return res;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }
        int[] res = new int[k];
        int index = 0;
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    res[index++] = num;
                    if (index == k) {
                        return res;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return res;
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        backtrack(res, map, digits, 0, new StringBuilder());
        return res;
    }

    /**
     * 77. 组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) return res;
        backtrack1(res, n, k, 1, new ArrayList<>());
        return res;
    }

    private void backtrack1(List<List<Integer>> res, int n, int k, int start, List<Integer> list) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            backtrack1(res, n, k, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    private void backtrack(List<String> res, Map<Character,String> map, String digits, int i,
            StringBuilder stringBuilder) {
        if (i == digits.length()) {
            res.add(stringBuilder.toString());
            return;
        }
        String letters = map.get(digits.charAt(i));
        for (char c : letters.toCharArray()) {
            stringBuilder.append(c);
            backtrack(res, map, digits, i + 1, stringBuilder);
            stringBuilder.deleteCharAt(i);
        }
    }

    /**
     * 443. 压缩字符串
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int index = 0;
        int i = 0;
        while (i < chars.length) {
            char c = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == c) {
                i++;
                count++;
            }
            chars[index++] = c;
            if (count > 1) {
                for (char digit : Integer.toString(count).toCharArray()) {
                    chars[index++] = digit;
                }
            }
        }
        return index;
    }



    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }        
    }

}