package com.example.algorithm.offer;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class 树 {

    private static String ZIP_FILE="/Users/yuanjie/work/git/MyCloud2/algorithm/src/main/java/com/example/algorithm/offer/111/树.zip";
    private static String ZIP_FILE1="/Users/yuanjie/work/git/MyCloud2/algorithm/src/main/java/com/example/algorithm/offer/111/50W.md3";
    private static String ROOT_DIR="/Users/yuanjie/work/git/MyCloud2/algorithm/src/main/java/com/example/algorithm/offer/111/树";

    private static String JPG_FILE_PATH="/Users/yuanjie/work/git/MyCloud2/algorithm/src/main/java/com/example/algorithm/offer/111/";

    private static String JPG_FILE_NAME="Go八股文大全";

    public static void main(String[] args) {
//        zipFileNoBuffer1();
        zipUnpack();
    }

    public static void zipFileNoBuffer() {
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 1; i <11; i++) {
                try (InputStream input = new FileInputStream(JPG_FILE_PATH + JPG_FILE_NAME + ".pdf")) {
                    zipOut.putNextEntry(new ZipEntry(JPG_FILE_NAME + i + ".pdf"));
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = input.read(buffer)) != -1) {
                        zipOut.write(buffer, 0, len);
                    }
//                    int temp = 0;
//                    while ((temp = input.read()) != -1) {
//                        zipOut.write(temp);
//                    }
                }
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipUnpack() {
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE1);
        File rootDir = new File(ROOT_DIR);
        try {
            unpack(zipFile, rootDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - beginTime);
    }

    public final static void unpack(File zipFile, File rootDir) throws IOException {
        ZipFile zip = new ZipFile(zipFile);

        Enumeration entries = (Enumeration) zip.entries();
        while(entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            java.io.File f = new java.io.File(rootDir, entry.getName());
            if (entry.isDirectory()) { // if its a directory, create it
                continue;
            }
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            BufferedInputStream bis = new BufferedInputStream(zip.getInputStream(entry)); // get the input stream
            BufferedOutputStream bos = new BufferedOutputStream(new java.io.FileOutputStream(f));
            while (bis.available() > 0) { // write contents of 'is' to 'fos'
                bos.write(bis.read());
            }
            bos.close();
            bis.close();
        }
    }


    public static void zipFileNoBuffer1() {
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut);
            WritableByteChannel writableByteChannel = Channels.newChannel(zipOut);
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0; i <10; i++) {
                RandomAccessFile r = new RandomAccessFile(JPG_FILE_PATH + JPG_FILE_NAME + ".pdf", "r");
                zipOut.putNextEntry(new ZipEntry(JPG_FILE_NAME + i + ".pdf"));
                MappedByteBuffer mappedByteBuffer = r.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, r.length());
                System.out.println("size:"+r.length());
                writableByteChannel.write(mappedByteBuffer);

//                try (FileChannel fileChannel = new FileInputStream(JPG_FILE_PATH + JPG_FILE_NAME + ".pdf").getChannel()){
//                    zipOut.putNextEntry(new ZipEntry(JPG_FILE_NAME + i + ".pdf"));
//                    fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
//                    System.out.println("size:"+fileChannel.size());
//                }
//                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(JPG_FILE_PATH + JPG_FILE_NAME + ".pdf"))) {
//                    zipOut.putNextEntry(new ZipEntry(JPG_FILE_NAME + i + ".pdf"));
//                    byte[] buffer = new byte[1024];
//                    int len;
//                    while ((len = input.read(buffer)) != -1) {
//                        bufferedOutputStream.write(buffer, 0, len);
//                    }
//                }
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printInfo(long beginTime) {
        long endTime= System.currentTimeMillis();
        long total=endTime-beginTime;
        System.out.println("耗時："+total);
    }


    public static void zipFileNoBuffer11111() {
        long begin = System.currentTimeMillis();
        File zipFile = new File("/Users/yuanjie/work/git/MyCloud2/algorithm/src/main/java/com/example/algorithm/offer/111/树.zip");
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
//            zipFile.createNewFile();
            for (int i = 0; i < 10; i++) {
                try (InputStream inputStream = new FileInputStream("/Users/yuanjie/work/git/MyCloud2/algorithm/src/main/java/com/example/algorithm/offer/111/Go八股文大全.pdf")) {
                    zos.putNextEntry(new ZipEntry("Go八股文大全-" +i + ".pdf"));
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        zos.write(buffer, 0, len);
                    }

                }
            }
            System.out.println(System.currentTimeMillis() - begin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 94.二叉树的中序遍历
     * @param root·
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            res.add(root.val);
            root = root.right;
        }
