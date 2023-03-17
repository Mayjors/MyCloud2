package com.example.algorithm.offer;

public class ShowMeBug {
    public static class Node {
        public int data;
        public Node next;

        public Node(int data, Node nxt) {
            this.data = data;
            this.next = nxt;
        }
    }

    /**
     * 思路，定义两个浮动游标，随之链表移动而移动，完成整个链表的遍历
     * @param head
     */
    public void solution(Node head) {
        Node cur = head;
        Node first = head;
        Node second = head.next;
        Node curseA = first;//游标A
        Node curseB = second;//游标B

//        while (curseB != null) {
//
//            if (curseB.next != null) {
//                curseA.next = curseB.next;
//                curseA = curseA.next;
//                curseB.next = curseA.next;
//                curseB = curseB.next;
//
//            } else {
//                curseA.next = null;
//                curseB.next = null;
//                break;
//            }
//        }

        while (curseA != null && curseA.next != null) {
            curseA.next = curseA.next.next;
            curseA = curseA.next;
        }
        while (curseB != null && curseB.next != null) {
            curseB.next = curseB.next.next;
            curseB = curseB.next;
        }
        printNode(first);
        printNode(second);
    }

    private static void printNode(Node head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }


    public static void main(String[] args) {

        ShowMeBug showMeBug = new ShowMeBug();

        Node node7 = new Node(7, null);
        Node node6 = new Node(6, node7);
        Node node5 = new Node(5, node6);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        showMeBug.solution(node1);
        //printNode(node1);
    }
}
