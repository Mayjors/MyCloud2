package com.example.algorithm.offer;

import java.util.ArrayList;
import java.util.List;

public class Medium {
    public static void main(String[] args) {
//        singleNumbers(new int[]{4,3,4});
        findContinuousSequence(9);
    }

    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence(int target) {
        int i=1, j=2, s=3;
        List<int[]> res = new ArrayList<>();
        while (i<j) {
            if (s == target) {
                int[] ans = new int[j-i+1];
                for (int k =i; k<=j; k++) {
                    ans[k-i] = k;
                }
                res.add(ans);
            }
            if (s >= target) {
                s-=i;
                i++;
            }else {
                j++;
                s+=j;
            }
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     * @param nums
     * @return
     */
    public static int[] singleNumbers(int[] nums) {
        int x= 0;
        for (int num : nums) {
            x ^= num;
        }
        return new int[]{x};
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int n=numbers.length;
        int l =0, r=n-1;
        while (l<r && numbers[0] == numbers[r]) {
            r--;
        }
        while (l<r) {
            int mid= l+r+1 >>1;
            if (numbers[mid] >= numbers[0]){
                l=mid;
            } else {
                r = mid-1;
            }
        }
        return l+1 < n ? numbers[l+1]: numbers[0];
    }

    /**
     * 剑指 Offer 07. 重建二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = build(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
        return root;
    }

    public TreeNode build(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int val = preorder[preStart];
        TreeNode root = new TreeNode(val);
        int index = 0;
        for (int i=inStart; i<inEnd; i++) {
            if (inorder[i]==val) {
                index = i;
                break;
            }
        }
        int leftSize = index-inStart;
        root.left = build(preorder, inorder, preStart+1, preStart+leftSize, inStart, index-1);
        root.right = build(preorder, inorder, preStart+leftSize, preEnd, index+1, inEnd);
        return root;
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    static boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        // 判断传入参数的可行性i 与图行数row比较，j与列数col比较
        // i,j初始0，在图左上角
        // k是传入字符串当前索引，开始时0，如果当前字符串索引和图当前索引对应的值不相等，表示第一个数就不相等
        // 所以继续找第一个相等的数

        // 如果board[i][j] == word[k] 则表明当前找到了对应的数，就继续执行
        if (i >= board.length || i<0|| j >= board[0].length || j < 0 || board[i][j] != words[k])
            return false;
        // 表明找到了，每个字符都找到了
        // 一开始k=0，而word.length肯定不是0，所以没找到，就执行dfs继续
        if (k == words.length-1) {
            return true;
        }
        // 访问过的标记空字符串，“ ”是空格 '\0'是空字符串，不一样的！
        // 比如当前为A，没有标记找过，且A是word中对应元素，则此时应该找A下一个元素，假设是B，在dfs（B）的时候还是-
        // ->要搜索B左边的元素（假设A在B左边），所以就是ABA（凭空多出一个A，A用了2次，不可以），如果标记为空字符串->
        // 就不会有这样的问题，因为他们值不相等AB != ABA。
        board[i][j] = '\0';

        boolean res = dfs(board, words, i + 1, j, k + 1) || dfs(board, words, i - 1, j, k + 1) ||
                dfs(board, words, i, j + 1, k + 1) || dfs(board, words, i , j - 1, k + 1);

        // 还原找过的元素，因为之后可能还会访问到
        board[i][j] = words[k];
        return res;
    }
}
