package com.nishank.algo.problems.list;

import com.nishank.algo.bean.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * <p>
 * <p>
 * Soln: Prepare heap from 1st element of all the lists. Pop out the top and replace it with next element in the list
 * and heapify. If one of the list, is completed and replace the root with last element in heap and heapify again.
 * <p>
 * Created by Nishank Gupta on 28-Jul-18.
 */
public class MergeKSortedList {

    public static void main(String[] args) {

        ListNode<Integer>[] data = prepareData();
        Solution solution = new Solution();

        ListNode<Integer> result = solution.mergeKLists(data);

        while (result != null) {
            System.out.print(result.getValue() + ",");
            result = result.getNext();
        }
    }

    private static ListNode<Integer>[] prepareData() {
        ListNode[] result = new ListNode[3];

        ListNode<Integer> node1 = new ListNode<Integer>(1);
        node1.setNext(new ListNode<Integer>(4));
        node1.getNext().setNext(new ListNode<Integer>(5));
        result[0] = node1;

        ListNode<Integer> node2 = new ListNode<Integer>(1);
        node2.setNext(new ListNode<Integer>(3));
        node2.getNext().setNext(new ListNode<Integer>(4));
        result[1] = node2;

        ListNode<Integer> node3 = new ListNode<Integer>(2);
        node3.setNext(new ListNode<Integer>(6));
        result[2] = node3;

        return result;
    }
}

class Solution {
    public ListNode<Integer> mergeKLists(ListNode<Integer>[] lists) {

        if (lists == null || lists.length < 1) {
            return null;
        }

        ListNode<Integer> head = null;
        ListNode<Integer> curr = null;
        int arrSize = lists.length; //counter to keep how many lists are finished traversing
        int counter = 0;

        //remove null lists
        while (counter < arrSize) {
            if (lists[counter] == null) {
                lists[counter] = lists[arrSize - 1];
                lists[arrSize - 1] = null;
                --arrSize;
            } else {
                ++counter;
            }
        }


        prepareHeap(lists, arrSize); //prepare the heap of first element of each sorted list

        while (arrSize > 1) {

            ListNode<Integer> nextEle = lists[0];

            if (head == null) {
                head = nextEle;
                curr = nextEle;
            } else {
                curr.setNext(nextEle);
                curr = nextEle;
            }

            if (nextEle.getNext() != null) {

                lists[0] = nextEle.getNext(); //replace top of heap with next element in the list and call heapify
            } else {
                lists[0] = lists[arrSize - 1]; //one list traversal is over so replace top with last and heapify
                lists[arrSize - 1] = null;
                --arrSize;
            }

            heapify(lists[0], 0, lists, arrSize);
        }

        if (arrSize == 1) {
            if (curr == null) {
                head = lists[0];
            } else {
                curr.setNext(lists[0]);
            }
        }

        return head;
    }

    private void heapify(ListNode<Integer> node, int index, ListNode<Integer>[] lists, int maxIndex) {
        ListNode<Integer> max = node;
        int childIndexStart = 2 * index + 1;

        if (childIndexStart < maxIndex && lists[childIndexStart].getValue() < max.getValue()) {
            lists[index] = lists[childIndexStart];
            lists[childIndexStart] = max;
            heapify(max, childIndexStart, lists, maxIndex);
            max = lists[index];
        }

        ++childIndexStart;

        if (childIndexStart < maxIndex && lists[childIndexStart].getValue() < max.getValue()) {
            lists[index] = lists[childIndexStart];
            lists[childIndexStart] = max;
            heapify(max, childIndexStart, lists, maxIndex);
        }
    }

    private void prepareHeap(ListNode<Integer>[] lists, int arrSize) {

        int lastParentIndex = (arrSize - 2) / 2;

        while (lastParentIndex >= 0) {
            heapify(lists[lastParentIndex], lastParentIndex, lists, arrSize);
            --lastParentIndex;
        }

    }
}
