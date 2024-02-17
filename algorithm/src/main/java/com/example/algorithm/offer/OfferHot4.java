package com.example.algorithm.offer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class OfferHot4 {
    public static void main(String[] args) {

    }

    /**
     * 复制带随机指针的链表
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        LinkedList<Node> list = new LinkedList<>();
        list.add(head);
        while (!list.isEmpty()) {
            Node node = list.removeFirst();
            if (node.next!= null) {
                list.add(node.next);
            }
            node.next = new Node(node.val);
        }
        return list.getFirst();
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
//            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }


    /**
     * 链表中倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 删除链表的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur== null) return head;
        pre.next = cur.next;
        return head;
    }

    /**
     * 1. 从尾到头打印链表
     * @param listNode
     * @return
     */
    public int[] reversePrint(ListNode listNode) {
        LinkedList<Integer> stack = new LinkedList<>();
        int count =0;
        while (listNode!= null) {
            stack.push(listNode.val);
            listNode = listNode.next;
            count++;
        }
        int[] res = new int[count];
        for (int i = 0; i < count; i++) {
            res[i] = stack.pop();
        }
        return res;
    }


    /**
     * 1 查找特殊二维数组中的数
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (matrix[i][j] == target) return true;
//            }
//        }
        // 从右上角开始找
        int i=0, j=0;
        for (i=0, j=matrix[0].length-1; i<matrix.length && j>=0;) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) j--;
            else i++;
        }
        return false;
    }

    /**
     * 6 查找旋转数组中的最小数字
     * @param nums
     * @return
     */
    public static int findminMumber(int[] nums) {
        int len = nums.length;
        int low = 0, hight = len-1;

        if (len == 0) return -1;
        if (len == 1) return nums[0];

        // 第一种情况，数组没有发生旋转，即数组本身
        if (nums[low] < nums[hight]) return nums[low];
        while (low < hight) {
            int mid = low + (hight - low) / 2;
            // 当首尾和中间元素相等时，因为没法比较大小，因此用不了折半查找，只能哟个顺序查找
            if (nums[low] == nums[mid] || nums[mid] == nums[hight]) {
                return orderFind(nums);
            }
            if (nums[low] < nums[mid]) {
                low = mid;
            } else if (nums[mid] < nums[hight]) {
                hight = mid;
            }
            if (hight-low == 1) {
                return nums[hight];
            }
        }
        return -1;
    }

    /**
     * 3 调整数组顺序，使得奇数在偶数前面
     * @param array
     */
    public static void reOrderArray(int[] array) {
        if (array == null || array.length == 0) return;
        int left = 0, right = array.length-1;
        while (left < right) {
            while (left < right && array[left] >= array[right]) {
                right--;
            }
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
            while (left < right && array[left] <= array[right]) {
                left++;
            }
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
    }

    private static int orderFind(int[] nums) {
        int min = nums[0];
        for (int i=0; i<nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }

    /**
     * LCR 143. 子结构判断
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    /**
     * LCR 144. 二叉树的镜像
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
//        TreeNode left = mirrorTree(root.left);
//        TreeNode right = mirrorTree(root.right);
//        root.left = right;
//        root.right = left;
        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }

    /**
     * LCR 145. 二叉树的层序遍历
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int[] res = new int[queue.size()];
        int index = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res[index++] = node.val;
            if (node.left!= null) queue.offer(node.left);
            if (node.right!= null) queue.offer(node.right);
        }
        return res;
    }


}
