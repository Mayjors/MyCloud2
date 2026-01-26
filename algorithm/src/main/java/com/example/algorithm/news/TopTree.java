package com.example.algorithm.news;

import java.util.ArrayDeque;
import java.util.Queue;

import com.example.algorithm.offer.TreeNode;

public class TopTree {
    public static void main(String[] args) {
        TopTree topTree = new TopTree();
        TreeNode root = topTree.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println("xxxx");
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 100. 相同的树
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }

    /**
     * 226. 翻转二叉树
     *
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
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recur(root.left, root.right);
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * @param preOrder
     * @param inOrder
     * @return
     */
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        return buildTreeHelper(preOrder, 0, preOrder.length, inOrder, 0, inOrder.length);
    }

    
    private TreeNode buildTreeHelper(int[] preOrder, int i, int length, int[] inOrder, int j, int length2) {
        if (i == length) return null;
        int val = preOrder[i];
        TreeNode root = new TreeNode(val);
        int index = 0;
        for (int k=j; k<length2; k++) {
            if (inOrder[k]==val) {
                index = k;
                break;
            }
        }
        int leftSize = index-j;
        root.left = buildTreeHelper(preOrder, i+1, i+leftSize+1, inOrder, j, index);
        root.right = buildTreeHelper(preOrder, i+leftSize+1, length, inOrder, index+1, length2);
        return root;
    }

    /**
     * 124. 二叉树中的最大路径和
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);
        return Math.max(Math.max(left, right) + root.val, root.val);
    }

    /**
     * 114. 二叉树展开为链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode next = cur.left;
                TreeNode pre = next;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }

    /**
     * 112. 路径总和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == targetSum) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 129. 求根节点到叶节点数字之和
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        // return sumNumbersHelper(root, 0);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) {
                sum += node.val;
            }
            if (node.left != null) {
                node.left.val += node.val * 10;
                queue.add(node.left);
            }
            if (node.right != null) {
                node.right.val += node.val * 10;
                queue.add(node.right);
            }
        }
        return sum;
    }

    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    private int sumNumbersHelper(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) return sum;
        return sumNumbersHelper(root.left, sum) + sumNumbersHelper(root.right, sum);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return recur(left.left, right.right) && recur(left.right, right.left);
    }

}
