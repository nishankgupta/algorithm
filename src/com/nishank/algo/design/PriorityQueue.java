package com.nishank.algo.design;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nishank Gupta on 08-Aug-18
 * <p>
 * Implementation of heap as PriorityQueue
 */
public class PriorityQueue<T> {

    private Object[] heapData;// complete binary tree as heap
    private int length = 0; // current heap size
    private Map<T, Integer> heapMap; // data to index max, needed for heapify
    private Comparator<T> comparator;

    public PriorityQueue(Iterable<T> data, Comparator<T> comparator, int queueSize) {
        this.heapData = new Object[queueSize];
        this.heapMap = new HashMap();
        this.comparator = comparator;

        initialize(data);
    }

    private void initialize(Iterable<T> data) {
        if (data == null)
            return;

        for (T input : data) {
            heapData[length] = input;
            heapMap.put(input, length);
            ++length;
        }

        prepareHeap();
    }

    private void prepareHeap() {
        int lastParent = (length - 2) / 2;

        while (lastParent >= 0) {
            heapifyDown((T) heapData[lastParent--]);
        }
    }

    private void heapifyDown(T data) {
        int index = heapMap.get(data);
        int leftChild = 2 * index + 1;
        int rightChild = leftChild + 1;

        compareAndSet(index, leftChild);
        compareAndSet(index, rightChild);
    }

    private void heapifyUp(T data) {
        int index = heapMap.get(data);
        int parent = (index - 1) / 2;

        while (parent >= 0) {
            T parentData = (T) heapData[parent];

            if (comparator.compare(parentData, data) < 0) {
                heapData[parent] = data;
                heapMap.put(data, parent);

                heapData[index] = parentData;
                heapMap.put(parentData, index);

                index = parent;
                parent = index / 2;

                if (index == parent)
                    return;

                continue;

            }

            break;
        }
    }

    private void compareAndSet(int currIndex, int compareIndex) {
        T data = (T) heapData[currIndex];
        T max = data;

        if (compareIndex < length) {
            T child = (T) heapData[compareIndex];

            if (comparator.compare(max, child) < 0) {
                heapData[currIndex] = child;
                heapMap.put(child, currIndex);

                heapData[compareIndex] = max;
                heapMap.put(max, compareIndex);
                heapifyDown(max);
            }
        }
    }

    public T deque() {
        if (length < 0)
            return null;

        T first = (T) heapData[0];
        heapMap.remove(first);
        --length;

        if (length >= 1) {
            T last = (T) heapData[length];
            heapData[0] = last;
            heapMap.put(last, 0);
            heapData[length] = null;

            heapifyDown(last);
        } else {
            heapData[0] = null;
        }

        return first;

    }

    public void enqueue(T data) throws Exception {
        if (length >= 100)
            throw new Exception("Queue is full");

        heapData[length] = data;
        heapMap.put(data, length);
        ++length;

        heapifyUp(data);

    }
}
