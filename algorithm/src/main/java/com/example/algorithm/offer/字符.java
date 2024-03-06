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
        System.out.println(findMedianSortedArrays2(new int[]{1, 3, 5}, new int[]{2, 4}));
        System.out.println(restoreIpAddresses("255255255255"));
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

    /**
     * 121. 买卖股票的最佳时机
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