//        inorder(root, res);
        return res;
    }

    /**
     * 前序遍历
     *
     * @param root
     * @param res
     */
    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    /**
     * 中序遍历
     *
     * @param root
     * @param res
     */
    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * 后序遍历
     *
     * @param root
     * @param res
     */
    private void postorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    /**
     * 226.翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    /**
     * 102.二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder22(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 二叉搜索树的后序遍历序列
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return true;
        }
//        int len = postorder.length;
//        return helper(postorder, 0, len - 1);

        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) {
                return false;
            }
            while (!stack.isEmpty() && postorder[i] < stack.peek()) {
                root = stack.pop();
            }
            stack.push(postorder[i]);
        }
        return true;
    }

    private boolean helper(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int p = left;
        // 左边节点的数据都比根节点的数小，找到p = 左边节点的位置
        while (postorder[p] < postorder[right]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[right]) {
            p++;
        }
        return p == right && helper(postorder, left, m - 1) && helper(postorder, m, right - 1);
    }

    /**
     * 236. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            // 只要当前节点是p和q中的任意一个，就返回(因为不能比这个更深了，再深p和q中的一个就没了)
            return root;
        }
        // 根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // p和q都没找到，那就没有
        if (left != null && right != null) {
            return root;
        }
        if (left == null) return right;
        if (right == null) return left;
        // 左右子树都找到p和q了 那就说明p和q分别两个子树上，所以此时的最近公共祖先就是root
        return root;
    }

    /**
     * 105.从前序与中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * 前序遍历数组为 preorder[preStart...preEnd]
     * 中序遍历数组为 inorder[inStart...inEnd]
     * 构造这个二叉树并返回该二叉树的根节点
     *
     * @param preorder
     * @param preStart
     * @param preEnd
     * @param inorder
     * @param inStart
     * @param inEnd
     * @return
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int val = preorder[preStart];
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == val) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(val);
        int leftSize = index - inStart;
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);

        return root;
    }

    /**
     * 617.合并二叉树
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    /**
     * 112.路径总和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) return false;
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueVal = new LinkedList<>();
        queueNode.offer(root);
        queueVal.offer(root.val);
        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int val = queueVal.poll();
            if (node.left == null && node.right == null && val == targetSum) {
                return true;
            }
            if (node.left != null) {
                queueNode.offer(node.left);
                queueVal.offer(val + node.left.val);
            }
            if (node.right != null) {
                queueNode.offer(node.right);
                queueVal.offer(val + node.right.val);
            }
        }
        return false;
    }

    /**
     * 100.相同的树
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 101.对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        if (left.val != right.val) return false;
        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    /**
     * 124.二叉树中的最大路径和
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        return 0;
    }

    /**
     * 662. 二叉树最大宽度
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                res++;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

    /**
     * 54. 二叉搜索树的第k大节点(有问题)
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == k - 1) {
                    return node.val;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return 0;
    }

    /**
     * 把二叉树打印成多行
     * @param root
     * @return
     */
    public List<List<Integer>> printRows(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
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
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res++;
        }
        return res;
    }


    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        dfs(root, map, 0);
        return new ArrayList<>(map.values());
    }

    private static void dfs(TreeNode node, Map<Integer, List<Integer>> map, int level) {
        if (node == null) {
            return;
        }
        List<Integer> list = map.getOrDefault(level, new ArrayList<>());
        list.add(node.val);
        map.put(level, list);
        dfs(node.left, map, level + 1);
        dfs(node.right, map, level + 1);
    }


}
