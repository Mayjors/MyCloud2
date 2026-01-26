package com.example.algorithm.news;

import com.example.algorithm.offer.TreeNode;

import static com.example.algorithm.offer.ClassicListNodeTest.reverse;

import java.util.*;

public class Hot {
    public static void main(String[] args) {
//        System.out.println(subarraySum(new int[] {1, 1, 1}, 2));
        System.out.println(subarraySum(new int[] {1, 2, 3}, 3));
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(productExceptSelf(new int[] {1, 2, 3, 4}));
        System.out.println(removeDuplicates(new int[] {1, 1, 2}));
        System.out.println(trap3(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(generateParenthesis(3));
    }

    /***********************数组*******************/

    /**
     * 134. 加油站
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

    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    /**
     * 1. 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

     /**
     * 26. 删除有序数组中的重复项
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int i = 0;
        for (int j = 1; j < len; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


    /**
     * 169. 多数元素
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }


    /**
     * 49. 字母异位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
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
     * 283. 移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i< nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, index, i);
                index++;
            }
        }
    }

    /**
     * 11. 盛最多水的容器
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r-l);
            max = Math.max(max, area);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
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
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i+1, right = nums.length - 1;
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
     * 42. 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }

    public int trap2(int[] height) {
        int res = 0;
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                max = Math.max(max, height[left]);
                res += max - height[left];
                left++;
            } else {
                max = Math.max(max, height[right]);
                res += max - height[right];
                right--;
            }
        }
        return res;
    }

    public static int trap3(int[] height) {
        int len = height.length;
        if (len < 3) return 0;
        int left = 0, right = len-1;
        int res = 0, max = 0;
        while(left < right) {
            if (height[left] < height[right]) {
                if (height[left] > max) {
                    max = height[left];
                } else {
                    res += max - height[left];
                }
                left++;
            } else {
                if (height[right] > max) {
                    max = height[right];
                } else {
                    res += max - height[right];
                }
                right--;
            }
        }
        return res;
    }

    /**
     * 14. 最长公共前缀
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String arr = strs[0];
        for (int i = 0; i< strs.length; i++) {
            if (strs[i].length() < arr.length()) {
                arr = strs[i];
            }
        }
        for (int i = 0; i < strs.length; i++) {
            int j = 0;
            for (; j < arr.length(); j++) {
                if (strs[i].charAt(j) != arr.charAt(j)) {
                    arr = arr.substring(0, j);
                    break;
                }
            }
        }
        return arr;
    }

    /**
     * 557. 反转字符串中的单词 III
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) return s;
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i].isEmpty()) continue;
            sb.append(arr[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= target) {
                sum -= nums[left++];
                if (sum >= target) {
                    return right - left;
                }
            }
        }
        return 0;
    }


    /**
     * 28. 实现 strStr()
     * 28. 找出字符串中第一个匹配项的下标
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) return 0;
        if (haystack == null || haystack.isEmpty()) return -1;
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }


    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int max = 0;
        int left = 0, right = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return max;
    }

    /**
     * 5. 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int max = 0;
        int begin = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && ((j - i < 3) || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) return s;
        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;
        for (int i = 0; i < strLen; i++) {
            left  = i -1;
            right = i+1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(left) == s.charAt(right)) {
                len += 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }

    /**
     * 560. 和为 K 的子数组
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
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
     * 239. 滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        int max = 0;
        for (int i = 0; i< nums.length - k +1; i++) {
            max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }

    /**
     * 76. 最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need[c] > 0) {
                valid++;
            }
            need[c]--;
            while (valid == t.length()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need[d] == 0) {
                    valid--;
                }
                need[d]++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    /**
     * 53. 最大子数组和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
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
        getParenthesis("", n, n, res);
        return res;
    }

    private static void getParenthesis(String n, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(n);
            return;
        }
        if (left > 0) {
            getParenthesis(n+"(", left-1, right, res);
        }
        if (right > left) {
            getParenthesis(n+")", left, right-1, res);
        }
    }

     /**
      * 56. 合并区间
      * @param intervals
      * @return
      */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][];
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> list = new ArrayList<>();
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (pre[1] >= intervals[i][0]) {
                pre[1] = Math.max(pre[1], intervals[i][1]);
            } else {
                list.add(pre);
                pre = intervals[i];
            }
        }
        list.add(pre);
        return list.toArray(new int[0][]);
    }

     /**
      * 189. 轮转数组
      * @param nums
      * @param k
      */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 238. 除自身以外数组的乘积
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    /*************二分查找**************/

     /**
      * 34. 在排序数组中查找元素的第一个和最后一个位置
      * @param nums
      * @param target
      * @return
      */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] == target) {
                left = mid;
                right = mid;
                while (left >=0 && nums[left] == target) {
                    left--;
                }
                while (right < nums.length && nums[right] == target) {
                    right++;
                }
                res[0] = left + 1;
                res[1] = right - 1;
                return res;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public int[] searchRange2(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        int left = 0, right = nums.length -1;
        while (left < right) {
            int mid = left + (right - left) /2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid +1;
            }
        }
        if (nums[left] != target) {
            return res;
        }
        res[0] = left;
        left = 0;
        right = nums.length -1;
        while(left < right) {
            int mid = left + (right - left +1) /2;
            if (nums[mid] <= target) {
                left = mid;
            } else{
                right = mid -1;
            }
        }
        res[1] = right;
        return res;
    }

     /**
      * 744. 寻找比目标字母大的最小字母
      * @param letters
      * @param target
      * @return
      */
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length -1;
        while (left < right) {
            int mid = left + (right - left) /2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return letters[left] > target ? letters[left] : letters[0];
    }

    /**
     * 1385. 两个数组间的距离值
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int res = 0;
        for (int i = 0; i <arr1.length; i++) {
            boolean flag = true;
            for (int j = 0; j< arr2.length; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res++;
            }
        }
        return res;
    }

    

    /**
     * 2300. 咒语和药水的成功对数
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int [] res = new int[spells.length];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            int left = 0, right = potions.length -1;
            while (left <= right) {
                int mid = left + (right - left) /2;
                if (spells[i] * potions[mid] >= success) {
                    right = mid -1;
                } else {
                    left = mid +1;
                }
            }
            res[i] = potions.length - left;
        }
        return res;
    }

    /**
     * 1170. 比较字符串最小字母出现频次
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int f1 = getFreq(queries[i]);
            int count = 0;
            for (int j = 0; j < words.length; j++) {
                int f2 = getFreq(words[j]);
                if (f1 < f2) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }

    private int getFreq(String s) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) {
                return arr[i];
            }
        }
        return 0;
    }



    /**************** 链表 ******************/

    /**
     * 1290. 二进制链表转整数
     * @param head
     * @return
     */
    public int getDecimalValue(ListNode head) {
        int res = 0;
        while (head != null) {
            res = res * 2 + head.val;
            head = head.next;
        }
        return res;
    }

    /**
     * 2181. 合并零之间的节点
     * @param head
     * @return
     */
    public ListNode mergeNodes(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int sum = 0;
        head = head.next;
        while (head != null) {
            if (head.val == 0) {
                cur.next = new ListNode(sum);
                cur = cur.next;
                sum = 0;
            } else {
                sum += head.val;
            }
            head = head.next;
        }
        return dummy.next;
    }

     /**
      * 725. 分隔链表
      * @param head
      * @param k
      * @return
      */
    public ListNode[] splitListToParts(ListNode head, int k) {
        if (head == null) return new ListNode[k];
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int n = len / k, m = len % k;
        ListNode[] res = new ListNode[k];
        cur = head;
        for (int i = 0; i < k; i++) {
            res[i] = cur;
            int size = n + (i < m ? 1 : 0);
            for (int j = 0; j < size - 1; j++) {
                cur = cur.next;
            }
            if (cur != null) {
                ListNode next = cur.next;
                cur.next = null;
                cur = next;
            }
        }
        return res;
    }

     /**
      * 203. 移除链表元素
      * @param head
      * @param val
      * @return
      */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

     /**
      * 19. 删除链表的倒数第 N 个结点
      * @param head
      * @param n
      * @return
      */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0, head);
        ListNode start = dummy, end = dummy;
        for (int i = 0; i< n; i++) {
            end = end.next;
        }
        while (end.next != null) {
            start = start.next;
            end = end.next;
        }
        start.next = start.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return head;
        ListNode fast = head, slow = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

     /**
      * 876. 链表的中间结点
      * @param head
      * @return
      */
    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 234. 回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode node = reverseList(head);
        while (node != null) {
            if (node.val != head.val) {
                return false;
            }
            head = head.next;
            node = node.next;
        }
        return true;
    }
    
    /**
     * 206. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    
     /**
      * 2095. 删除链表的中间节点
      * @param head
      * @return
      */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        if (pre == null) {
            return null;
        }
        pre.next = slow.next;
        return head;
    }

    /**
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode node = dummy;
        while (node.next != null && node.next.next != null) {
            ListNode left = node.next;
            ListNode right = node.next.next;
            
            node.next = right;
            left.next = right.next;
            right.next = left;
            node = left;
            
        }
        return dummy.next;
    }

     /**
      * 21. 合并两个有序链表
      * @param list1
      * @param list2
      * @return
      */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }

    /**
     * 1171. 从链表中删去总和值为零的连续节点
     * @param head
     * @return
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int sum = 0;
        Map<Integer, ListNode> map = new HashMap<>();
        map.put(0, dummy);
        while (head != null) {
            sum += head.val;
            map.put(sum, head);
            head = head.next;
        }
        sum = 0;
        head = dummy;
        while (head != null) {
            sum += head.val;
            head.next = map.get(sum).next;
            head = head.next;
        }
        return dummy.next;
    }

     /**
      * 23. 合并K个升序链表
      * @param lists
      * @return
      */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = mergeKLists(lists, left, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(o->o.val));
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null) {
                queue.offer(tail.next);
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists3(ListNode[] lists) {
        int len = lists.length;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (true) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < len; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    minIndex = i;
                }
            }
            if (minIndex == -1) break;
            tail.next = lists[minIndex];
            tail = tail.next;
            lists[minIndex] = lists[minIndex].next;
        }
        return dummy.next;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

     /**
      * 1669. 合并两个链表
      * @param list1
      * @param a
      * @param b
      * @param list2
      * @return
      */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        if (list1 == null || list2 == null) return null;
        ListNode cur = list1;
        for (int i = 0; i < a - 1; i++) {
            cur = cur.next;
        }
        ListNode next = cur.next;
        cur.next = list2;
        while (cur.next != null) {
            cur = cur.next;
        }
        for (int i = 0; i < b - a + 1; i++) {
            next = next.next;
        }
        cur.next = next;
        return list1;
    }

     /**
      * 2216. 美化数组的最少删除数
      * @param nums
      * @param head
      * @return
      */
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null) {
            if (set.contains(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /********二叉树***********/

    /**
     * 94. 二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i< size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }




    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /********************动态规划*****************/

    /**
     * 3693. 爬楼梯②
     * @param n
     * @param costs
     * @return
     */
    public int climbStairs(int n, int[] costs) {
        if (n == 0) return 0;
        if (n == 1) return costs[0] + costs[0];
        int[] dp = new int[n+1];
        int infinity = Integer.MAX_VALUE /2;
        for (int i = 1; i <= n; i++) {
            int minCost = infinity;
            int minStart = Math.max(i -3, 0);
            for (int j = minStart; j < i; j++) {
                minCost = Math.min(minCost, dp[j] + costs[i-1] + (i-j) * (i-j));
            }
            dp[i] = minCost;
        }
        return dp[n];
     }

     /**
      * 198. 打家劫舍
      * @param nums
      * @return
      */
     public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // int[] dp = new int[nums.length];
        // dp[0] = nums[0];
        // dp[1] = nums[1];
        // for (int i = 2; i< nums.length; i++) {
        //     dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        // }
        // return dp[nums.length -1];
        int pre = 0, cur = 0;
        for (int i = 0; i< nums.length; i++) {
            int temp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = temp;
        }
        return cur;
     }

     /**
      * 64. 最小路径和
      * @param grid
      * @return
      */
     public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            dp[i] = grid[0][i] + dp[i-1];
        }
        for (int i = 1; i < grid.length; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < grid[0].length; j++) {
                dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j];
            }
        }
        return dp[grid[0].length -1];

        // for (int i = 0; i < grid.length; i++) {
        //     for (int j = 0; j < grid[0].length; j++) {
        //         if (i == 0 && j == 0) {
        //             continue;
        //         } else if (i == 0) {
        //             grid[i][j] += grid[i][j-1];
        //         } else if (j == 0) {
        //             grid[i][j] += grid[i-1][j];
        //         } else {
        //             grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
        //         }
        //     }
        // }
        // return grid[grid.length -1][grid[0].length -1];
    }

    /**
     * 62. 不同路径
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }

     /**
      * 63. 不同路径 II
      * @param obstacleGrid
      * @return
      */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j-1];
                }
            }
        }
        return dp[n-1];
    }

     /**
      * 120. 三角形最小路径和
      * @param triangle
      * @return
      */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            dp[i] = dp[i-1] + triangle.get(i).get(i);
            for (int j = i-1; j > 0; j--) {
                dp[j] = Math.min(dp[j-1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] += triangle.get(i).get(0);
        }
        int min = dp[0];
        for (int i = 1; i < dp.length; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        //初始化最后一行
        //Integer[] dp = triangle.get(n - 1).toArray(new Integer[n]);
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        
        //依次取 下方一行，正下方和右下方的最小的。 更新替换到当前位置即可
        //因为是三角结构，下方总比上方多一个，所以右下方是始终存在的
        //最后一步，到达顶点，只有一个元素，自然是更新后的[0]为最小的
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

}
