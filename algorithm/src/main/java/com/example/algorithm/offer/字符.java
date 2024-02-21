package com.example.algorithm.offer;

import java.util.*;

public class 字符 {
    public static void main(String[] args) {
        String babad = longestPalindrome3("babad");
        System.out.println(babad);
        System.out.println(isValid("(}"));
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
        System.out.println(generateParenthesis2(3));
        System.out.println(longestValidParentheses(")(()"));
    }


    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // 如果是右括号则出栈
                stack.pop();
                // 但是如果栈是空的，把右括号放进去
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 如果栈不为空，说明左括号还没有出栈
                    int left = stack.peek();
                    res = Math.max(res, i - left);
                }
            }
        }
        return res;
    }


    /**
     * 33. 搜索旋转排序数组
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        for (int i = len -1; i > 0; i--) {
            // 从右向前找到第一个相邻升序的元素对
            if (nums[i] > nums[i - 1]) {
                Arrays.sort(nums, i, len);
                for (int j = i; j <len; j++) {
                    // 如果在[j, len-1]中有比i-1位置元素大的，直接交换
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
    }

    public static void nextPermutation2(int[] nums) {
        int len = nums.length;
        // 从后向前查找第一个相邻升序的元素对 (i, i+1)，满足 nums[i] < nums[i+1]。此时 [i+1,end) 必然是降序
        for (int i = len -1; i > 0; i--) {
            for (int j = len-1; j >i; j--) {
                // 在 [i+1,end) 从后向前查找第一个元素 j 满足 nums[i] < nums[j]
                if (nums[i] < nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    // 反转[i-1, end
                    Arrays.sort(nums, i+1, nums.length);
                    return;
                }
            }
        }
        Arrays.sort(nums);
    }


    /**
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int start = 0, end = 0; end < len; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(map.get(c), start);
            }
            ans = Math.max(ans, end - start +1);
            map.put(s.charAt(end), end+1);
        }
        return ans;
    }

    /**
     * 15. 三数之和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <3) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >0) break;
            if (i>0 && nums[i] == nums[i-1]) continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l<r && nums[l] == nums[l+1]) l++;
                    while (l<r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return result;
    }

    /**
     * 11. 盛最多水的容器
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int max = 0;
//        for (int i = 0; i < height.length; i++) {
//            for (int j = i+1; j < height.length; j++) {
//                max = Math.max(max, Math.min(height[i], height[j]) * (j-i));
//            }
//        }
        int i = 0, j = height.length - 1;
        while (i<j) {
            if (height[i] < height[j]) {
                max = Math.max(max, (j-i) * height[i]);
                i++;
            } else {
                max = Math.max(max, (j-i) * height[j]);
                j--;
            }
        }
        return max;
    }

    /**
     * 22. 括号生成
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        parenthesis("", n, n, res);
        return res;
    }

    public static List<String> generateParenthesis2(int n) {
        if (n == 1) {
            return Arrays.asList("()");
        }
        HashSet<String> set = new HashSet<>();
        for (String str : generateParenthesis2(n-1)) {
            for (int i =0; i<=str.length()/2; i++) {
                set.add(str.substring(0, i) + "()" + str.substring(i, str.length()));
            }
        }
        return new ArrayList<>(set);
    }

    public static void parenthesis(String str, int left, int right, List<String> res) {
        // 每次尝试都使用新的字符串变量，所以无需回溯
        // 在递归终止时，直接把它添加到结果集即可
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            parenthesis(str+"(", left-1, right, res);
        }
        if (right > 0) {
            parenthesis(str+")", left, right-1, res);
        }
    }

    /**
     * 20. 有效的括号
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top!= '(') || (c == '}' && top!= '{') || (c == ']' && top!= '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * 5. 最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        String res = "";
        int len = s.length();
        boolean[] p = new boolean[len];
        for (int i = len-1; i >= 0; i--) {
            for (int j = len-1; j>= i; j--) {
                p[j] = s.charAt(i) == s.charAt(j) && (j-i < 3 || p[j-i]);
                if (p[j] && j-i+1 > res.length()) {
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() <1) return "";
        int start = 0, end =0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > end-start) {
                start = i-(len-1) /2;
                end = i+ len/2;
            }
        }
        return s.substring(start, end+1);
    }

    public static String longestPalindrome3(String s) {
        // 中心扩散
        if (s == null || s.length() ==0) {
            return "";
        }
        int strlen = s.length();
        int left = 0, right = 0;
        int len = 1;
        int maxStart = 0, maxLen = 0;
        for (int i = 0; i < strlen; i++) {
            left = i-1;
            right = i+1;
            while (left>= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strlen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left>=0 && right<strlen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart+1, maxStart+maxLen + 1);
    }
    public static String longestPalindrome1(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    max = Math.max(max, ans.length());
                    ans = s.substring(i, j);
                }
            }
        }
        return ans;
    }

    /**
     * 215. 数组中的第K个最大元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    public static int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int pivot = partition(nums, left, right);
            if (pivot == target) {
                return nums[pivot];
            } else if (pivot < target) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        return nums[left];
    }

    private static int partition(int[] nums, int left, int right) {
        int ptvot = nums[right];
        int i = left-1;
        for (int j = left; j<right; j++) {
            if (nums[j] <= ptvot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i+1, right);
        return i+1;
    }



    public static boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len/2; i++) {
            if (s.charAt(i)!= s.charAt(len-i-1)) {
                return false;
            }
        }
        return true;
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >=0 && r<s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r-l-1;
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
