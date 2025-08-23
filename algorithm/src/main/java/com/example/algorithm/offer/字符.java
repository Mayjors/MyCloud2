package com.example.algorithm.offer;

import java.util.*;

public class 字符 {
    public static void main(String[] args) {
        String babad = longestPalindrome3("babad");
        System.out.println(babad);
        System.out.println(isValid("(}"));
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
        System.out.println(generateParenthesis2(3));
        System.out.println(longestValidParentheses("))(()"));
        System.out.println(findMedianSortedArrays2(new int[]{1, 3, 5}, new int[]{2, 4}));
        System.out.println(restoreIpAddresses("255255255255"));
        System.out.println(missingNumber(new int[]{3, 0, 1}));

        int[][] nums={{3,1,2,4,5},{1,2,3,4},{3,4,5,6}};
        System.out.println(intersection1(nums).toString());

        rotate2(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);

    }

    public static List<Integer> intersection(int[][] nums) {
        List<Integer> list=new LinkedList<>();//计数排序的思想
        int[] a=new int[1001];
        for(int[] row:nums) {//从每一行中获取元素内容
            for(int data:row){//从遍历的每行中，在逐个遍历每一列的元素。
                a[data]++;
            }
        }
        for (int i = 0; i <a.length ; i++) {
            if (nums.length==a[i]){
                list.add(i);
            }
        }
        return list;
    }

    public static List<Integer> intersection1(int[][] nums) {
        Map<Integer,Integer> map=new HashMap<>();//计数排序的思想
        for(int[] row:nums) {//从每一行中获取元素内容
            for (int data : row) {//从遍历的每行中，在逐个遍历每一列的元素。
                map.put(data, map.getOrDefault(data, 0) + 1);
            }
        }
        List<Integer> list=new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == nums.length) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    /**
     * 242. 两个字符串包含的字符是否完全相同
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] d = new int[26];
        for (int i = 0; i < s.length(); i++) {
            d[s.charAt(i) - 'a']++;
            d[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (d[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数组中出现次数超过一半的数字
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int len = nums.length;
        int[] d = new int[len + 1];
        for (int i = 0; i < len; i++) {
            d[nums[i]]++;
            if (d[nums[i]] > len / 2) {
                return nums[i];
            }
        }
        return 0;
    }

    public int majorityElement2(int[] nums) {
        int res = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                res = num;
                count++;
                continue;
            }
            if (num == res) {
                count++;
            } else {
                count--;
            }
        }
        return res;
    }

    public static int missingNumber(int[] nums) {
        int len = nums.length;
        int[] d = new int[len +1];
        for (int num : nums) {
            d[num] = 1;
        }
        for (int i : d) {
            if (i == 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 66.加一
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i>=0; i--) {
            digits[i] ++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length +1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 替换空格
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        int len = str.length();
        for (int i=0; i<len; i++) {
            if (str.charAt(i) ==' ') {
                str.replace(i, i+1, "%20");
            }
        }
        return str.toString();
    }



    /**
     * 统计一个数字在排序数组中出现的次数
     * @param nums
     * @param k
     * @return
     */
    public static int getNumberOfK(int[] nums, int k) {
        int left = binarySearch(nums, k);
        if (nums[left] != k) {
            return 0;
        }
        int right = binarySearch(nums, k+1);
        return right - left;
    }

