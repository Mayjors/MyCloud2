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
        nextPermutation(new int[]{1, 2, 3});

        ListNode node = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        deleteNode(node, 5);
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
                if (s.charAt(l) == s.charAt(r) && (r-l)<=2 || dp[l+1][r-1]) {
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
//        List<List<Integer>> res = new ArrayList<>();
//        for (int i=0; i< (1<<nums.length); i++) {
//            List<Integer> list = new ArrayList<>();
//            for (int j=0; j<nums.length; j++) {
//                if (((i>>j) & 1) == 1) {
//                    list.add(nums[j]);
//                }
//            }
//            res.add(list);
//        }
//        return res;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<Integer>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;
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
}