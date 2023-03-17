package com.example.algorithm.offer;

import java.util.*;

public class OfferHot2 {
    public static void main(String[] args) {
        generateParenthesis(3);
        divide(60, 8);
        trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(1);
        ListNode l6 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        partition2(l1, 3);

        l2 = new ListNode(4);
        l2.next = l4;
        l4.next = l1;
        l1.next = l3;

        hammingWeight(00000000000000000000000000001011);
        findDuplicate(new int[]{1, 3, 4, 2, 2});
        findRepeatNumber(new int[]{3, 4, 2, 1, 1, 0});
        mul(12, 3);
        merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});

        subsets(new int[]{1, 2, 3});
        subsets2(new int[]{1, 2, 3});
//        search(new int[]{4,5,6,7,0,1,2}, 0);
        search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8);
        ListNode ll1 = new ListNode(1);
        ListNode ll2 = new ListNode(2);
        ListNode ll3 = new ListNode(3);
        ListNode ll4 = new ListNode(4);
        ListNode ll5 = new ListNode(5);
        ll1.next = ll2;
        ll2.next = ll3;
        ll3.next = ll4;
        ll4.next = ll5;
        rotateRight3(ll1, 2);
        System.out.println(isMatch("()("));
    }

    public static long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
            k >>= 1;
            a += a;
        }
        return ans;
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                res = Math.max(res, (j - i) * height[i]);
                i++;
            } else {
                res = Math.max(res, (j - i) * height[j]);
                j--;
            }
        }
        return res;
    }

    /**
     * 13. 罗马数字转整数
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int sum = 0;
        // 要讲I V X L 等字转换成 1 5 10 50
        int preNum = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            int num = s.charAt(i);
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) return combinations;
        Map<Character, String> phoneMap = new HashMap<>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    private static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    /**
     * 匹配括号
     * @param str
     * @return
     */
    public static boolean isMatch(String str) {
        Stack<Character> chars = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);
            if (currChar == '(') {
                chars.push(currChar);
            } else if (currChar == ')') {
                Character t = chars.pop();
                if (t != '(') {
                    return false;
                }
            }
        }
        if (chars.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 22. 括号生成
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        if (n == 0) return res;
        getParenthesis("", n, n);
        return res;
    }

    static List<String> res = new ArrayList<>();

    private static void getParenthesis(String str, int left, int right) {
        if (left == 0 && right == 0) return;
        if (left == right) {
            // 剩余左右括号相等，下一个只能用左括号
            getParenthesis(str + "(", left - 1, right);
        } else if (left < right) {
            // 剩余的左括号小于右括号，下一个可以用左括号也可以用右括号
            if (left > 0) {
                getParenthesis(str + "(", left - 1, right);
            }
            getParenthesis(str + ")", left, right - 1);
        }
    }

    /**
     * 两数相除
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MIN_VALUE;
        }
        boolean flag = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0;
        long m = Math.abs(dividend);
        long n = Math.abs(divisor);
        int ans = 0;
        while (m >= n) {
            long temp = n;
            int count = 1;
            while (m >= (temp << 1)) {
                temp = temp << 1;
                count = count << 1;
            }
            ans = ans + count;
            m = m - temp;
        }
        return flag ? ans : -ans;
    }

    /**
     * 42. 接雨水
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;
        // 单调不增栈，height元素作为右墙依次入栈
        // 出现入栈元素比栈顶大时，说明在右墙左侧形成低洼处
        int water = 0;
        Stack<Integer> stack = new Stack<>();
        for (int right = 0; right < height.length; right++) {
            // 栈不为空，且当前元素比栈顶大
            while (!stack.isEmpty() && height[right] > height[stack.peek()]) {
                int bottom = stack.pop();
                // 查看栈里还有没有东西
                //有 右墙+有低洼 没有 则不做处理
                if (stack.isEmpty()) {
                    break;
                }

                // 左墙位置及左墙 右墙 低洼处的高度
                int left = stack.peek();
                int leftHeight = height[left];
                int rightHeight = height[right];
                int bottomHeight = height[bottom];
                water += (right - left - 1) * (Math.min(leftHeight, rightHeight) - bottomHeight);
            }
            stack.push(right);
        }
        return water;
    }

    /**
     * 86. 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }

    /**
     * 86. 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition2(ListNode head, int x) {
        ListNode cur = head;
        ListNode small = head;
        ListNode large = head;
        ListNode smallHead = null;
        ListNode largeHead = null;
        while (small != null && small.val >= x) {
            small = small.next;
        }
        while (large != null && large.val < x) {
            large = large.next;
        }
        while (cur != null) {
            if (cur.val >= x) {
                if (largeHead != null) {
                    largeHead.next = cur;
                }
                largeHead = cur;
            } else {
                if (smallHead != null) {
                    smallHead.next = cur;
                }
                smallHead = cur;
            }
            cur = cur.next;
        }
        largeHead.next = null;
        smallHead.next = large;
        return small;
    }

    /**
     * 剑指 Offer II 026. 重排链表
     * @param head
     */
    public void reorderList(ListNode head) {
        // 将主链表进行截断，划分为前一段&&后一段
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        ListNode afterNode = slow.next;
        slow.next = null;
        ListNode newNode = reverse(afterNode);
        // 重构链表
        while (newNode != null) {
            ListNode next1 = head.next;
            head.next = newNode;
            ListNode next2 = newNode.next;
            newNode.next = next1;
            newNode = next2;
            head = next1;
        }
    }


    /**
     * 116. 填充每个节点的下一个右侧节点指针
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Node pre = root;
        while (pre.left != null) {
            // 循环条件时当前节点的left不为空，当只有根节点或所有叶子结点都出串联完成后循环退出
            Node temp = pre;
            while (temp != null) {
                // 将temp左右节点都串联起来
                temp.left.next = temp.right;
                if (temp.next != null) {
                    temp.right.next = temp.next.left;
                }
                temp = temp.next;
            }
            pre = pre.left;
        }
        return root;
    }

    /**
     * 148. 排序链表
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
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
     * 125. 验证回文串
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stringBuffer.append(Character.toLowerCase(ch));
            }
        }
        int n = stringBuffer.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(stringBuffer.charAt(left)) != Character.toLowerCase(stringBuffer.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count = count + n & 1;
            n = n << 1;
        }
        return count;
    }

    /**
     * 202. 快乐数
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        int slow = n, fast = squareSum(n);
        while (slow != fast) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }
        return slow == 1;
    }

    public static int squareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

    /**
     * 287. 寻找重复数
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while (pre1 != pre2) {
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }

    /**
     * 56. 合并区间
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或当前区间的起始位置 > 结果数组中最后区间的终止位置
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

    /**
     * 152. 乘积最大子数组
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        int max = Integer.MAX_VALUE, imax = 1, imin = 1;
        for (int i = 0; i <= nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            max = Math.max(max, imax);
        }
        return max;
    }

    /**
     * 78. 子集
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new ArrayList<Integer>();
            for (int j = 0; j < nums.length; j++) {
                int k = (i >> j);
                int h = (i >> j) & 1;
                if (((i >> j) & 1) == 1) sub.add(nums[j]);
            }
            res.add(sub);
        }
        return res;
    }

    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;
    }

    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;
        int left = 0, right = len - 1;
        // 1、首先明白，旋转数组后，从中间划分，一定有一边是有序的
        // 2、由于一定有一边是有序的，所以根据有序的两个边界值来判断目标值在有序一边还是无序一边
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 61. 旋转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode slow = temp, fast = head;
        ListNode pre = head;
        int listLen = 0;
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                fast = head.next;
                int temp1 = (k - 1) % listLen;
                for (int j = 0; j < temp1; j++) {
                    fast = fast.next;
                }
                break;
            }
            fast = fast.next;
            listLen++;
        }
        if (fast == null) return head;
        while (fast != null) {
            if (fast.next == null) pre = fast;
            fast = fast.next;
            slow = slow.next;
        }
        pre.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }

    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || k == 0) return head;
        int n = 0;
        ListNode tail = null;
        for (ListNode p = head; p != null; p = p.next) {
            tail = p;
            n++;
        }
        k %= n;
        ListNode pre = head;
        for (int i = 0; i < n - k - 1; i++) {
            pre = pre.next;
        }
        tail.next = head;
        head = pre.next;
        pre.next = null;
        return head;
    }

    public static ListNode rotateRight3(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int len = 0;
        ListNode pre = head, temp = pre;
        while (temp.next != null) {
            temp = temp.next;
            len++;
        }
        temp.next = head;
        k = k % (len + 1);
        if (k == 0) {
            pre = temp.next;
            temp.next = null;
            return pre;
        }
        temp = temp.next;
        for (int i = 0; i < len - k; i++) {
            temp = temp.next;
        }
        pre = temp.next;
        temp.next = null;
        return pre;
    }

    /**
     * 88. 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;
        while (n > 0) {
            if (m > 0 && nums1[m - 1] > nums2[n - 1]) {
                nums1[--len] = nums1[--m];
            } else {
                nums1[--len] = nums2[--n];
            }
        }
    }

    /**
     * 215. 数组中的第K个最大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return 0;
    }

    public static int getSum(int[] arr, int target) {
        int len = arr.length;
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            int sum = arr[i];
            for (int j = i + 1; j < len; j++) {
                if (sum + arr[j] == target) {
                    res = Math.min(res, j - i + 1);
                } else if (sum + arr[j] > target) {
                    break;
                } else {
                    sum = sum + arr[i];
                }
            }
        }
        return res;
    }

    /**
     * 25. K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = pre.next, next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
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
     * 328. 奇偶链表
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 21. 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode cur = new ListNode(0);
        ListNode pre = cur;
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
        return pre.next;
    }

    /**
     * 160. 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    /**
     * 剑指 Offer II 009. 乘积小于 K 的子数组
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k<=1) return 0;
        int len = nums.length;
        int left = 0, right = 0;
        int count =0, product =1;
        while (right<len) {
            product *= nums[right];
            right++;
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            count += (right-left);
        }
        return count;
    }


}