package com.example.algorithm.tujie.divide;

import com.example.algorithm.tujie.TreeNode;

/**
 * 分治
 */
public class Solution {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return null;
    }

    /**
     * 剑指 Offer 16. 数值的整数次方
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (x == 0.0f) return 0.0d;
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 /x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) res *=x;
            x *=x;
            b>>=1;
        }
        return res;
    }

    /**
     * 剑指 Offer 17. 打印从 1 到最大的 n 位数
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        return null;
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return true;
    }

    /**
     * 剑指 Offer 04. 二维数组中的查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
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
     * 剑指 Offer 11. 旋转数组的最小数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int i=0, j=numbers.length-1;
        while (i<j) {
            int mid = (i+j)/2;
            if (numbers[mid] > numbers[j]) {
                i = mid+1;
            } else if (numbers[mid] < numbers[j]) {
                j = mid;
            } else {
                j--;
            }
        }
        return numbers[i];
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1 中缺失的数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int i=0, j= nums.length-1;
        while (i<=j) {
            int m = (i+j)/2;
            if (nums[m] == m) {
                i = m+1;
            } else {
                j = m-1;
            }
        }
        return i;
    }

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int i=0, j=nums.length-1;
        while (i<j) {
            while (i<j && (nums[i] &1) == 1) {
                i++;
            }
            while (i<j && (nums[j] &1) == 0) {
                j--;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }
}
