package com.example.algorithm.offer;

import java.text.DecimalFormat;
import java.util.*;

public class Hot {
    public static void main(String[] args) {
        Integer s = lengthOfLongestSubstring("abcabcbb");
        System.out.println(s);
        double d = findMedianSortedArrays2(new int[]{1, 3}, new int[]{2, 4});
        System.out.println(d);
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        maxDepth(root);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(6);
        flatten(root1);
//        flatten2(root1);
        dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
        findUnsortedSubarray(new int[]{2,3,6,4,8,5,10,9,11,15});
        subarraySum(new int[]{1,2,3}, 3);
        List list = permute(new int[]{1,2,3});
        System.out.println(Arrays.toString(new List[]{list}));
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
        throw new RuntimeException("");
    }

    /**
     * 2. 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
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
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < n; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(map.get(c), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
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
                while (j != n) {
                    nums[len++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
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

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int a = 0, b = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (a < m && (b == n || nums1[a] < nums2[b])) {
                right = nums1[a++];
            } else {
                right = nums2[b++];
            }
        }
        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // 因为数组是从索引0开始的，因此我们在这里必须+1，即索引(k+1)的数，才是第K个数
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        // 将偶数和奇数的情况合并,如果是奇数，会求两次同样的k
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        // 因为索引和算数不同6-0 = 6，但是是有7个数的，因为end初始就是数组长度-1构成
        // 最后len代表当前数组(也可能是经过递归排除后的数组)，符合当前条件的元素的个数
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        //就是如果len1长度小于len2，把getKth()中参数互换位置，即原来的len2就变成了len1，即len1，永远比len2小
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        //如果一个数组中没有了元素，那么即从剩余数组nums2的其实start2开始加k再-1.
        //因为k代表个数，而不是索引，那么从nums2后再找k个数，那个就是start2 + k-1索引处就行了。因为还包含nums2[start2]也是一个数。因为它在上次迭代时并没有被排除
        if (len1 == 0) return nums2[start2 + k - 1];

        //如果k=1，表明最接近中位数了，即两个数组中start索引处，谁的值小，中位数就是谁(start索引之前表示经过迭代已经被排出的不合格的元素，即数组没被抛弃的逻辑上的范围是nums[start]--->nums[end])。
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        //为了防止数组长度小于 k/2,每次比较都会从当前数组所生长度和k/2作比较，取其中的小的(如果取大的，数组就会越界)
        //然后素组如果len1小于k / 2，表示数组经过下一次遍历就会到末尾，然后后面就会在那个剩余的数组中寻找中位数
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        //如果nums1[i] > nums2[j]，表示nums2数组中包含j索引，之前的元素，逻辑上全部淘汰，即下次从J+1开始。
        //而k则变为k - (j - start2 + 1)，即减去逻辑上排出的元素的个数(要加1，因为索引相减，相对于实际排除的时要少一个的)
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    /**
     * 5. 最长回文子串
     * 中心扩散方法
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int left = 0, right = 0;
        int len = 0;
        int maxStart = 0, maxLen = 0;
        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            //向左侧扩展，直到超过边界或遇到与中心字符不等跳出while循环
            while (len >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            //向右侧扩展，直到超过边界或遇到与中心字符不等跳出while循环
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            //同时向左右两侧扩展
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                //注意此处，在最后一次循环中，即最长回文子串索引为：i~j，此时的left=i-1，right=j+1
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
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0, maxEnd = 0, maxLen = 1;
        boolean[][] dp = new boolean[strLen][strLen];
        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

    public static String longestPalindrome3(String s) {
        if (s == null || s.length() < 2) return s;
        int len = s.length();
        int maxLen = 0;
        int[] res = new int[2];
        for (int i = 0; i < len; i++) {
            int[] odd = help(s, i, i);
            int[] even = help(s, i, i + 1);
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }

    private static int[] help(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right - left + 1};
    }

    /**
     * 21. 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        if (list1 == null) {
//            return list2;
//        } else if (list2 == null) {
//            return list1;
//        } else if (list1.val < list2.val) {
//            list1.next = mergeTwoLists(list1.next, list2);
//            return list1;
//        } else {
//            list2.next = mergeTwoLists(list1, list2.next);
//            return list2;
//        }
        ListNode head = new ListNode(-1);
        ListNode prev = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;
        return head.next;
    }

    /**
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
//        if (root.left == null && root.right == null) return true;
//        // 用队列保存
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(root.left);
//        queue.add(root.right);
//        while (queue.size() > 0) {
//            TreeNode left = queue.removeFirst();
//            TreeNode right = queue.removeFirst();
//            if (left == null && right == null) {
//                continue;
//            }
//            if (left == null || right == null) return false;
//            if (left.val != right.val) return false;
//            // 将左节点的左孩子，右节点的右孩子放入队列
//            queue.add(left.left);
//            queue.add(right.right);
//            queue.add(left.right);
//            queue.add(right.left);
//        }
        return checkSymmetric(root.left, root.right);
    }

    static boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
    }

    /**
     * 102. 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
//        int left = maxDepth(root.left);
//        int right = maxDepth(root.right);
//        return Math.max(left, right) +1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buileTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private static TreeNode buileTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int in_start, int in_end) {
        if (p_start == p_end) return null;
        int val = preorder[p_start];
        TreeNode root = new TreeNode(val);
        // 找到中序遍历的root位置
        int index = 0;
        for (int i = in_start; i < in_end; i++) {
            if (val == inorder[i]) {
                index = i;
                break;
            }
        }
        int leftNum = index - in_start;
        root.left = buileTreeHelper(preorder, p_start + 1, p_start + leftNum + 1, inorder, in_start, index);
        root.right = buileTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, index + 1, in_end);
        return root;
    }

    /**
     * 114. 二叉树展开为链表
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            // 左子树为null， 直接考虑下一个节点
            if (curr.left == null) {
                curr = curr.right;
            } else {
                TreeNode pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将原来的右子树接到左子树的最右边节点
                pre.right = curr.right;
                // 将左子树插入到右子树的地方
                curr.right = curr.left;
                curr.left = null;
                // 考虑下一个节点
            }
            curr = curr.right;
        }
        System.out.println(root);
    }

    public static void flatten2(TreeNode root) {
        while (root != null) {
            TreeNode p = root.left;
            if (p != null) {
                while (p.right != null) p = p.right;
                p.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
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
     * 136. 只出现一次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    /**
     * 128. 最长连续序列
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, num);
        }
        int ans = 0;
        for (int num: nums) {
            if (!map.containsKey(num-1)) {
                int right = map.get(num);
                while (map.containsKey(right +1)) {
                    map.remove(right);
                    right = map.get(right +1);
                }
                map.put(num, right);
                ans = Math.max(ans, right-num +1);
            }
        }
        return ans;
    }

    /**
     * 148. 排序链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

    /**
     * 平方根
     * @param total
     * @return
     */
    public static Double sqrt(int total) {
        double left =0.0, right = Double.valueOf(total);
        double mid = (left + right) /2;
        while(left<right) {
            if(mid * mid < total) {
                left = mid;
            } else {
                right = mid;
            }
            mid = (left + right) /2;
        }
        DecimalFormat format = new DecimalFormat("#.000");
        String d = format.format(mid);
        return Double.parseDouble(d);
    }

