package com.example.algorithm.offer;

import com.example.algorithm.simple.TreeNode;

import java.util.*;

public class Simple {
    public static void main(String[] args) {
        TreeNode root = buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println(root);
        int s = cuttingRope(3);
        System.out.println(s);
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        Set<Integer> dic = new HashSet<>();
        for (int num : nums) {
            if (dic.contains(num)) {
                return num;
            }
            dic.add(num);
        }
        return -1;
    }

    /**
     * 剑指 Offer 04. 二维数组中的查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length-1, j=0;
        while (i>=0 && j<matrix[0].length) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] res = new int[list.size()];
        for (int i=0; i<res.length; i++) {
            res[i] = list.get(list.size()- i-1);
        }
        return res;
    }

    /**
     * 剑指 Offer 07. 重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 根据前序找到根节点
        TreeNode root = build(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
        return root;
    }
    public static TreeNode build(int[] preorder, int[]inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if(preStart>preEnd || inStart>inEnd) {
            return null;
        }
        int val = preorder[preStart];
        TreeNode root = new TreeNode(val);
        int index =0;
        for(int i=inStart; i<= inEnd; i++) {
            if(inorder[i] == val) {
                index = i;
                break;
            }
        }
        int leftSize = index-inStart;
        root.left = build(preorder, inorder, preStart+1, preStart+leftSize, inStart, index-1);
        root.right = build(preorder, inorder, preStart+leftSize+1, preEnd, index+1, inEnd);
        return root;
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * @param n
     * @return
     */
    public static int numWays(int n) {
        int a=1, b=1, sum;
        for (int i=0; i<n; i++) {
            sum = (a+b) %1000000007;
            a=b;
            b=sum;
        }
        return a;
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * 给你一个可能存在重复元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
     *
     * @param numbers
     * @return
     */
    public static int minArray(int[] numbers) {
        int n = numbers.length;
        int l=0, r=n-1;
        while (l<r && numbers[0] == numbers[r]) {
            r--;
        }
        while (l<r) {
            int mid = l+r+1 >>1;
            if (numbers[mid] >= numbers[0]) {
                l = mid;
            } else {
                r= mid-1;
            }
        }
        return l+1 < n? numbers[l+1] : numbers[0];
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int n = nums.length;
        int l =0, r= n-1;
        while (l<r) {
            int mid = l+r+1 >>1;
            if (nums[mid] >=nums[l]) {
                l = mid;
            } else {
                r = mid-1;
            }
        }
        return l+1 < n ? nums[l+1]: nums[0];
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false
     * 深度优先搜索(DFS) + 剪枝
     * 剪枝：在搜索中，遇到 这条路不可能和目标字符串匹配成功 的情况（例如：此矩阵元素和目标字符不同、此元素已被访问），则应立即返回，称之为 可行性剪枝
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        // 遍历图
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                // 如果找到了，就返回true
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    static boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        // 判断传入参数的可行性i 与图行数row比较，j与列数col比较
        // i,j初始0，在图左上角
        // k是传入字符串当前索引，开始时0，如果当前字符串索引和图当前索引对应的值不相等，表示第一个数就不相等
        // 所以继续找第一个相等的数

        // 如果board[i][j] == word[k] 则表明当前找到了对应的数，就继续执行
        if (i >= board.length || i<0|| j >= board[0].length || j < 0 || board[i][j] != word[k])
            return false;
        // 表明找到了，每个字符都找到了
        // 一开始k=0，而word.length肯定不是0，所以没找到，就执行dfs继续
        if (k == word.length-1) {
            return true;
        }
        // 访问过的标记空字符串，“ ”是空格 '\0'是空字符串，不一样的！
        // 比如当前为A，没有标记找过，且A是word中对应元素，则此时应该找A下一个元素，假设是B，在dfs（B）的时候还是-
        // ->要搜索B左边的元素（假设A在B左边），所以就是ABA（凭空多出一个A，A用了2次，不可以），如果标记为空字符串->
        // 就不会有这样的问题，因为他们值不相等AB != ABA。
        board[i][j] = '\0';

        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);

        // 还原找过的元素，因为之后可能还会访问到
        board[i][j] = word[k];
        return res;
    }

