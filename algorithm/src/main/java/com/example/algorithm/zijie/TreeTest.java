package com.example.algorithm.zijie;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class TreeTest {

    /**
     * 二叉树路径问题
     * 543. 二叉树的直径
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        return 0;
    }

    /**
     * 257. 二叉树的所有路径
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode root, String path, List<String> res) {
        // 判断返回值
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
            return;
        }
        // 如果不是叶子节点，再继续遍历
        dfs(root.left, path+root.val +"->", res);
        dfs(root.right, path+root.val +"->", res);
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i=0; i<n; i++) {
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
     * 105. 从前序与中序遍历序列构造二叉树
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end, HashMap<Integer, Integer> map) {
        if (p_start == p_end) {
            return null;
        }
        int val = preorder[p_start];
        TreeNode root = new TreeNode(val);
        // 从中序遍历中找到根节点位置
//        int index = 0;
//        for (int i=i_start; i<i_end; i++) {
//            if (val == inorder[i]) {
//                index = i;
//                break;
//            }
//        }
        int index = map.get(val);
        int leftNum = index-i_start;
        // 构造左右子树
        root.left = buildTreeHelper(preorder, p_start+1, p_start+leftNum+1, inorder, i_start, index, map);
        root.right = buildTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, index + 1, i_end, map);
        return root;
    }

    /**
     * 二叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

}
