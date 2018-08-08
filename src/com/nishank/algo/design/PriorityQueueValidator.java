package com.nishank.algo.design;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Nishank Gupta on 08-Aug-18
 */
public class PriorityQueueValidator {

    public static void main(String[] args) throws Exception {
        List<Integer> data = Arrays.asList(1, 5, 2, 8, 4, 6, 10, 4);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue(data, new IntegerComparator(), 100);

        priorityQueue.enqueue(16);
        priorityQueue.enqueue(0);
        priorityQueue.enqueue(8);


        Integer item = priorityQueue.deque();

        while (item != null) {
            System.out.println(item);
            item = priorityQueue.deque();
        }
    }

}

class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }

}