    /**
     * 剑指 Offer 14- I. 剪绳子
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1]
     * 求每段的乘积
     * @param n
     * @return
     */
    public static int cuttingRope(int n) {
        // 当i>=2时，假设对长度为i绳子剪出第一段长度为j(1<=j<i)，则有一下两种方案
        // 1、将i剪成j和i-j长度的绳子，且i-j不再继续剪，此时乘积为 jx(i-j)
        // 2、将i剪成j和i-j长度的绳子，且i-j继续剪，此时乘积为 jxdp[i-j]
        // 因此，当j固定时，有dp[i] = max(jx(i-j), jxdp[i-j])
        // 由于j的取值范围是1到i，需要遍历所有j得到dp[i]
        // 初始化状态：0不是正整数，1是最小正整数，0和1都不能拆分，因此dp[0]=dp[1]=0
        // 遍历顺序 由状态转义方程知道dp[i]是从jx(i-j), jxdp[i-j]且j的取值返回时1到i-1,需要遍历所有的j

        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i=3; i<=n; i++) {
            for (int j=1; j<i-1; j++) {
                dp[i] = Math.max(dp[i], Math.max((i-j)*j, dp[i-j]*j));
            }
        }
        return dp[n];
    }

    public static int cuttingRope2(int n) {
        if(n<=3) return n-1;
        int a= n/3, b = n%3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) (Math.pow(3, a-1) *4);
        }
        return (int) (Math.pow(3, a) *2);
    }

    /**
     * 面试题13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     * @param m
     * @param n
     * @param k
     * @return
     */

    public static int movingCount(int m, int n, int k) {
        boolean[][] visited; // 记录位置是否被遍历过
        return 0;
    }

    /**
     * 剑指 Offer 15. 二进制中1的个数
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n&1;
            n>>>=1;
        }
//        while (n!=0) {
//            res ++;
//            n&=n-1; // 将最末尾的1取出
//        }
        return res;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) {
            pre.next = cur.next;
        }
        return head;
    }

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int i=0, j=nums.length-1, tmp;
        while (i<j) {
            while (i<j && (nums[i]&1)==1) {
                i++;
            }
            while (i<j && (nums[j]&1) == 0) {
                j--;
            }
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

    /**
     * 剑指 Offer 25. 合并两个排序的链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;

        return pre.next;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length ==0) {
            return new int[0];
        }
        int index = 0;
        int l = 0, r = matrix[0].length-1, t = 0, b = matrix.length-1;
        int[] res = new int[(r+1) * (b+1)];
        while (true) {
            for (int i=1; i<=r; i++) {
                res[index++] = matrix[t][i];
            }
            if (++t >b) {
                break;
            }
        }
        return null;
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * @param root
     * @return
     */
    public static int[] levelOrder(TreeNode root) {
        if(root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i=0; i<ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径
     * DFS
     *
     * @param root
     * @param target
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        return null;
    }


    public static void dfs2(TreeNode root, int tar) {
        if (root == null) return;

    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode cur = head;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        // 构建新链表的next和random指向
        while (cur != null) {
            // map.get(cur) 得到新节点的值 .next .random
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) return null;
        RandomListNode cur = head;
        // 复制各节点，构建一个新的链表
        while (cur != null) {
            RandomListNode tmp = new RandomListNode(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        cur = head;
        // 构建各新节点的random指向
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // 拆分两个链表
        cur = head;
        RandomListNode pre = head, res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null;
        return res;
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        int n = s.length();
        int i=n-1, j=n-1;
        StringBuilder res = new StringBuilder();
        while (i>=0) {
            while (i>=0 && s.charAt(i) != ' ') {
                i--;
            }
            res.append(s.substring(i+1, j+1) + " ");
            while (i>=0 && s.charAt(i) == ' ') {
                i--;
            }
            j=i;
        }
        return res.toString().trim();
    }
}

