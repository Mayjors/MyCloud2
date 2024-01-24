package com.example.algorithm.zijie;

public class Test {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5))))))));
        deleteDuplicates(l1);
        removeElement(new int[]{3,2,2,3}, 3);
        plusOne(new int[]{8, 9});
        mySqrt(8);
        isHappy(20);
    }

    public static boolean isHappy(int n) {
        int slow = n, fast = getNext(n);
        while (slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return slow == 1;
    }

    private static int getNext(int n) {
        int sum = 0;
        while(n>0) {
            int digit = n % 10;
            sum += digit * digit;
            n = n/10;
        }
        return sum;
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i>=0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0 ) {
                return digits;
            }
        }
        digits = new int[digits.length +1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 最后一个单词的长度
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int index = s.length() -1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int wordLenth = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLenth++;
            index--;
        }
        return wordLenth;
    }

    /**
     * 移除元素
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int x : nums) {
            if (x != val) {
                nums[idx++] = x;
            }
        }
        return idx;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        //创建双指针
        ListNode dummy = new ListNode();
        //dummy为虚拟头结点，用于返回最终结果
        ListNode tail = dummy;
        //tail代表当前有效链表的结尾，tail用于具体的操作中
        while(head != null){
            //只要head不为空 /** 外层while循环的思路： * 先判断是否需要加入此节点（与下一个不重复则加入） * 如果符合加入条件则进入if子句，在结果链表中加入此节点 * 当不符合判断条件的时候，会进入while子句，往后推移一个节点，同时不能加入结果链表中 * 重复上述判断，知道不符合循环语句的判断条件，然后跳出循环，再往后推移一个节点 * 之所以在跳出循环之后还要再后移一个节点，是因为循环中只是跳过了重复的二者中前面的一个！ * 此时，外层循环结束此轮，继续判断head是否为空，只要不为空，则外层循环继续 */
            // 进入循环时，确保head不会与上一节点相同
            if(head.next == null || head.val != head.next.val){
                tail.next = head;
                //如果相邻两节点不相同，则可以加入到结果链表中
                tail = head;
                //然后更新tail为当前节点head，确保tail总是在结果链表中的末端
            }
            //如果head与下一节点相同，跳过相同节点。必须先跳一次，而不能是两次（考虑多重复的情况）
            while(head.next != null && head.val == head.next.val){
                head = head.next;
            }
            //因为if中需要进行此更新操作一次，while中需要进行此更新操作两次， //但考虑到两个以上 如三个相同的节点相邻的情况，因此需要只在while子句中更新一次！！
            // 所以这里可以提出一个这样的操作到二者的公共区域
            head = head.next;
        }
        tail.next = null;
        //上述循环结束后，将tail.next设置为null，代表结果链表更新结束
        return dummy.next; //返回虚拟头节点的next指针即可。
    }


    /**
     * x 的平方根
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 0 ) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int left = 1;
        int right = x /2;
        while (left < right) {
            int mid = left + (right -left + 1) /2;
            if (mid > x/mid) {
                // 下轮的搜索区为[left, mid-1]
                right = mid-1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * x 的平方根
     * 牛顿迭代法 x + a/x >= 2
     * @param x
     * @return
     */
    public static int mySqrt2(int x) {
        long a = x;
        while (a * a > x ) {
            a = (x + x/a) /2;
        }
        return (int) a;
    }
}