    private static int binarySearch(int[] nums, int k) {
        int left = 0, right = nums.length -1;
        while (left < right) {
            int mid = left + ((right - left) >>1);
            if (nums[mid] >= k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * 53.最大子数组和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
//        int max = nums[0];
//        int sum = 0;
//        for (int i=0; i<nums.length; i++) {
//            sum += nums[i];
//            max = Math.max(max, sum);
//            if (sum < 0) {
//                sum = 0;
//            }
//        }
//        return max;
        int pre = 0;
        int res = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            res = Math.max(res, pre);
        }
        return res;
    }

    /**
     * 调整数组顺序使奇数位于偶数前面
     * @param array
     */
    public static void reOrderArray(int[] array) {
        int left = 0;
        int right = array.length-1;
        while (left < right) {
            while (left < right && (array[left] & 1) == 1) {
                left++;
            }
            while (left < right && (array[right] & 1) == 0) {
                right--;
            }
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
        }
    }

    public static void reOrderArray2(int[] array) {
        int fast = 0, slow = 0;
        while (fast < array.length) {
            if ((array[fast] & 1) == 1) {
                int tmp = array[fast];
                array[fast] = array[slow];
                array[slow] = tmp;
                slow++;
            }
            fast++;
        }
    }


    /**
     * 80.删除有序数组中的重复项 II
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums, int k) {
        if (nums == null || nums.length <= k) return nums.length;
        // 初始化快慢指针
        int slow = k, fast = k;
        while (fast < nums.length) {
            // 如果nums[fast] 不等于nums[slow-k],则将nums[fast]复制到nums[slow]处,并且slow++
            if (nums[fast]!= nums[fast - k]) {
                nums[slow] = nums[fast];
                slow++;
            }
            // 将fast向前移动一位，以检查下一个元素
            fast++;
        }
        return slow;
    }

    /**
     * 392.判断子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        int len1 = s.length(), len2 = t.length();
        while (i < len1 && j < len2) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == len1;
    }

    /**
     * 283.移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int slow = 0, fast = 0;
        while (fast < len) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while (slow < len) {
            nums[slow] = 0;
            slow++;
        }
    }

    /**
     * 202.快乐数
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
//        Set<Integer> set = new HashSet<>();
//        while (n != 1 && !set.contains(n)) {
//            set.add(n);
//            n = squareSum(n);
//        }
//        return n == 1;
        int slow = n, fast = squareSum(n);
        while (fast != 1 && slow != fast) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }
        return slow == 1;
    }

    private int squareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            n /= 10;
            sum += digit * digit;
        }
        return sum;
    }

    /**
     * 289.生命游戏
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <board[0].length; j++) {
                copy[i][j] = board[i][j];
            }
        }
        // 计算board数组的每个元素周围的活细胞数量
        int count = 0;
        // 遍历board数组的每个元素
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 计算周围的活细胞数量
                count = 0;
                // 行和列的范围是i-1到i+1, j-1到j+1
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k >= 0 && k < board.length && l >= 0 && l < board[0].length && (k != i || l != j)) {
                            if (copy[k][l] == 1) {
                                count++;
                            }
                        }
                    }
                }
                if (copy[i][j] == 1) {
                    if (count < 2 || count > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    /**
     * 187.重复的DNA序列
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i +10 <= n; i++) {
            String sub = s.substring(i, i + 10);
            int cnt = map.getOrDefault(sub, 0);
            if (cnt == 1) {
                ans.add(sub);
            }
            map.put(sub, cnt + 1);
        }
        return ans;
    }

    /**
     * 443.压缩字符串
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        if (chars == null) return 0;
        if (chars.length <= 1) return chars.length;
        int write = 0, start = 0;
        for (int i = 1; i < chars.length; i++) {
            if (i == chars.length - 1 || chars[i]!= chars[i - 1]) {
                chars[write++] = chars[i];
                // 子串长度
                int len = i - start + 1;
                if (len > 1) {
                    String str = String.valueOf(len);
                    for (int j = 0; j < str.length(); j++) {
                        chars[write++] = str.charAt(j);
                    }
                }
                // 更新下一个子串的起点
                start = i + 1;
            }
        }
        return write;
    }

    /**
     * 633.平方数之和
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    /**
     * 524.通过删除字母匹配到字典里最长单词
     * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            } else {
                return a.compareTo(b);
            }
        });
        String ans = "";
        for (String t : dictionary) {
            int i = 0, j = 0;
            while (i < s.length() && j < t.length()) {
                if (s.charAt(i) == t.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == t.length()) {
                if (t.length() > ans.length() || (t.length() == ans.length() && t.compareTo(ans) < 0)) {
                    ans = t;
                }
            }
        }
        return ans;
    }

    public int magicalString(int n) {
        char[] s = new char[n+2];
        s[0] = 1;
        s[1] = s[2] = 2;
        char c = 2;
        for (int i = 2, j = 3; j < n; ++i) {
            c ^= 3; // 1^3 = 2, 2 ^3 = 1,这样就能在1和2之间转换
            s[j++] = c;
            if (s[i] == 2) s[j++] = c;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
//            if (s[i] == 1) ++ans;
            ans += 2 - s[i];
        }
        return ans;
    }

    /**
     * 42. 接雨水
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int len = height.length;
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            int leftMax = 0, rightMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i + 1; j < len; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            res += Math.min(leftMax, rightMax) - height[i];
        }
        return res;
    }

    public static int trap2(int[] height) {
        int len = height.length;
        int res = 0;
        int left = 0, right = len - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }

    /**
     * 896.单调数列
     * @param nums
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        int len = nums.length;
        boolean inc = true, dec = true;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] > nums[i]) {
                inc = false;
            }
            if (nums[i - 1] < nums[i]) {
                dec = false;
            }
        }
        return inc || dec;
    }

    /**
     * 27.移除元素
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
//        int slow =0, fast = 0;
//        while (fast < nums.length) {
//            if (nums[fast] != val) {
//                nums[slow] = nums[fast];
//                slow++;
//            }
//            fast++;
//        }
//        return slow;
        int left = 0, right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right-1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    /**
     * 977.有序数组的平方
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
//        int[] ans = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            ans[i] = nums[i] * nums[i];
//        }
//        Arrays.sort(ans);
//        return ans;
        int left = 0;
        int right = nums.length - 1;
        int[] ans = new int[nums.length];
        while (left <= right) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                ans[right] = nums[left] * nums[left];
                left++;
            } else {
                ans[right--] = nums[right] * nums[right];
            }
        }
        return ans;
    }

    /**
     * 26.删除有序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q-p > 1) {
                    nums[p+1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p+1;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> res = new ArrayList<>();
//        if (strs == null || strs.length == 0) return res;
//        Map<String, List<String>> map = new HashMap<>();
//        for (String str : strs) {
//            char[] chars = str.toCharArray();
//            Arrays.sort(chars);
//            String key = new String(chars);
//            if (map.containsKey(key)) {
//                map.get(key).add(str);
//            } else {
//                List<String> list = new ArrayList<>();
//                list.add(str);
//                map.put(key, list);
//                res.add(list);
//            }
//        }
//        return res;
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String keys = new String(chars);
            if (map.containsKey(keys)) {
                map.get(keys).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(keys, list);
                res.add(list);
            }
        }
        return res;
    }

    /**
     * 121. 买卖股票的最佳时机
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        for (int price : prices) {
            min = Math.min(price, min);
            max = Math.max(max, price - min);
        }
        return max;
    }

    /**
     * 402. 移掉 K 位数字
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>(num.length());
        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && c < stack.peek()) {
                stack.pop();
                k--;
            }
            if (c != '0' || !stack.isEmpty()) {
                stack.push(c);
            }
        }
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        StringBuffer str = new StringBuffer();
        while (!stack.isEmpty()) {
            str.append(stack.pollLast());
        }
        return str.length() == 0 ? "0" : str.toString();
    }

    /**
     * 32. 最长有效括号
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) return 0;
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

    public int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxLen =0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, right * 2);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i>= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, right * 2);
            } else if (right > left) {
                left = right = 0;
            }
        }
        return maxLen;
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

    /**
     * 31.下一个排列
     * @param nums
     */
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
     * 287.寻找重复数
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        while (nums[fast]!= nums[nums[fast]]) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        while (slow!= fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        return slow;
    }

    public int findDuplicate2(int[] nums) {
        int len = nums.length;
        int left = 1, right = len - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }

    public int findDuplicate3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 189.轮转数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        if (k == 0) return;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start <end) {
            int temp = nums[end];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 189.轮转数组
     * @param nums
     * @param k
     */
    public static void rotate2(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        int[] temp = Arrays.copyOf(nums, len);
        for (int i = 0; i < len; i++) {
            nums[(i + k) % len] = temp[i];
        }
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

    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0, ans = 0;
        while (right < s.length()) {
            while (left < right && s.charAt(left)!= s.charAt(right)) {
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    public List<List<Integer>> twoSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int left = 0, right = nums.length -1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                res.add(Arrays.asList(nums[left], nums[right]));
                while (left < right && nums[left] ==nums[left+1]) left++;
                while (left < right && nums[right] ==nums[right-1]) right--;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
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

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum == target) {
                    return res;
                } else if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
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
     * 93. 复原 IP 地址
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len> 12 || len < 4) {
            return res;
        }
        // 定义一个保存路径上的变量
        Deque<String> path = new ArrayDeque<>();
        restoreIpDfs(s, len, 0, 4, path, res);
        return res;
    }

    private static void restoreIpDfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {
        // 如果字符串已经遍历到最后，并且已经切分成4段了
        // 就把当前路径上的元素加入到返回的结果集中
        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }
            return;
        }
        // begin表示遍历字符串从哪开始
        for (int i = begin; i<begin+3; i++) {
            // 如果超出字符串长度，就直接退出
            // begin 每次选择都是从之前选择的元素的下一个元素开始
            if (i>=len) {
                break;
            }
            // 如果剩余元素大于ip最多能容纳的个数，就剪枝
            if (len -i > residue * 3) {
                continue;
            }
            // 判断当前截取字符串是否小于0或大于255
            // 这里的begin和i代表的是这时候截取了几个字符
            if (judgeIpSegment(s, begin, i)) {
                // 保留当前截取字符
                String currentIpSegment = s.substring(begin, i+1);
                path.push(currentIpSegment);
                restoreIpDfs(s, len, i+1, residue-1, path, res);
                path.pop();
            }
        }
    }

    private static boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }
        // 定义返回结果的集合
        int res = 0;
        // 拼接字符
        while (left <= right) {
            // res*10 是为了将先加的字符默认比后面的字符大10倍，也就是位数是从小到大
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        return res >= 0 && res <= 255;
    }

    /**
     * 409. 计算一组字符集合可以组成的回文字符串的最大长度
     * @param s
     * @return
     */
    public int longestPalindromeAll(String s) {
        int[] arr = new int[128];
//        for (int i = 0; i < s.length(); i++) {
//            arr[s.charAt(i)]++;
//        }
        for (char c : s.toCharArray()) {
            arr[c]++;
        }
        int res = 0;
        for (int i : arr) {
            res += i / 2 * 2;
            if (res % 2 == 0 && i % 2 == 1) {
                res++;
            }
        }
        return res;
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
     * 75. 颜色分类
     * @param nums
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i =0; i<n; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i<n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int[] shorts = new int[3];
        for (int num : nums) {
            shorts[num]++;
        }
        int j = 0;
        for (int i=0; i<shorts.length; i++) {
            while (shorts[i]-- > 0) {
                nums[j++] = i;
            }
        }
    }

    public void sortColors3(int[] nums) {
        int a =0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num < 2) {
                nums[b] = 1;
                b++;
            }
            if (num < 1) {
                nums[a] = 2;
                a++;
            }
        }
    }

    /**
     * 96. 不同的二叉搜索树
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i<=n; i++) {
            for (int j = 0; j<=i-1; j++) {
                dp[i] += dp[j] * dp[i-1-j];
            }
        }
        return dp[n];
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String ans = strs[0];
        for(String s : strs) {
            if (ans.length() > s.length()) {
                ans = s;
            }
        }
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) != ans.charAt(j)) {
                    ans = ans.substring(0, j);
                    break;
                }
            }
        }
        if (ans.isEmpty()) {
            return ans;
        }
        return ans;
    }


    /**
     * 搜索插入位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 寻找数组的中心索引
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int tag = 0;
        for (int i =0; i< nums.length; i++) {
            if (tag + nums[i] == sum - tag) {
                return i;
            }
            tag += nums[i];
        }
        return -1;
    }

    /**
     * 209.长度最小的子数组
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
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
     * 4. 寻找两个正序数组的中位数
     * 归并算法
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] nums = new int[m+n];
        int i = 0, j = 0, k = 0;
        while (i<m && j<n) {
            if (nums1[i] <= nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        while (i<m) {
            nums[k++] = nums1[i++];
        }
        while (j<n) {
            nums[k++] = nums2[j++];
        }
        if ((m+n)%2 == 1) {
            return nums[k/2];
        } else {
            return (nums[k/2-1]+nums[k/2])/2.0;
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        int left = 0, right = m, i, j, maxLeft = 0, minRight = 0;
        while (left <= right) {
            i = (left + right) / 2;
            j = (m + n + 1) / 2 - i;
//            if (i > 0 && j > 0) {
//                maxLeft = Math.max(maxLeft, Math.min(nums1[i-1], nums2[j-1]));
//            }
//            if (i > 0) {
//                minRight = Math.min(minRight, nums1[i-1]);
//            }
//            if (j > 0) {
//                maxLeft = Math.max(maxLeft, nums2[j-1]);
//            }
//            if (maxLeft <= minRight) {
//                left = i + 1;
//            } else {
//                right = i - 1;
//            }
            if (i>0 && nums1[i-1] > nums2[j]) {
                right = i-1;
            } else if (i<m && nums2[j-1] > nums1[i]) {
                left = i+1;
            } else {
                maxLeft = (i==0) ? nums2[j-1] : (j==0) ? nums1[i-1] : Math.max(nums1[i-1], nums2[j-1]);
                if ((m+n) %2 ==1) {
                    return maxLeft;
                }
                minRight = (i==m) ? nums2[j] : (j==n) ? nums1[i] : Math.min(nums1[i], nums2[j]);
                return (maxLeft+minRight)/2.0;
            }
        }
        return 0.0;
    }


    /**
     * 72. 编辑距离
     * 使用了一个二维数组 dp 来存储编辑距离的中间结果，
     * 其中 dp[i][j] 表示将 word1 的前 i 个字符转换成 word2 的前 j 个字符所需的最少操作次数。
     * 接着，我们使用两个 for 循环计算 dp 数组中的所有值，其中 dp[0][0] 初始值为 0，dp[0][j] 表示将空字符串转换为 word2 的前 j 个字符所需的最少操作次数，
     * dp[i][0] 表示将 word1 的前 i 个字符转换为空字符串所需的最少操作次数。最后，函数返回 dp[m][n]，
     * 即将 word1 转换为 word2 所需的最少操作次数
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i<=m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j<=n; j++) {
            dp[0][j] = j;
        }
        // 计算dp数组中的所有值
        for (int i = 1; i<=m; i++) {
            for (int j = 1; j<=n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 643.子数组最大平均数 I
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return 0.0;
        }
        if (len == 1) {
            return nums[0];
        }
//        int[] sums = new int[len];
//        for (int i = 0; i < len; i++) {
//            sums[i] = i == 0? nums[i] : sums[i-1] + nums[i];
//        }
//        int maxSum = 0;
//        for (int i = 0; i < len; i++) {
//            maxSum = Math.max(maxSum, sums[i]);
//            if (i >= k) {
//                maxSum = Math.max(maxSum, sums[i] - sums[i-k]);
//            }
//        }
//        return (double) maxSum / k;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i<= len; i++) {
            sum = sum - nums[i-k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return (double) maxSum / k;
    }

    /**
     * 658. 找到 K 个最接近的元素
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int size = arr.length;
        int left = 0, right = size -1;
        int removeNums = size - k;
        while (removeNums > 0) {
            if (Math.abs(arr[left] - x) > Math.abs(arr[right] - x)) {
                left++;
            } else {
                right--;
            }
            removeNums--;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    /**
     * 219.存在重复元素 II
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
//        for (int i = 0; i< len-1; i++) {
//            for (int j = i+1; j< len; j++) {
//                if (nums[i] == nums[j] && Math.abs(nums[i]-nums[j]) <= k) {
//                    return true;
//                }
//            }
//        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i< len; i++) {
            // 维护滑动窗口大小
            if (i > k) {
                // 将窗口中最左端的元素移除
                set.remove(nums[i-k-1]);
            }
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
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
