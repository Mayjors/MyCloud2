package com.example.algorithm.zijie;

import java.util.*;

public class ListTest {
    public static void main(String[] args) {
        int aa = findKthLargest(new int[]{3,2,1,5,6,4}, 2);
        System.out.println(aa);
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> lists = permute(nums);
        System.out.println(lists);
        String s = getPermutation(4, 10);
        System.out.println(s);
        int a = findLengthOfLCIS(new int[]{1, 3, 5, 4, 7});
        System.out.println(a);
    }

    /**
     * 两数相加
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode cur = prev;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 如果l1!= null,取他的值，等于null 赋值0，保持两个链表具有相同位数
            int x = l1 != null ? l1.val :0;
            int y = l2 != null ? l2.val :0;
            // 将两个链表的值，相加，并加上进位数
            int sum = x+y+carry;
            carry = sum / 10;
            // 计算两数和，此时排除超过10的情况
            sum = sum %10;
            // 将求和数赋值给新链表的节点
            // 注意这时候不能直接将sum赋值给cur.next
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 如果最后两位数，相加时有进位数，将进位数赋予链表的新节点
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return prev.next;
    }

    /**
     * 141. 环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast) return true;
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast!=null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * 160. 相交链表
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a != null ? a.next : headB;
            b = b != null ? b.next : headA;
        }
        return a;
    }

    /**
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    public ListNode swapPairs2(ListNode head) {
        //递归的终止条件
        if(head==null || head.next==null) {
            return head;
        }
        //假设链表是 1->2->3->4
        //这句就先保存节点2
        ListNode tmp = head.next;
        //继续递归，处理节点3->4
        //当递归结束返回后，就变成了4->3
        //于是head节点就指向了4，变成1->4->3
        head.next = swapPairs(tmp.next);
        //将2节点指向1
        tmp.next = head;
        return tmp;
    }

    /**
     * 链表排序
     * 剑指 Offer II 077. 链表排序
     * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return null;
    }

    /**
     * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode start = pre, end = pre;
        for (int i=0; i<n; i++) {
            start = start.next;
        }
        while (start.next != null) {
            start = start.next;
            end = end.next;
        }
        end.next = end.next.next;
        return pre.next;
    }

    /**
     * 25. K 个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, end = dummy;
        while (end.next != null) {
            for (int i =0; i<k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    /**
     * 三数之和
     * 15. 三数之和
     * 判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 响应国家号召，生三胎，领百万大奖
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length <3) {
            return list;
        }
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++) {
            // 如果老大都大于0, 后面的都大于0
            if (nums[i] >0) break;
            int first = nums[i];    // 老大出列，站好别动
            // 老大想再往后占个位，多领一次奖，这不行
            if (i>0 && nums[i] == nums[i-1]) continue;

            // 画个圈 让各家老二在里面呆着
            Set<Integer> set = new HashSet<>();
            for (int j = i+1; j< nums.length; j++) {
                // 老三出列，一会你和老大一块到圈里找老二
                int third = nums[j];
                int second = - (first + third);
                // 找到老二，记到中奖名单上
                if (set.contains(second)) {
                    list.add(new ArrayList<>(Arrays.asList(first, second, third)));
                    // 老三想多领奖， 不行
                    while (j<nums.length-1 && nums[j] == nums[j+1]) j++;
                }
                set.add(third);
            }
        }
        return list;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        // 枚举a
        for (int first = 0; first<n; first++) {
            // 需要和上次枚举的数不相同
            if (first>0 && nums[first] == nums[first-1]) {
                continue;
            }
            // c 对应的指针初始指向数组最后端
            int third = n-1;
            int target = -nums[first];
            // 枚举b
            for (int second = first+1; second<n; ++second) {
                // 需要和上次的枚举数不相同
                if (second > first+1 && nums[second] == nums[second-1]) {
                    continue;
                }
                // 需要保证b的指针在c的指针左侧
                while (second<third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着b后续的增加
                // 就不会满足 a+b+c=0 且 b<c的c了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[first]);
                    l.add(nums[second]);
                    l.add(nums[third]);
                    list.add(l);
                }
            }
        }
        return list;
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <3) return ans;
        int len = nums.length;
        Arrays.sort(nums);
        for (int i=0; i<len; i++) {
            if (nums[i] >0) {
                // 最小的数大于0 不满足
                break;
            }
            if (i>0 && nums[i] == nums[i-1]) {
                // 重复数据
                continue;
            }
            int l = i+1;
            int r = len-1;
            while (l < r) {
                int sum = nums[i]+ nums[l] + nums[r];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i],nums[l], nums[r]));
                    while(l<r && nums[l] == nums[l+1]) l++;
                    while(l<r && nums[r] == nums[r-1]) r++;
                    l++;
                    r--;
                } else if (sum<0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <3) return ans;
        int len = nums.length;
        Arrays.sort(nums);
        for (int i=0; i<len; i++) {
            if (nums[i] >0) break;
            if (i>0 && nums[i] == nums[i-1]) continue;
            int l = i+1, r = len-1;
            while (l<r) {
                int sum = i+l+r;
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l<r && nums[l] == nums[l+1]) l++;
                    while (l<r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                } else if (sum<0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer 25. 合并两个排序的链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0), cur = pre;
        while(l1 != null && l2 != null) {
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
     * 搜索旋转排序数组
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length -1;
        // 先找出旋转排序数组的旋转点 low
        while (low < high) {
            int mid = low + (high-low) /2;
            if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid+1;
            }
        }
        // 判断是不是比旋转点之前的数组大，大则在前面数组中
        if (nums[0] < target) {
            for (int i=0; i<low; i++) {
                if (nums[i] == target) {
                    return i;
                }
            }
        } else {
            for (int i= low; i<nums.length; i++) {
                if (nums[i] == target) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * 最长连续递增序列
     * nums = [1,3,5,4,7]
     * 输出：3
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length <=1) {
            return nums.length;
        }
        int ans = 1;
        int count =1;
        for (int i=0; i<nums.length-1; i++) {
            if (nums[i+1] > nums[i]) {
                count++;
            } else {
                count =1;
            }
            ans = Math.max(count, ans);
        }
        return ans;
    }

    /**
     * 最长连续序列
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        return 0;
    }

    /**
     * 46.排列序列/全排序
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len ==0) {
            return res;
        }
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private static void dfs(int[] nums, int len, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择出一个元素作为下一个位置的元素，这显然得通过一个循环实现
        for(int i=0; i<len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                System.out.println("  递归之前 => " + path);
                dfs(nums, len, depth+1, path, used, res);
                // 注意：下面这两行代码发生[回溯]，回溯发生在从深层节点回到浅层节点的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.remove(path.size()-1);
                System.out.println("递归之后 => " + path);
            }
        }
    }

    /**
     * 排列序列
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
        // 注意：相当于在n个数字的全排列中找到下标为k-1的那个数，因此k先减1
        k--;
        int[] factorial = new int[n];
        factorial[0] = 1;
        // 先算出所有的阶乘值
        for (int i=1; i<n; i++) {
            factorial[i] = factorial[i-1]*i;
        }
        StringBuilder stringBuilder = new StringBuilder();
//        List<Integer> nums = new LinkedList<>();
//        for (int i=1; i<=n; i++) {
//            nums.add(i);
//        }
//        // i 表示剩余的数字个数，初始化为n-1
//        for (int i=n-1; i>=0; i--) {
//            int index = k / factorial[i];
//            stringBuilder.append(nums.remove(index));
//            k -= index * factorial[i];
//        }
        int[] valid = new int[n+1]; // 用来记录是否被访问过
        Arrays.fill(valid, 1);
        for (int i=1; i<=n; i++) {
            // 找到第一个位置的数字
            int x = k / factorial[n-i] +1;
            for (int j=1; j<=n; j++) {
                x -= valid[j];
                if (x==0) {
                    valid[j] = 0;
                    stringBuilder.append(j);    // 找到了对应的数
                    break;
                }
            }
            k %= factorial[n-i];    //找下一个位置，需要除以当前阶乘(n-1)!
        }
        return stringBuilder.toString();
    }

    /**
     * 剑指 Offer II 076. 数组中的第 k 大的数字
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        for (int i= heapSize/2; i>=0; i--) {
            // 建立堆
            maxHeap(nums, i, heapSize);
        }
        for (int i= nums.length-1; i>= nums.length -k +1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeap(nums, 0, heapSize);
        }
        return nums[0];
    }
    public static void maxHeap(int[] a, int i, int heapSize) {
        int temp = a[i];
        for (int k = i*2 +1; k < heapSize; k= k*2+1) {
            if (k+1 < heapSize && a[k] < a[k+1]) {
                k++;
            }
            if (a[k] > temp) {
                a[i] = a[k];
                i=k;
            } else {
                break;
            }
        }
        a[i] = temp;
    }

    /**
     * 234. 回文链表
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        // 通过快慢指针找到中心点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果fast不为空，说明链表的长度为奇数个
        if (fast != null) {
            slow = slow.next;
        }
        // 反转后半部分链表
        slow = reverse(slow);

        fast = head;
        while (slow != null) {
            // 然后比较，判断节点值是否相等
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = head.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
