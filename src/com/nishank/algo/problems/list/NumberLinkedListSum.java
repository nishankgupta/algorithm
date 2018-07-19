package com.nishank.algo.problems.list;

import com.nishank.algo.bean.ListNode;

import javax.xml.soap.Node;

/**
 * You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1’s digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
 * Output: 8 -> 0 -> 8
 * <p>
 * Soln:
 * From both the list keep adding the sum and maintain carry
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * <p>
 * Created by Nishank Gupta on 19-Jul-18.
 */
public class NumberLinkedListSum {

    public static void main(String[] args) {

        ListNode<Integer> a = createListA();
        printNum(a);
        ListNode<Integer> b = createListB();
        printNum(b);

        ListNode<Integer> sum = sum(a, b);
        printNum(sum);
    }

    private static void printNum(ListNode<Integer> list) {
        String result = "";

        while (list != null) {
            result = list.getValue() + "" + result;
            list = list.getNext();
        }

        System.out.println(result);
    }

    private static ListNode<Integer> sum(ListNode<Integer> a, ListNode<Integer> b) {

        ListNode<Integer> result = null;
        ListNode<Integer> parent = null;
        int carry = 0;
        int aValue = 0;
        int bValue = 0;
        int sum = 0;

        while (a != null || b != null) {

            if (a != null) {
                aValue = a.getValue();
                a = a.getNext();
            } else {
                aValue = 0;
            }

            if (b != null) {
                bValue = b.getValue();
                b = b.getNext();
            } else {
                bValue = 0;
            }

            sum = aValue + bValue + carry;
            carry = 0;

            if (sum >= 10) {
                carry = 1;
                sum = sum - 10;
            }

            ListNode<Integer> node = new ListNode<>(sum);

            if (parent == null) {
                result = parent = node;
            } else {
                parent.setNext(node);
                parent = node;
            }
        }


        if (carry == 1) {
            parent.setNext(new ListNode<>(carry));
        }

        return result;
    }

    private static ListNode<Integer> createListB() {

        ListNode<Integer> head = new ListNode<>(5);
        ListNode<Integer> next = new ListNode<>(9);
        head.setNext(next);

        next.setNext(new ListNode<>(2));

        return head;
    }

    private static ListNode<Integer> createListA() {

        ListNode<Integer> head = new ListNode<>(3);
        ListNode<Integer> next = new ListNode<>(1);
        head.setNext(next);

        next.setNext(new ListNode<>(5));

        return head;
    }

}
