package com.example.algorithm.sort;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) throws ClassNotFoundException {
        int[] arr = {4, 5, 6, 3, 2, 1};
//        bubbleSort(arr);
//        bubbleSort2(arr);
//        selectSort(arr);
//        insertSort(arr);
//        insertSort2(arr);
//        quickSort2(arr, 0, arr.length-1);
        heapSort(new int[] {5, 4, 6, 2, 3, 1});
        System.out.println(Arrays.toString(arr));
        ClassLoader loader = SortTest.class.getClassLoader();
        Class<?> s = loader.loadClass("com.example.algorithm.sort.SortTest");
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }

    }

    /**
     * 冒泡排序
     * 对每一对相邻元素作比较，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    /**
     * 冒泡排序改进
     * 之前都只计算最大的值，现在我们在每次循环里进行正反两次冒泡，分别找到最大值和最小值
     * @param arr
     */
    public static void bubbleSort2(int[] arr) {
        int left =0, right = arr.length-1;
        while (left < right) {
            for (int i=left; i<right; ++i) {
                if (arr[i] > arr[i+1]) {
                    swap(arr, i, i+1);
                }
            }
            right--;
            for (int j=right; j>left; --j) {
                if (arr[j]< arr[j-1]) {
                    swap(arr, j, j-1);
                }
            }
            left++;
        }
    }

    /**
     * 选择排序
     * 选出最大(最小)元素，放到起始位置
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i=0; i<arr.length;i++) {
            int minIndex = i;
            for (int j=i+1; j< arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(arr, minIndex, i);
            }
        }
    }

    /**
     * 插入排序
     * 将未排序的数据插入到已排序的数据中
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i=1; i<arr.length;i++) {
            if (arr[i] < arr[i-1]) {
                int temp = arr[i];
                int j;
                for (j=i-1; j>=0&&temp < arr[j];j--) {
                    arr[j+1] = arr[j];
                }
                arr[j+1] = temp;
            }
        }
    }

    /**
     * 插入算法优化(二分插入)
     * @param arr
     */
    public static void insertSort2(int[] arr) {
        for (int i=1; i<arr.length; i++) {
            // 如果新纪录小于有序序列的最大元素，则用二分法找出新纪录所在位置
            if (arr[i] < arr[i-1]) {
                int temp = arr[i];
                int left =0, right=i-1;
                while (left <= right) {
                    int mid = (left + right)/2;
                    if (arr[mid] <temp) {
                        left = mid +1;
                    } else {
                        right = mid-1;
                    }
                }
                for (int j=i; j>left; j--) {
                    arr[j] = arr[j-1];
                }
                arr[left] = temp;
            }
        }
    }

    /**
     * 归并排序
     * 先递归分解，再合并数组
     * @param arr
     */
    public static void mergeSort(int[] arr, int low, int high) {
        int mid = (high+low)/2;
        if (low < high) {
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i = low;
        int j = mid +1;
        int index = 0;
        while (i<=mid && j<=high) {
            if (arr[i] <= arr[j]) {
                temp[index] = arr[i];
                i++;
            } else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }
        while (i <= mid) {
            temp[index] = arr[i];
            i++;
            index++;
        }
        //后面一个数组有多余数据
        while (j <= high) {
            temp[index] = arr[j];
            j++;
            index++;
        }
        //把临时数组中的数据重新存入原数组
        for (int k = 0; k < temp.length; k++) {
            arr[k + low] = temp[k];
        }
    }

    /**
     * 快排
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int temp = arr[start];
            int low = start, high = end;
            // 循环找比标准数大和标准数小的数
            while (low < high) {
                // 如果右边数字比标准数大，下标向前移
                while (low<high && arr[high] >= temp) {
                    high--;
                }
                arr[low] = arr[high];
                while (low<high && arr[low] <= temp) {
                    low++;
                }
                arr[high] = arr[low];
            }
            arr[low] = temp;
            quickSort(arr, start, low);
            quickSort(arr, low+1, end);
        }
    }

    public static void quickSort2(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        int pIndex = partition(arr, start, end);
        quickSort2(arr, start, pIndex-1);
        quickSort2(arr, pIndex+1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int left = start;
        // all in [start+1,left] < pivot
        // all in [left+1, i) >= pivot
        for (int i = start+1; i< end; i++) {
            if (arr[i] < pivot) {
                left++;
                swap(arr, i, left);
            }
        }
        swap(arr, start, left);
        return left;
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        for (int i=arr.length/2 -1; i>=0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int j=arr.length-1; j>0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将一个数组调整成一个大根堆
     * 举例int arr[]={4, 6,8,5,9};=>i=1=> adjustHeap=>得到{4,9,8,5, 6}
     * 如果我们再次调用adjustHeap 传入的是i=0=>得到{4,9, 8,5,6}=> {9,6,8,5, 4}
     * @param arr
     * @param i
     * @param len
     */
    private static void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        // 开始调整
        // 说明k=i*2 +1k是i节点的左子节点
        for (int k=i*2+1; k<len; k=k*2+1) {
            if (k+1 < len && arr[k] < arr[k+1]) {
                k++;
            }
            if (arr[k] > arr[i]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    public static void heapSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0 ; i< arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    // 某个数现在处在index位置，往上继续移动到头
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
