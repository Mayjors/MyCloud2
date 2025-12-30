package com.example.algorithm.news;

import java.util.*;

public class Offer {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(subsets(new int[] {1, 2, 3}));
        System.out.println(subsets2(new int[] {1, 2, 3}));
        System.out.println(findNumbers(new int[] {555,901,482,1771}));
    }


    public static int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            int n = 0;
            while (num > 0) {
                num = num/10;
                n++;
            }
            if (n >0 && n %2 ==0) {
                ans++;
            }
        }
        return ans;
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
                return new int[]{map.get(target-nums[i]), i};
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
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(key);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 128. 最长连续序列
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, num);
        }
        int ans = 0;
        for (int num : nums) {
            int cur = num;
            if (!map.containsKey(cur - 1)) {
                int count = 1;
                while (map.containsKey(cur +1)) {
                    cur++;
                    count++;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 1) {
            return findKth(nums1, nums2, len / 2 + 1);
        } else {
            return (findKth(nums1, nums2, len / 2) + findKth(nums1, nums2, len / 2 + 1)) / 2.0;
        }
    }


    public int findKth(int[] nums1, int[] nums2, int k) {
        if (nums1.length > nums2.length) {
            return findKth(nums2, nums1, k);
        }
        if (nums1.length == 0) {
            return nums2[k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[0], nums2[0]);
        }
        int i = Math.min(k / 2, nums1.length);
        int j = k - i;
        if (nums1[i - 1] > nums2[j - 1]) {
            return findKth(nums1, Arrays.copyOfRange(nums2, j, nums2.length), k - j);
        } else {
            return findKth(Arrays.copyOfRange(nums1, i, nums1.length), nums2, k - i);
        }
    }

    /**
     * 33. 搜索旋转排序数组
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid -1;
                } else {
                    left = mid +1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid +1;
                } else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left = 0, right = 0;
        int ans = 0;
        Set<Character> set = new HashSet<>();
        while (right < len) {
            char c = s.charAt(right);
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            right++;
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    /**
     * 239. 滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i< nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        int[] res = new int[nums.length - k +1];
        res[0] = max;
        for (int i = 0; i< nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else if (nums[i-k] == max) {
                max = Integer.MIN_VALUE;
                for (int j = i-k+1; j<=i; j++) {
                    max = Math.max(max, nums[j]);
                }
            } else {
                max = Math.max(max, nums[i]);
            }
        }
        return res;
    }

    /**
     * 977. 有序数组的平方
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int left = 0, right = len -1;
        for (int i = len-1; i>=0; i--) {
            int square = 0;
            if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                square = nums[left] * nums[left];
                left++;
            } else {
                square = nums[right] * nums[right];
                right--;
            }
            res[i] = square;
        }
        return res;
    }
    /**
     * 53. 最大子数组和
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
//        int pre = 0, max = nums[0];
//        for (int num : nums) {
//            pre = Math.max(pre + num, num);
//            max = Math.max(max, pre);
//        }
//        return max;
       int result = Integer.MIN_VALUE;
       for (int i = 0; i<nums.length; i++) {
           int sum = 0;
           for (int j = i; j<nums.length; j++) {
               sum = sum + nums[j];
               result = Math.max(result, sum);
           }
       }
       return result;
    }

    /**
     * 189. 轮转数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k %len;
        int left = 0, right = nums.length-1;
        reverse(nums, left, right);
        reverse(nums, 0, k-1);
        reverse(nums, k, right);
    }

    /**
     * 238. 除自身以外数组的乘积
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int left = 1;
        for (int i = 0; i< len; i++) {
            res[i] = left;
            left *= nums[i];
        }
        for (int i = len-1; i>=0; i--) {
            res[i] *= left;
            left *= nums[i];
        }
        return res;
    }

    /**
     * 283.移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    /**
     * 383. 赎金信
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] map = new int[26];
        for (char c : magazine.toCharArray()) {
            map[c - 'a']++;
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
     * 35. 搜索插入位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid +1;
            }
        }
        return nums[left] < target ? left +1 : left;
    }

    /**
     * 160. 相交链表
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a.next == null ? headB : a.next;
            b = b.next == null ? headA : b.next;
        }
        return a;
    }

    /**
     * 206. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null, cur = head;
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
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null || fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverseList(slow);
        fast = head;
        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 92. 反转链表 II
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pre = dump;
        for (int i = 0; i<left-1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i = 0; i<right-left; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dump.next;
    }

    /**
     * 141. 环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 21. 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list2;
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

    /**
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
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
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode start = cur.next;
            ListNode end = cur.next.next;
            cur.next = end;
            start.next = end.next;
            end.next = start;
            cur = start;
        }
        return dummy.next;
    }

    /**
     * 61. 旋转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode fast = head, slow = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            return fast;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }

    /*****************************************/
    /****************动态规划******************/
    /*****************************************/

    /**
     * 200. 岛屿数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i< grid.length; i++) {
            for (int j = 0; j< grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    landsDfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void landsDfs(char[][] grid, int i, int j) {
        if (i< 0 || i>= grid.length || j< 0 || j>= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        landsDfs(grid, i - 1, j);
        landsDfs(grid, i + 1, j);
        landsDfs(grid, i, j - 1);
        landsDfs(grid, i, j + 1);
    }

    /**
     * 78. 子集
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
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

    public static List<List<Integer>> subsets2(int[] nums) {
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
     * 74. 搜索二维矩阵
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int cow = 0;
        while (row >= 0 && cow < matrix[0].length) {
            if (matrix[row][cow] == target) {
                return true;
            } else if (matrix[row][cow] > target) {
                row--;
            } else {
                cow++;
            }
        }
        return false;
    }

    /**
     * 416. 分割等和子集
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11]
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum /2;
        boolean[][] dp = new boolean[len][target +1];
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i-1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
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