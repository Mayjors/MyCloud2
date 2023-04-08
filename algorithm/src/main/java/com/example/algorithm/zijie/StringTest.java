package com.example.algorithm.zijie;

import java.util.*;

public class StringTest {
    public static void main(String[] args) {
        int a = lengthOfLongestSubstring("abcbd");
        System.out.println(a);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = swapPairs(node1);
        System.out.println(node);
        isPalindrome(121);
        isValid("()[]{}");
    }

    /**
     * 74. 搜索二维矩阵
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length-1, col = 0;
        while (row >= 0 && col <matrix[0].length) {
            int num = matrix[row][col];
            if (num == target) {
                return true;
            } else if (num > target) {
                row--;
            } else {
                col ++;
            }
        }
        return false;
    }

    /**
     * 无重复字符的最长子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int end = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int ans =0, n = s.length();
        Set<Character> set = new HashSet<>();
        Integer left = 0, right = 0;
        while (right<n) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            set.add(s.charAt(right++));
            ans = Math.max(ans, right-left);
        }
        return ans;
    }

    /**
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode a = temp.next;
            ListNode b = temp.next.next;
            temp.next = b;
            a.next = b.next;
            b.next = a;
            temp = a;
        }
        return pre.next;
    }

    /**
     * 字符串相乘
     *     * 计算形式
     *     *    num1
     *     *  x num2
     *     *  ------
     *     *  result
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String res = "0";
        for (int i = num2.length() -1; i>=0; i--) {
            int carry = 0;
            // 保存num2第i位数字与num1相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补0
            for (int j =0; j< num2.length()-1-i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';
            // num2的第i位数字n2 与num1相乘
            for (int j=num1.length()-1; j>=0 || carry !=0; j--) {
                int n1 = j<0 ? 0 : num1.charAt(j) -'0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    /**
     * 反转字符串中的单词
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        char[] a = s.toCharArray();
        int left =0, right=s.length()-1;
        // 清除字符串两边的空格
        while (a[left] == ' ') {
            left++;
        }
        while (a[right] == ' ') {
            right--;
        }
        StringBuilder sb = new StringBuilder();
        // 开始填单词
        while (left <= right) {
            int index = right;
            // index向左遍历找到第一个空格
            while (index>= left && a[index] !=' ') {
                index--;
            }
            // 找到第一个空格，i = index+1 后移到字符串出现的位置
            for (int i=index+1; i<=right; i++) {
                sb.append(a[i]);
            }
            // 如果不是最后一个单词，就添加空格
            if (index > left) sb.append(' ');
            // 使用index 指针，跳过中间可能出现的空格
            while (index >= left && a[index] == ' ') {
                index--;
            }
            // 把right放到下一个单词出现的位置，继续循环
            right = index;
        }

        return sb.toString();
    }

    public static String reverseWords2(String s) {
        StringBuilder sb = new StringBuilder();
        int left=0, right = s.length()-1;
        while (s.charAt(right) == ' ') {
            right--;
        }
        while (s.charAt(left) == ' ') {
            left++;
        }
        while (left < right) {
            int index = right;
            // 找到要存储的单词
            while (index >= left && s.charAt(index) != ' ') {
                index--;
            }
            // 存储sb
            sb.append(s, index+1, right+1);
            if (index > left) {
                sb.append(" ");
            }
            // 找到下一个单词
            while (index >= left && s.charAt(index) == ' ') {
                index--;
            }
            right = index;
        }
        return sb.toString();
    }

    /**
     * 200. 岛屿数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int count=0;
        for (int r = 0; r<grid.length; r++) {
            for (int c = 0; c<grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int r, int c) {
        // 判断base case
        if (!isArea(grid, r, c)) {
            return;
        }
        if (grid[r][c] !='1') {
            return;
        }
        grid[r][c] = '2'; // 将扫描过的格子标记为已遍历
        // 访问上下左右四个相邻节点
        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c+1);
    }
    public void dfs2(char[][] grid,int i, int j){
        //防止 i 和 j 越界，也就是防止超出岛屿（上下左右）的范围。特别注意当遍历到海洋的时候也退出循环
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]=='0') return;
        //将遍历过的陆地改为海洋，防止重复遍历
        grid[i][j]='0';
        //上，
        dfs(grid,i+1,j);
        //下
        dfs(grid,i-1,j);
        //右
        dfs(grid,i,j+1);
        //左
        dfs(grid,i,j-1);
    }
    /**
     * 判断坐标(r,c)是否在网格中
     * @param grid
     * @param r
     * @param c
     * @return
     */
    private boolean isArea(char[][] grid, int r, int c) {
        return 0 <= r && r<grid.length && 0<=c && c<grid[0].length;
    }

    /**
     * 670. 最大交换
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            list.add(num % 10);
            num /= 10;
        }
        int n = list.size(), ans = 0;
        int[] index = new int[n];
        for (int i=0, j=0; i<n; i++) {
            if (list.get(i) > list.get(j)) {
                j=i;
                index[i] = j;
            }
        }
        for (int i=n-1; i>=0; i--) {
            if (list.get(index[i]) != list.get(i)) {
                int c = list.get(index[i]);
                list.set(index[i], list.get(i));
                list.set(i, c);
                break;
            }
        }
        for (int i = n - 1; i >= 0; i--) ans = ans * 10 + list.get(i);
        return ans;
    }

    /**
     * 9.回文数
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        // 边界判断
        if (x <0) return false;
        int div =1;
        // 获取x的位数
        while (x / div >= 10) div *=10;
        while (x>0) {
            int left = x/div;
            int right = x%10;
            if (left != right) return false;
            x = (x % div) /10;
            div /= 100;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x<0 || (x % 10 == 0 && x !=0)) return false;
        int reverseX = 0;
        while (x>reverseX) {
            reverseX = reverseX * 10 + x %10;
            x /=10;
        }
        return x== reverseX || x == reverseX /10;
    }

    /**
     * 20. 有效的括号
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c=='(') {
                stack.push(')');
            } else if (c=='{') {
                stack.push('}');
            } else if (c=='[') {
                stack.push(']');
            } else if (stack.empty() || c!=stack.pop()) {
                return false;
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    /**
     * 31.下一个排列
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int fIndex = -1;
        for (int i=nums.length-2; i>=0; i--) {
            if (nums[i] < nums[i+1]) {
                fIndex = i;
                break;
            }
        }
        if (fIndex == -1) {
            reverse(nums, 0, nums.length-1);
            return;
        }
        int sIndex = -1;
        for (int i=nums.length-1; i>=0; i--) {
            if (nums[i] > nums[fIndex]) {
                sIndex = i;
                break;
            }
        }
        swap(nums, fIndex, sIndex);
        reverse(nums, fIndex+1, nums.length-1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i<j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
