package com.example.algorithm.news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopString {

    public static void main(String[] args) {
        TopString topString = new TopString();
        topString.merge(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);
        System.out.println(topString.jump(new int[] {2, 3, 1, 1, 2, 2, 2}));
        System.out.println(topString.hIndex(new int[] {3, 0, 6, 1, 5}));
        System.out.println(topString.candy(new int[] {1, 0, 2}));
        System.out.println(topString.candy2(new int[] {1, 0, 2}));
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
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
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
        if (s == null || s.isEmpty()) return 0;
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