    /**
     *
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] result = new int[len];
//        for (int i=0; i<len; i++) {
//            int current = temperatures[i];
//            if (current < 100) {
//                for (int j=i+1; j<len;j++) {
//                    if (temperatures[j] > current) {
//                        result[i] = j-i;
//                        break;
//                    }
//                }
//            }
//        }
        // 从左向右遍历
        for (int i=len-2; i>=0; i--) {
            for (int j=i+1; i<len;j+=result[j]) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j-i;
                    break;
                } else if (result[j] == 0) {
                    // 遇到0表示后面不会有更大的值，那当前值为0
                    result[i] = 0;
                    break;
                }
            }
        }
        // 队列模式
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i=0; i<len; i++) {
            while (!queue.isEmpty() && temperatures[queue.peekLast()] < temperatures[i]) {
                int idx = queue.pollLast();
                result[idx] = i-idx;
            }
            queue.addLast(i);
        }

        // 队列模式
        result = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<len;i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i-idx;
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 581. 最短无序连续子数组
     * @param nums
     * @return
     */
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
//        int i=0, j=n-1;
//        int[] arr = nums.clone();
//        Arrays.sort(arr);
//        while (i<=j && nums[i] == arr[i]) i++;
//        while (i<=j && nums[j] == arr[j]) j--;
//        return j-i+1;
        // 方法二 线性扫描，找到递增的分割点
        // 再比较中间数组中的最大最小值
//        int i=0, j=n-1;
//        while (i<j && nums[i] <= nums[i+1]) i++;
//        while (i<j && nums[j] >= nums[j-1]) j--;
//        int l = i, r=j;
//        int min = nums[i], max = nums[j];
//        for (int u = l; u <= r; u++) {
//            if (nums[u] < min) {
//                while (i >= 0 && nums[i] > nums[u]) i--;
//                min = i >= 0 ? nums[i] : 0;
//            }
//            if (nums[u] > max) {
//                while (j < n && nums[j] < nums[u]) j++;
//                max = j < n ? nums[j] : n;
//            }
//        }
//        return j==i ? 0: (j-1)-(i+1)+1;
        int max = Integer.MIN_VALUE, right = -1;
        int min = Integer.MAX_VALUE, left = -1;
        for (int i=0; i< n; i++) {
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }
            if (min < nums[n-i-1]) {
                left = n-i-1;
            } else {
                min = nums[n-i-1];
            }
        }
        return right == -1 ? 0 : right-left+1;
    }

    /**
     * 560. 和为 K 的子数组
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int count = 0, len = nums.length;
        for (int left = 0; left<len; left++) {
            int sum = 0;
            for (int right = left; right<len; right++) {
                sum += nums[right];
                if (sum == k) {
                    count++;
                }
            }
        }
        count =0;
        // 前缀和方法，计算出 nums[i+1]前面的所有数据和
        int[] preSum = new int[len+1];
//        preSum[0] = 0;
        for (int i=0; i<len; i++) {
            preSum[i+1] = preSum[i] + nums[i];
        }
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
     * 全排列
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
     * 226. 翻转二叉树
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
//        TreeNode temp = root.right;
//        root.right = root.left;
//        root.left  = temp;
//        invertTree(root.left);
//        invertTree(root.right);
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            TreeNode left = temp.left;
            temp.left = temp.right;
            temp.right = left;
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            int size = stack.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode cur = stack.pop();
//                TreeNode temp = cur.left;
//                cur.left = cur.right;
//                cur.right = temp;
//                if (cur.right != null) {
//                    stack.push(cur.right);
//                }
//                if (cur.left != null) {
//                    stack.push(cur.left);
//                }
//            }
//        }
        return root;
    }

}