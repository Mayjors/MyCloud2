package com.example.algorithm.offer;

import java.util.*;

public class OfferHot {
    public static void main(String[] args) {
        productExceptSelf(new int[]{1,2,3,4});
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


    /**
     * 53. 最大子数组和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, max = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
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
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 206. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
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
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        ListNode left = head;
        ListNode right = reverseList(slow);
        while (right != null) {
            if (right.val != left.val) {
                return false;
            }
            right = right.next;
            left = left.next;
        }
        return true;
    }


    /**
     * 21. 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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

    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int count = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + count;
            cur.next = new ListNode(sum % 10);
            count = sum / 10;
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (count != 0) {
            cur.next = new ListNode(count);
        }
        return pre.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode left = pre.next;
            ListNode right = pre.next.next;
            pre.next = right;
            left.next = right.next;
            right.next = left;
            pre = left;
        }
        return dummy.next;
    }

    /**
     * 25. K 个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }
            ListNode next = tail.next;
            tail.next = null;
            ListNode cur = pre.next;
            pre.next = reverseList(cur);
            cur.next = next;
            pre = cur;
        }
        return dummy.next;
    }


    /**
     * 148. 排序链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 1.递归结束条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        right = sortList(right);
        return mergeTwoLists(left, right);
    }

    /**
     * 23. 合并K个升序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return dummy.next;
    }


    /**
     * 200. 岛屿数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    /**
     * 46. 全排列
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if(len ==0) {
            return res;
        }
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        backtrack(nums, path, used, res);
        return res;
    }

    private static void backtrack(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=0; i<nums.length; i++) {
//            if (!used[i]) {
//                path.add(nums[i]);
//                used[i] = true;
//                backtrack(nums, path, used, res);
//                used[i] = false;
//                path.remove(path.size()-1);
//            }
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            backtrack(nums, path, used, res);
            path.remove(path.size()-1);
        }
    }


    /**
     * 78. 子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        return null;
    }

    /**
     * 22. 括号生成
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        backtrack2("", n, n, res);
        return res;
    }


    private void backtrack2(String str, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left > 0) {
            backtrack2(str + "(", left - 1, right, res);
        }
        if (right > left) {
            backtrack2(str + ")", left, right - 1, res);
        }
    }


    /**
     * 35. 搜索插入位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len -1;
        while (left <= right) {
            int mid = left + (right -left)/2;
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
     * 74. 搜索二维矩阵
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length -1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }


    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                left = mid;
                right = mid;
                while (left >0 && nums[left] == target) {
                    left--;
                }
                while (right <nums.length && nums[right] == target) {
                    right++;
                }
                return new int[]{left+1, right-1};
            } else if (nums[mid] < target) {
                left = mid +1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 75. 颜色分类
     * @param nums
     */
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length -1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 238. 除自身以外数组的乘积
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = left;
            left *= nums[i];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }


    /**
     * 4. 寻找两个正序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        int len = 0;
        if (m == 0 || n == 0) {
            if (m == 0) {
                nums = nums2;
                len = n;
            } else {
                nums = nums1;
                len = m;
            }
            if (len % 2 == 0) {
                return (nums[len / 2 - 1] + nums[len / 2]) / 2.0;
            } else {
                return nums[len / 2];
            }
        }
        int i = 0, j = 0;
        while (len != (m + n)) {
            if (i == m) {
                while (j!= n) {
                    nums[len++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i!= m) {
                    nums[len++] = nums1[i++];
                }
                break;
            }
            if (nums1[i] < nums2[j]) {
                nums[len++] = nums1[i++];
            } else {
                nums[len++] = nums2[j++];
            }
        }
        if (len % 2 == 0) {
            return (nums[len / 2 - 1] + nums[len / 2]) / 2.0;
        } else {
            return nums[len / 2];
        }
    }

    /**
     * 数组中的最大K个数
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        initHeap(nums);
        for(int i = 0; i < k-1; i++){
            swap(nums, 0, nums.length - i - 1);
            maxHeapify(nums, 0, nums.length - i - 1);
        }
        return nums[0];
    }

    private void initHeap(int[] arr) {
        for (int i = arr.length/2 -1; i>=0; i--) {
            maxHeapify(arr, i, arr.length);
        }
    }

    /**
     * 最大堆
     * @param nums
     * @param i
     * @param heapSize
     */
    private void maxHeapify(int[] nums, int i, int heapSize) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(nums, i, largest);
            maxHeapify(nums, largest, heapSize);
        }
    }


    /**
     * 322. 零钱兑换
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 300. 最长递增子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
