package com.example.algorithm.simple;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SimpleSort {
    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
//        int[] arr = {4, 2, 8, 5, 9, 62};
        System.out.println(Arrays.toString(arr));
//        quickSort(arr, 0, arr.length-1);
//        System.out.println(Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));
//        selectionSort(arr);
//        System.out.println(Arrays.toString(arr));
//        selectionSortOP(arr);
//        System.out.println(Arrays.toString(arr));
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));
//        insertSortBS(arr);
//        System.out.println(Arrays.toString(arr));
//        shellSort(arr);
//        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * 一直比大小
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i=0; i< arr.length-1; i++) {
            for (int j=0; j< arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 每次从无序区间选出最大的一个元素，存放在无序区间的最后
     * 直到全部排序的数据元素排完
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            int min = i;
            for (int j=i+1; j<arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
//            int tmp=arr[i];
//            arr[i]=arr[min];
//            arr[min]=tmp;
            swap(arr, i, min);
        }
    }

    /**
     * 双向选择排序
     * 每次从无序区间选出最小、最大的元素，存放在无序区间的最前和最后，直到全部待排序元素排完
     * @param arr
     */
    public static void selectionSortOP(int[] arr) {
        int low = 0;
        int high = arr.length -1;
        // low = high, 无序区间只剩下一个元素，整个数组已经有序
        while (low <= high) {
            // 定义max, min为无序区间的起始位置索引
            int max = low;
            int min = low;
            for (int i=low +1; i<=high; i++) {
                if (arr[i] < arr[min]) {
                    min = i;
                }
                if (arr[i]> arr[max]) {
                    max = i;
                }
            }
            swap(arr, low, min);
            // 如果最大值对应的索引刚好是无序区间的起始位置，经过上面的调整，最大值被调整到了arr[min]位置
            if (max == low) {
                max = min;
            }
            swap(arr, max, high);
            high--;
            low++;
        }
    }

    /**直接插入排序
     * 每次选择无序区间中的第一个元素，在有序区间内选择合适的位置插入
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i=1; i<arr.length; i++) {
            // 待排序区间[i...n]
            // 待排序区间的第一个元素arr[i]
            // 从待排序区间的第一个元素向前看，找到合适的插入位置
            for (int j=i; j>0;j--) {
                // arr[j-1] 为已排序区间的最后一个元素
                // 相等我们也不交换，保证稳定性
                // 此时说明arr[i] >已排序区间的最大值，arr[i]已经有序了，--直接下次循环
                if (arr[j] >= arr[j-1]) {
                    break;
                } else {
                    swap(arr, j, j-1);
                }
            }
        }
    }

    /**
     * 折半插入排序
     * 在有序区间用二分法选择数据应该插入的位置时，因为区间的有序性，可以利用折半查找的思想
     * @param arr
     */
    public static void insertSortBS(int[] arr) {
        for (int i=1; i < arr.length; i++) {
            int right = i;
            int left = 0;
            int val = arr[i];
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (val < arr[mid]) {
                    right = mid;
                } else {
                    left = mid +1;
                }
            }
            for (int j =i; j> left; j--) {
                arr[j] = arr[j-1];
            }
            // left 就是val插入的位置
            arr[left] = val;
        }
    }

    /**
     * 希尔排序 --缩小增量法
     * 先选定一个整数gap(gap一般为数组长度的一半或1/3)，把待排序数组以gap为间隔分成各组，
     * 各组间内部使用插入排序，排序后，再将gap/2 或gap/3，重复上述流程，知道gap=1，此时数组已经近乎有序，利用插入排序对近乎有序的数组进行调整
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int gap = arr.length >>1;
        while (gap >1) {
            // 当gap>1时，对arr以gap为间隔进行分组，组内进行插入排序
            insertionSortBygap(arr, gap);
            gap = gap>> 1;
        }
        // 此时gap==1，数组接近有序，对此时的arr进行插入排序
        insertSort(arr);
    }

    public static void insertionSortBygap(int[] arr, int gap) {
        for (int i =gap; i< arr.length; i++) {
            for (int j=i; j-gap >= 0; j=j-gap) {
                if (arr[j] > arr[j-gap]) {
                    break;
                }
                if (arr[j] < arr[j-gap]) {
                    swap(arr, j, j-gap);
                }
            }
        }
    }

    /**
     * 快速排序（递归）
     * 通过一趟排序将待记录分割成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小。则可分别对这两部分记录继续进行排序，已达到整个序列有序
     * 1、从待排序区间选择一个数，作为基准值(pivot)
     * 2、Partition:遍历整个待排序区间，将比基准值小的(可以包含相等的)放到基准值的左边，将比基准值大的(可以包含相等的)放到基准值的右边
     * 3、采用分治思想，对左右两个小区间按照同样的方式处理，知道小区间的长度==1，代表已经有序，或者小区间的长度==0，代表没数据
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) return;

        i = low;
        j= high;
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }
    /**
     * 快速排序（递归）
     * 通过一趟排序将待记录分割成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小。则可分别对这两部分记录继续进行排序，已达到整个序列有序
     * 1、从待排序区间选择一个数，作为基准值(pivot)
     * 2、Partition:遍历整个待排序区间，将比基准值小的(可以包含相等的)放到基准值的左边，将比基准值大的(可以包含相等的)放到基准值的右边
     * 3、采用分治思想，对左右两个小区间按照同样的方式处理，知道小区间的长度==1，代表已经有序，或者小区间的长度==0，代表没数据
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort2(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        // 先获取分区点 -- 所谓的分区点就是经过分区函数后，某个元素落在最终的位置
        // 分区点左侧全都是小鱼该元素的区间，分区点右侧全都是>=该元素的区间
        int p = partition(arr, low, high);
        quickSort2(arr, low, p-1);
        quickSort2(arr, p+1, high);
    }

    /**
     * 在arr[l...h]上的分区函数，返回分区点的索引
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] arr, int low, int high) {
        // 随机在当前数组中选一个数
        int v = arr[low];
        int j = low;
        for (int i = low +1; i <= high; i++) {
            if (arr[i] < v) {
                swap(arr, j+1, i);
                j++;
            }
        }
        swap(arr, low, j);
        return j;
    }

    public static void quickSortNonRecursion(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int l = 0;
        int r = arr.length-1;
        stack.push(r);
        stack.push(l);
        while (!stack.isEmpty()) {
            // 栈不为空时，说明子区间还没有处理完
            int left = stack.pop();
            int right = stack.pop();
            if (left >= right) {
                continue;
            }
            int p = partition(arr, left, right);
            // 依次将右区间的开始和结束位置入栈
            stack.push(right);
            stack.push(p+1);
            // 再将左侧区间的开始和结束位置入栈
            stack.push(p-1);
            stack.push(left);
        }
    }

    public static void heapSort(int[] arr) {
        int temp;
        for (int i=arr.length / 2-1;  i >=0; i--) {
//            adjustHeap(arr, i, arr.length);
            adjustHeap2(arr, i, arr.length);
        }

        System.out.println("排序后=" + Arrays.toString(arr));
        /**
         * 将对顶元素与末尾元素交换，将最大元素"沉"到数组末尾
         * 重新调整结构，使其满足堆定义，然后继续交换对顶元素与当前末尾元素，反复执行调整+交换，直到整个序列有序
         */
        for (int j = arr.length-1; j>0; j--) {
            System.out.println("剩余数据量为" + (j + 1));
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            System.out.println("排序后=" + Arrays.toString(arr));
//            adjustHeap(arr, 0, j);
            adjustHeap2(arr, 0, j);
            System.out.println("调整后=" + Arrays.toString(arr));
        }
    }

    /**
     * 将一个数组调整成一个大根堆
     * @param arr
     * @param i
     * @param length
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素值，保存在临时变量
        int temp = arr[i];
        // 开始调整
        // 说明：k= i*2 +1k 是i结点的左子节点
        for (int k = i*2 + 1; k < length; k=k * 2+1) {
            if (k+1 < length && arr[k] < arr[k+1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    private static void adjustHeap2(int[] arr, int i, int length) {
        int temp = arr[i];
        //1. k = i * 2 + 1 k 是 i结点的左子结点
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            //说明左子结点的值小于右子结点的值。j指向右节点。本质就是找到左右节点中的较大值
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            //如果子结点大于父结点
            if (arr[j] > temp) {
                //把较大的值赋给当前结点
                arr[i] = arr[j];
                //!!! i 指向 k,继续循环比较
                i = j;
            } else {
                /*
                 * 下面break的理解至关重要。上面调用方法两次提到这里！注意看上面解释！
                 */
                break;
            }
        }
        //当for 循环结束后，我们已经将以i 为父结点的树的最大值，放在了 最顶(局部)。并且给temp找到了合适为位置(当前i的位置)
        arr[i] = temp;
    }
    
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
