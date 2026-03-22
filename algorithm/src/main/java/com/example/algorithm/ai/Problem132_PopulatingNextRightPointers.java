package com.example.algorithm.ai;

/**
 * 132. 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 
 * 示例:
 * 输入: root = [1,2,3,4,5,6,7]
 * 输出: [1,#,2,3,#,4,5,6,7,#]
 */
public class Problem132_PopulatingNextRightPointers {
    
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        
        public Node() {}
        public Node(int _val) { val = _val; }
    }
    
    // 方法 1: BFS
    public Node connect(Node root) {
        if (root == null) return null;
        
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return root;
    }
    
    // 方法 2: 利用已建立的 next 指针 (O(1) 空间)
    public Node connectOptimized(Node root) {
        if (root == null) return null;
        
        Node leftmost = root;
        
        while (leftmost.left != null) {
            Node head = leftmost;
            
            while (head != null) {
                // 连接同一父节点的左右子节点
                head.left.next = head.right;
                
                // 连接不同父节点的子节点
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                
                head = head.next;
            }
            
            leftmost = leftmost.left;
        }
        
        return root;
    }
}
