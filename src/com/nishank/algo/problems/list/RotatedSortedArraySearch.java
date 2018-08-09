package com.nishank.algo.problems.list;

/**
 * If you are given an array which was initially increasingly sorted(duplicate allowed) but later rotated by few rounds,
 * how can you efficiently search for an element in this array
 * <p>
 * <p>
 * Soln: Similar to binary search, if we split the array into half, one half will be sorted and other half wont. If
 * element is in sorted half then do binary search else other half is subproblem and can be recursively solved
 * <p>
 * Time complexity: O(logn)
 * Space complexity: O(1)
 * <p>
 * <p>
 * Created by Nishank Gupta on 09-Aug-18.
 */
public class RotatedSortedArraySearch {

    public static void main(String[] args) {
        int[] input = {50, 50, 55, 60, 70, 80, 10, 20, 20, 20, 30, 40, 50, 50};

        System.out.println(search(input, 20));
        System.out.println(search(input, 60));
        System.out.println(search(input, 50));
        System.out.println(search(input, 100));
    }

    public static int search(int[] input, int element) {
        if (input == null || input.length == 0)
            return -1;

        return searchInRange(0, input.length - 1, input, element);
    }

    private static int searchInRange(int start, int end, int[] input, int element) {

        if (start <= end) {
            int mid = (start + end) / 2;

            if (input[mid] == element)
                return mid;


            if (mid + 1 <= end) {
                if (isSorted(mid + 1, end, input)) {
                    if (input[mid + 1] <= element && input[end] >= element) { //if element in between do binary search
                        return binarySearch(mid + 1, end, input, element);
                    }

                    return searchInRange(start, mid - 1, input, element); //else search in other half
                }
            }

            if (mid - 1 >= start) {
                if (isSorted(start, mid - 1, input)) {
                    if (input[start] <= element && input[mid - 1] >= element) { //if element in between do binary search
                        return binarySearch(start, mid - 1, input, element);
                    }

                    return searchInRange(mid + 1, end, input, element); //else search in other half
                }
            }
        }

        return -1;
    }

    private static int binarySearch(int start, int end, int[] input, int element) {
        if (start <= end) {
            int mid = (start + end) / 2;

            if (input[mid] == element)
                return mid;

            if (input[mid] < element)
                return binarySearch(mid + 1, end, input, element);

            return binarySearch(start, mid - 1, input, element);
        }

        return -1;
    }

    private static boolean isSorted(int start, int end, int[] input) {
        while (end > start && input[start] == input[end]) {
            --end;
        }

        return input[start] <= input[end];
    }
}
