package com.nishank.algo.problems.list;

/** Java program to search an element in an array where difference between all elements is max k
 *
 * Soln: As difference between consecutive elements can be max of k, so rather then iterating each number, based on
 * difference between element to search and current number to factor of k, iterations can be jumped
 */
public class KDiffElementSearch {

    public static int search(int arr[], int key, int eleDiff) {

        for (int i = 0; i < arr.length; ) {

            if (arr[i] == key) {
                System.out.println("Element is present in the array at index: " + i);
                return i;
            }

            i = i + Math.max(1, Math.abs((key - arr[i]) / eleDiff));
        }

        System.out.println("Element is not present in the array");
        return -1;
    }

    public static void main(String[] args) {

        int arr[] = {20, 10, -10, 0, 20, 40};
        int key = 40;
        int eleDiff = 20;

        search(arr, key, eleDiff);
    }
}
