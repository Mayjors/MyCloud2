package com.example.algorithm.offer;

import java.util.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OfferHot3 {
    public static void main(String[] args) {
        isValid("()[]{}");
        removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        plusOne(new int[]{4, 3, 2});

        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode.left = treeNode2;
        treeNode.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        inorderTraversal(treeNode);

        missingNumber(new int[]{0, 1, 3});
        moveZeroes(new int[]{0, 1, 0, 3, 12});
        lengthOfLongestSubstring("abcabcbb");
        strStr("sadabutsad", "abu");
        searchRange(new int[]{5,7,7,8,8,10}, 8);
        subsets(new int[] {1, 2, 3});

        TreeNode tree = new TreeNode(3);
        TreeNode tree2 = new TreeNode(9);
        TreeNode tree3 = new TreeNode(20);
        TreeNode tree4 = new TreeNode(15);
        TreeNode tree5 = new TreeNode(7);
        tree.left = tree2;
        tree.right = tree3;
        tree2.left = tree4;
        tree2.right = tree5;
        levelOrder(tree);
        longestConsecutive(new int[] {100,4,200,1,3,2});

        longestPalindrome3("babad");
//        nextPermutation3(new int[]{1, 2, 3});
        nextPermutation3(new int[]{1, 5, 8, 4, 7, 6, 5, 3, 1});

        ListNode node = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        deleteNode(node, 5);
        findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1});
        findDisappearedNumbers2(new int[]{4,3,2,7,8,2,3,1});
        subarraySum2(new int[]{1, 2, 3}, 3);

        findKthLargest(new int[]{3,2,1,5,6,4}, 2);
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
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 415.字符串相加
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length()-1, j = num2.length()-1, carry =0;
        while(i>=0 || j>=0) {
            int n1= i>=0 ? num1.charAt(i) - '0' : 0;
            int n2= j>=0 ? num2.charAt(j) - '0' : 0;
            int temp = n1+n2 + carry;
            carry = temp /10;
            res.append(temp % 10);
            i--;
            j--;
        }
        if(carry == 1) res.append(1);
        return res.reverse().toString();
    }

    /**
     * 53. 最大子数组和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
//        int len = nums.length;
//        int[] dp = new int[len];
//        dp[0] = nums[0];
//        for(int i=1; i<len; i++) {
//            if(dp[i-1] >0) {
//                dp[i] = dp[i-1] + nums[i];
//            } else {
//                dp[i] = nums[i];
//            }
//        }
//        int res = dp[0];
//        for(int i=0; i<len; i++) {
//            res = Math.max(res, dp[i]);
//        }
        int pre = 0;
        int res = nums[0];
        for(int num : nums) {
            pre = Math.max(pre + num, num);
            res = Math.max(res, pre);
        }
        return res;
    }

    /**
     * 27. 移除元素
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int x : nums) {
            if (x != val) {
                nums[idx++] = x;
            }
        }
        return idx;
    }

    /**
     * 14. 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String ans = strs[0];
        for (int i = 0; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            ans = ans.substring(0, j);
            if (ans.equals("")) {
                return ans;
            }
        }
        return ans;
    }

    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s == null) return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.empty() || c != stack.pop()) {
                return false;
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    /**
     * 22. 括号生成
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if(n == 0) return res;
        getParenthesis("", n, n, res);
        return res;
    }

    private void getParenthesis(String n, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(n);
            return;
        }
        if (left == right) {
            getParenthesis(n+"(", left-1, right, res);
        } else if (left < right) {
            if (left > 0) {
                getParenthesis(n+"(", left-1, right, res);
            }
            getParenthesis(n+")", left, right-1, res);
        }
    }

    /**
     * 88. 合并两个有序数组
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m+n;
        while(n>0) {
            if (m>0 && nums1[m-1] > nums2[n-1]) {
                nums1[--i] = nums1[--m];
            } else {
                nums1[--i] = nums2[--n];
            }
        }
    }

    /**
     * 658.找到 K 个最接近的元素
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int right = leftBoundSearch(arr, x);
        int left = right - 1;
        while (k-- > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public int leftBoundSearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid]  < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    /**
     * 674. 最长连续递增序列
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int start = 0;
        int max = Integer.MIN_VALUE;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] <= nums[i-1]) {
                start = i;
            }
            max = Math.max(max, i-start+1);
        }
        return max;
    }

    /**
     * 子数组最大平均值
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int len = nums.length;
        // 先统计第一个窗口的和
        for(int i=0; i<k; i++) {
            sum+= nums[i];
        }
        int max = sum;
        for (int i=k;i<len; i++) {
            sum = sum-nums[i-k]+nums[i];
            max = Math.max(sum, max);
        }
        return max/k * 1.0;
    }

    /**
     * 省份数量
     * 深度优先
     * @param citysConnected
     * @return
     */
    public static int getProvince(int[][] citysConnected) {
        int citys = citysConnected.length;
        boolean[] visited = new boolean[citys];
        int provinces = 0;
        for (int i=0; i< citys; i++) {
            if (!visited[i]) {
                provinceDfs(i, citys, visited, citysConnected);
                provinces++;
            }
        }
        return provinces;
    }

    private static void provinceDfs(int i, int citys, boolean[] visited, int[][] citysConnected) {
        for (int j=0; j<citys; j++) {
            if (citysConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                provinceDfs(j, citys, visited, citysConnected);
            }
        }
    }

    /**
     * 21. 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return head.next;
    }

    /**
     * 23. 合并K个升序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (true) {
           ListNode minNode = null;
           int minPointer = -1;
           for (int i=0; i<k; i++) {
               if (lists[i] == null) {
                   continue;
               }
               if (minNode == null || lists[i].val < minNode.val) {
                   minNode = lists[i];
                   minPointer = i;
               }
           }
           if (minPointer == -1) {
               break;
           }
           tail.next = minNode;
           tail = tail.next;
           lists[minPointer] = lists[minPointer].next;
        }
        return dummyHead.next;
    }

    /**
     * 56. 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
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
     * 66. 加一
     *
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
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
     * 删除链表的节点
     * @param head
     * @param val
     * @return
     */
    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                break;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode cre = head;
        while (cre.next != null) {
            if (cre.next.val == cre.val) {
                cre.next = cre.next.next;
            } else {
                cre = cre.next;
            }
        }
        return head;
    }

    /**
     * 69. x 的平方根
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        int left = 1, right = x / 2;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
//        int[] dp = new int[n+1];
//        dp[0] = 1;
//        dp[1] = 1;
//        for (int i=2; i<=n; i++) {
//            dp[i] = dp[i-1] + dp[i-2];
//        }
//        return dp[n];
        int a = 0, b = 0, sum = 1;
        for (int i = 1; i <= n; i++) {
            a = b;
            b = sum;
            sum = a + b;
        }
        return sum;
    }

    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (stack.size() > 0 || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            res.add(temp.val);
            root = temp.right;
        }
        return res;
    }

    /**
     * 191. 位1的个数
     *
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }

    /**
     * 905. 按奇偶排序
     * @param nums
     * @return
     */
    public int[] sortArrayByParity(int[] nums) {
        int left =0, right = nums.length-1;
        while (left < right) {
            while (left<right && (nums[left] &1) == 0) {
                left++;
            }
            while (left<right && (nums[right]&1) == 1) {
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        return nums;
    }

    /**
     * 206. 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 234. 回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        return false;
    }

    /**
     * 268. 丢失的数字
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int len = nums.length;
//        Arrays.sort(nums);
//        for (int i=0; i<len; i++) {
//            if (nums[i] != i) return i;
//        }
//        int cur = 0, sum = len * (len+1)/2;
//        for (int i : nums) cur+=i;
//        return sum-cur;
        boolean[] hash = new boolean[len + 1];
        for (int i = 0; i < len; i++) {
            hash[nums[i]] = true;
        }
        for (int i = 0; i < len; i++) {
            if (!hash[i]) return i;
        }
        return len;
    }

    /**
     * 283. 移动零
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
//        int j =0;
//        for (int i=0; i<nums.length; i++) {
//            if (nums[i] != 0) {
//                int temp = nums[i];
//                nums[i] = nums[j];
//                nums[j] = temp;
//                j++;
//            }
//        }
        int left =0, right =0;
        while (right<nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    /**
     * 344. 反转字符串
     * @param s
     */
    public void reverseString(char[] s) {
        int len = s.length;
        int left =0, right = len-1;
        while (left<right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 7. 整数反转
     * @param x
     * @return
     */
    public int reverse(int x) {
        int res= 0;
        while (x != 0) {
            int temp = x%10;
            // 判断是否大于最大32位整数
            if (res > 214748364 || (res == 214748364 && temp >7)) {
                return 0;
            }
            // 判断是否小妤 最小32位整数
            if (res < -214748364 || (res == -214748364 && temp < -8)) {
                return 0;
            }
            res = res * 10 + temp;
            x /= 10;
        }
        return res;
    }

    /**
     * 350. 两个数组的交集 II
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) +1;
            map.put(num, count);
        }
        int[] res = new int[nums1.length];
        int index =0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count >0) {
                res[index++] = num;
                count--;
                if (count>0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }

    /**
     * 2. 两数相加
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(-1);
        ListNode cur = pre;
        int carry =0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x+y;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * 461. 汉明距离
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int s = x^y, ret = 0;
        while (s != 0) {
            ret += s&1;
            s>>=1;
        }
        return ret;
    }

    /**
     * 560. 和为 K 的子数组
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int count =0;
        for (int left = 0; left<len; left++) {
            int sum = 0;
            for (int right = left; right<len; right++) {
                sum +=nums[right];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] preSum = new int[len+1];
        for (int i=0; i<len; i++) {
            preSum[i+1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int left =0; left<len; left++) {
            for (int right = left; right<len; right++) {
                if (preSum[right+1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 226. 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 141. 环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * 3. 无重复字符的最长子串
     * 输入: s = "abcabcbb"
     * 输出: 3
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int[] m = new int[128];
        int len = 0;
        for (int i=0, j=0; j<s.length(); j++) {
            i = Math.max(m[s.charAt(j)], i);
            len = Math.max(len, j-i+1);
            m[s.charAt(j)] = j+1;
        }
        return len;
    }

    public static int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        // 滑动窗口左指针
        int left = 0, maxLen = 0;
        for (int i=0; i<s.length(); i++) {
            // 先判断当前字符是否包含在map中，如果不包含添加
            // 如果包含则 1、字符串在当前有效字段中 如abca，更新left=get(0)+1=1，有效字段为bca
            // 2、当前字段不包含在当前有效字段中
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) +1);
            }
            map.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i-left+1);
        }
        return maxLen;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (n+m+1)/2;
        int right = (n+m+2)/2;
        // 将偶数和奇数的情况合并，如果是奇数，会求两次同样的k
        return (getKth(nums1,   0, m-1, nums2, 0, n-1, left) + getKth(nums1, 0, m-1, nums2, 0, n-1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1-start1+1;
        int len2 = end2-start2+1;
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2+k-1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k/2) -1;
        int j = start2 + Math.min(len2, k/2) -1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j+1, end2, k-(j-start2+1));
        } else {
            return getKth(nums1, i+1, end1, nums2, start2, end2, k-(i-start1+1));
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        int left = 0, right = nums1.length;
        int halfLen = (nums1.length + nums2.length+1)/2;
        while (left<=right) {
            int i=(left+right)/2;
            int j = halfLen-i;
            if (i<right && nums2[j-1]>nums1[i]) {
                left++;
            } else if (i>left && nums1[i-1] > nums2[j]) {
                right--;
            } else {
                int leftMax = (i==0) ? nums2[j-1] : (j==0?nums1[i-1]:Math.max(nums1[i-1], nums2[j-1]));
                if (((nums1.length + nums2.length) & 1) == 1) {
                    return leftMax * 1.0;
                }
                int rightMin = (i==nums1.length) ? nums2[j]:(j==nums2.length ? nums1[i]:Math.min(nums1[i], nums2[j]));
                return (leftMax + rightMin) /2.0;
            }
        }
        return 0.0;
    }

    /**
     * 448. 找到所有数组中消失的数字
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        boolean[] b = new boolean[nums.length+1];
        for (int i =0; i< nums.length; i++) {
            if (!b[nums[i]]) {
                b[nums[i]] = true;
            }
        }
        for (int i=1; i<nums.length; i++) {
            if (!b[i]) {
                res.add(i);
            }
        }
        return res;
    }

    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        for(int num : nums) {
            int x = (num-1) % n;
            nums[x] +=n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }

    /**
     * 11. 盛最多水的容器
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i=0, j=height.length-1, res = 0;
        while(i<j) {
            if(height[i] < height[j]) {
                res = Math.max(res, (j-i) * height[i]);
                i++;
            } else {
                res = Math.max(res, (j-i) * height[j]);
                j--;
            }
        }
        return res;
    }

    /**
     * 5. 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length()<2) return s;
        int len = s.length(), maxLen = 0;
        int[] res = new int[2];
        for (int i=0; i<len; i++) {
            int[] odd = help(s, i, i);
            int[] even = help(s, i, i+1);
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1]> maxLen) {
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0]+ res[1]);
    }

    /**
     * 动态规划
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() <2) return s;
        int len = s.length();
        int maxStart = 0, maxEnd = 0, maxLen =1;
        boolean[][] dp = new boolean[len][len];
        for (int r = 1; r < len; r++) {
            for (int l=0; l<r; l++) {
//                dp[l][r] => num[l]==num[r] && dp[l+1][r--];
                if (s.charAt(l) == s.charAt(r) && ((r-l)<=2 || dp[l+1][r-1])) {
                    dp[l][r] = true;
                    if (r-l+1 > maxLen) {
                        maxLen = r-l+1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd+1);
    }

    /**
     * 中心扩散
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() ==0) return s;
        int strLen = s.length(), left=0, right=0;
        int maxStart = 0, maxEnd = 0, maxLen =0, len=1;
        for (int i=0; i<strLen; i++) {
            left = i-1;
            right = i+1;
            while (left>=0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right<strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left>=0 && right<strLen && s.charAt(right) == s.charAt(left)) {
                len = len+2;
                left--;
                right++;
            }
            if (len>maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len=1;
        }
        return s.substring(maxStart+1, maxStart+maxLen+1);
    }

    /**
     * 中心扩散
     * @param s
     * @return
     */
    public static String longestPalindrome4(String s) {
        if (s == null || s.length()<1) return "";
        int start = 0, end = 0;
        for(int i =0; i<s.length(); i++) {
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

    private static int expandAroundCenter(String s, int left, int right) {
        while(left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right-left-1;
    }

    /**
     * 统计素数个数
     * @param n
     * @return
     */
    public static int eratosthenes(int n) {
        boolean[] isPrime = new boolean[n];
        int count = 0;
        for (int i=2; i<n; i++) {
            if (!isPrime[i]) {
                count++;
                for (int j=i*i; j<n; j+=i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }


    /**
     * 15. 三数之和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length<3) return res;
        int len = nums.length;
        Arrays.sort(nums);
        for (int i=0; i<len; i++) {
            if (nums[i] >0) break;
            if (i>0 && nums[i] == nums[i-1]) continue;
            int l = i+1, r = len-1;
            while (l<r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
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
        return res;
    }

    /**
     * 10.正则表达式匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String str, String pattern, int s, int p) {
        if (p==pattern.length()) {
            return str.length() == s;
        }
        // 正则表达式下一位为*，则此时考虑两种情况
        if (p+1 < pattern.length() && pattern.charAt(p+1) == '*') {
            // 正则表达式当前位字符与字符串当前位置向匹配，则匹配1位或0位
            if (s<str.length() && (str.charAt(s) == pattern.charAt(p) || pattern.charAt(p) == '.')) {
                return isMatch(str, pattern, s, p+2) || isMatch(str, pattern, s+1, p);
            }
            // 若正则表达式当前为字符与字符串当前位置不匹配，则匹配0位
            return isMatch(str, pattern, s, p+2);
        }
        // 匹配1位
        if (s<str.length() && (str.charAt(s)==pattern.charAt(p) || pattern.charAt(p) == '.')) {
            return isMatch(str, pattern, s+1, p+1);
        }
        return false;
    }

    /**
     * 148. 排序链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val<right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? right : left;
        return res.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode start = pre, end = pre;
        for (int i=0; i<n; i++) {
            start = start.next;
        }
        while (start.next != null) {
            start = start.next;
            end = end.next;
        }
        end.next = end.next.next;
        return pre.next;
    }

    /**
     * 28. 找出字符串中第一个匹配项的下标
     * @param ss
     * @param pp
     * @return
     */
    public static int strStr(String ss, String pp) {
        int n = ss.length(), m = pp.length();
        char[] s = ss.toCharArray(), p = pp.toCharArray();
        for (int i=0; i<=n-m; i++) {
            int a=i, b=0;
            while (b<m && s[a] == p[b]) {
                a++;
                b++;
            }
            if (b==m) return i;
        }
        return -1;
    }

    /**
     * 33. 搜索旋转排序数组
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;
        int left =0, right = len-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (target == nums[mid]) return mid;
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target<= nums[right]) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            } else {
                if (target>=nums[left] && target < nums[mid]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            }
        }
        return -1;
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

    /**
     * 215. 数组中的第K个最大元素
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        // 数组的最值初始化
        int min = nums[0];
        int max = nums[0];
        for(int i : nums){
            // 遍历数组获取最值
            if(i < min){
                min = i;
            }
            if(i > max){
                max = i;
            }
        }

        // 利用最值造数组大小(造数组hash表)
        //hash表的长度为 max-min+1 即i - min都在这个范围内
        int[] hash = new int[max - min + 1];

        for(int i : nums){
            // i - min一定在 max - min + 1 范围 让i-min存入hash
            // 且这个hash有顺序 末尾位置的最大
            hash[i - min]++;
        }

        //求第k个最大值 从末尾开始遍历hash
        for(int i = max - min; i >= 0; i--){
            // hash 的值记录着个数
            k -= hash[i];
            // 当减完当前位置的个数少于0时 说明要取的值在这个位置
            if(k <= 0){
                // 当前这个位置索引 + min得到第k个最大值
                return i + min;
            }
        }
        // 若上述没有return说明每个值都一样
        return nums[0];
    }

    /**
     * 75. 颜色分类
     * @param nums
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        if(len < 2) return;
        int zero = 0;
        int two = len;
        int i=0;
        while(i<two) {
            if(nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if(nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    public void sortColors2(int[] nums) {
        int len = nums.length;
        int l=0, r = len-1;
        for(int i=0; i<=r; i++) {
            while (i <= r && nums[i] == 2) {
                swap(nums, i, r);
                --r;
            }
            if (nums[i] == 0) {
                swap(nums, i, l);
                ++l;
            }
        }
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;
        for (int i =2; i<=n; i++) {
            for (int j=1; j<=i; j++) {
                G[i] += G[j-1]*G[i-j];
            }
        }
        return G[n];
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = searchMid(nums, target);
        if (left >= nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = searchMid(nums, target+1);
        return new int[]{left, right-1};
    }

    private static int searchMid(int[] nums, int target) {
        int left =0, right = nums.length-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid]>= target) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return left;
    }

    public static int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length<1) {
            return new int[]{-1, -1};
        }
        int low = 0;
        int high = nums.length-1;
        while (low <= high) {
            int mid = (low + high)>>1;
            if(nums[mid] < target) {
                low = mid+1;
            } else if (nums[mid] > target) {
                high = mid-1;
            } else {
                int left = mid, right = mid;
                while(left >= low && nums[left] == target) {
                    left--;
                }
                while(right<=high && nums[right] == target) {
                    right++;
                }
                return new int[]{left+1, right-1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 54. 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        int i=0;
        int count = (Math.min(m, n)+1)/2;
        while (i < count) {
            for (int j=i; j<n-i; j++) {
                res.add(matrix[i][j]);
            }
            for (int j = i+1; j < m-i; j++) {
                res.add(matrix[j][(n-1)-i]);
            }

            for (int j = (n-1)-(i+1); j >= i && (m-1-i != i); j--) {
                res.add(matrix[(m-1)-i][j]);
            }
            for (int j = (m-1)-(i+1); j >= i+1 && (n-1-i) != i; j--) {
                res.add(matrix[j][i]);
            }
            i++;
        }
        return res;
    }

    /**
     * 56. 合并区间
     * @param intervals
     * @return
     */
    public int[][] mergeInterval(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[][] res = new int[intervals.length][2];
        int idx =-1;
        for (int[] ints : intervals) {
            if (idx == -1 || ints[0] > res[idx][1]) {
                res[++idx] = ints;
            } else {
                res[idx][1] = Math.max(res[idx][1], ints[1]);
            }
        }
        return Arrays.copyOf(res, idx+1);
    }

    public int[][] mergeInterval2(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a-> a[0]));
        int p=0, q=1;
        while (q < intervals.length) {
            if (intervals[p][0] < intervals[q][0] && intervals[p][1] < intervals[q][0]) {
                p++;
                intervals[p][0] = intervals[q][0];
                intervals[p][1] = intervals[q][1];
            } else {
                intervals[p][1] = Math.max(intervals[q][1], intervals[p][1]);
            }
            q++;
        }
        List<int[]> res = new ArrayList<>();
        for (int i=0; i<=p; i++) {
            res.add(intervals[i]);
        }
        return res.toArray(new int[][]{});
    }

    public int[][] mergeInterval3(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 78. 子集
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i=0; i< (1<<nums.length); i++) {
            List<Integer> list = new ArrayList<>();
            for (int j=0; j<nums.length; j++) {
                if (((i>>j) & 1) == 1) {
                    list.add(nums[j]);
                }
            }
            res.add(list);
        }
        return res;
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//        res.add(new ArrayList<Integer>());
//        for (Integer n : nums) {
//            int size = res.size();
//            for (int i = 0; i < size; i++) {
//                List<Integer> newSub = new ArrayList<Integer>(res.get(i));
//                newSub.add(n);
//                res.add(newSub);
//            }
//        }
//        return res;
    }

    /**
     * 二叉树的最小深度
     * 最小深度是从根节点到最近叶子结点的最短路径上的节点数量
     * @param root
     * @return
     */
    public static int minDept(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(minDept(root.left), min);
        }
        if (root.right != null) {
            min = Math.min(minDept(root.right), min);
        }
        return min+1;
    }

    public static int minDept2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int dept = 0;
        while(!queue.isEmpty()) {
            dept++;
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) {
                return dept;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return dept;
    }

    /**
     * 617. 合并二叉树
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return root1 == null ? root2 : root1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root1);
        queue.add(root2);
        while (queue.size() >0) {
            TreeNode r1 = queue.poll();
            TreeNode r2 = queue.poll();
            r1.val += r2.val;
            if (r1.left != null && r2.left != null) {
                queue.add(r1.left);
                queue.add(r2.left);
            } else if (r1.left == null) {
                r1.left = r2.left;
            }
            if (r1.right != null && r2.right != null) {
                queue.add(r1.right);
                queue.add(r2.right);
            } else if (r1.right == null) {
                r1.right = r2.right;
            }
        }
        return root1;
    }

    /**
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        while (root != null || !queue.isEmpty()) {
            while (root != null) {
                queue.add(root);
                root = root.left;
            }
            root = queue.poll();
            if (--k ==0) return root.val;
            root = root.right;
        }
        return -1;
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        levelOrderDfs(root, 1, res);
        return res;
    }

    private static void levelOrderDfs(TreeNode root, int index, List<List<Integer>> res) {
        if (root == null) return;
        if (res.size() < index) {
            res.add(new ArrayList<>());
        }
        res.get(index-1).add(root.val);
        if (root.left != null) {
            levelOrderDfs(root.left, index+1, res);
        }
        if (root.right != null) {
            levelOrderDfs(root.right, index+1, res);
        }
    }

    /**
     * 31. 下一个排列
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <=1) return;
        for (int i=len-1; i>=1; i--) {
            if (nums[i] > nums[i-1]) {
                for (int j=len-1; j>=i; j--) {
                    if (nums[j] > nums[i-1]) {
                        int temp = nums[i-1];
                        nums[i-1] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                }
                Arrays.sort(nums, i, len);
                return;
            }
        }
//        Arrays.sort(nums);
    }

    public static void nextPermutation3(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                Arrays.sort(nums, i, len);
                for (int j = i; j <len; j++) {
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
        return;
    }

    public static void nextPermutation2(int[] nums) {
        int len = nums.length;
        if (len <=1) return;
        int index = -1;
        for (int i=nums.length-2; i>=0; i--) {
            if (nums[i] < nums[i+1]) {
                index =i;
                break;
            }
        }
        if (index == -1) {
            reverse(nums, 0, nums.length-1);
            return;
        }
        int secIndex = -1;
        for (int i=nums.length-1; i>=0; i--) {
            if (nums[i] > nums[index]) {
                secIndex = i;
            }
        }
        swap(nums, index, secIndex);
        reverse(nums, index+1, nums.length-1);
    }

    /**
     * 46. 全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        return null;
    }

    /**
     * 39. 组合总和
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            dfs(candidates, i, len, target - candidates[i], path, res);

            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 55. 跳跃游戏
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if (nums == null) return false;
        // 前n-1个元素能够跳到的最远距离
        int k =0;
        for (int i=0; i<=k; i++) {
            // 第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            k = Math.max(k, temp);
            if (k >= nums.length-1) {
                return true;
            }
        }
        return false;
    }

    public static boolean canJump2(int[] nums) {
        if (nums == null) return false;
        // 从后往前跳
        int last = nums.length-1;
        for (int i=nums.length-1; i>=0; i--) {
            if (nums[i] + i >= last) {
                last=i;
            }
        }
        return last==0;
    }

    /**
     * 128. 最长连续序列
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            map.put(num, num);
        }
        int ans = 0;
        for(int num: nums) {
            if(!map.containsKey(num-1)) {
                int right = map.get(num);
                while(map.containsKey(right+1)) {
                    right = map.get(right+1);
                }
                map.put(num, right);
                ans = Math.max(ans, right-num+1);
            }
        }
        return ans;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                Node node = queue.poll();
                if (i<size-1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) return null;
        Node left = root;
        while (left.left != null) {
            Node node = left;
            while (node != null) {
                node.left.next = node.right;
                if (node.next != null) {
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            left = left.left;
        }
        return root;
    }

    /**
     * 64. 最小路径和
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (i==0 && j==0) continue;
                else if (i==0) {
                    grid[i][j] = grid[i][j-1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i-1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }

    /**
     * 62.不同路径
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 204. 计数质数
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
//        int cnt = 0;
//        for (int i=2; i<n; i++) {
//            if (isPrimes(i)) {
//                cnt++;
//            }
//        }
//        return cnt;
        int[] isPrime = new int[n];
        for (int i=0; i<n; i++) {
            isPrime[i] = 1;
        }
        int ans = 0;
        for (int i=2; i<n; ++i) {
            if (isPrime[i] == 1) {
                ans++;
                if (i*i <n) {
                    for (int j=i*i; j<n; j+=i) {
                        isPrime[j] =0;
                    }
                }
            }
        }
        return ans;
    }

    private static boolean isPrimes(int num) {
        for (int i=2; i*i<=num; i++) {
            if (num % i ==0) {
                return false;
            }
        }
        return true;
    }

    private int[] help(String s, int left, int right) {
        while (left>=0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left+1, right-left-1};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

}