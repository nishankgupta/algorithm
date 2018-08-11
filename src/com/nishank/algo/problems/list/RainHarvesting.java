package com.nishank.algo.problems.list;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * <p>
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * <p>
 * Soln:
 * <p>
 * Best: LeetCode: Time:(n), Space(1)
 * <p>
 * Water can only be trapped when height from left bar and right bar are equal or more. Keep  two pointer and leftMax,
 * rightMax. Till left height is smaller than right, keep moving from left until value is less than leftMax and add the
 * difference of height to sum. Once left height become equal or greater than right, start from right and do the same
 * computation from rightMax. Keep shifting direction until both left and right meet at some point
 * <p>
 * Alternate: Nishank: Time:(n), Space(n)
 * Water be trapped between two bars which are either equal or one of them is higher and rest bars in between is smaller.
 * Maintain an auxialiary array, which will keep first highest bar next to each of the bar(bottom to top). If there is
 * no higher bar on right then keep the second best bar(top to bottom). Using Stack this can be done.
 * <p>
 * Start the iteration and maintain min which will left of the bar and max which will be right of the bar. Calculate the
 * difference and add it to sum.
 * <p>
 * Created by Nishank Gupta on 11-Aug-18.
 */
public class RainHarvesting {

    public static void main(String[] args) {

        //UC1: Ans 6
        int[] uc1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(computeTrap(uc1));
        System.out.println(computeBestTrap(uc1));

        //UC2: Ans 5
        int[] uc2 = {0, 0, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(computeTrap(uc2));
        System.out.println(computeBestTrap(uc2));

        //UC3: Ans 7
        int[] uc3 = {0, 0, 0, 2, 1, 0, 1, 3, 0, 1, 2, 1};
        System.out.println(computeTrap(uc3));
        System.out.println(computeBestTrap(uc3));

        //UC4: Ans 14
        int[] uc4 = {0, 0, 0, 2, 1, 0, 1, 3, 0, 1, 2, 1, 1, 3};
        System.out.println(computeTrap(uc4));
        System.out.println(computeBestTrap(uc4));

        //UC5: Ans 0
        int[] uc5 = {0, 0, 0};
        System.out.println(computeTrap(uc5));
        System.out.println(computeBestTrap(uc5));

        //UC6: Ans 4
        int[] uc6 = {1, 2, 0, 3, 4, 5, 6, 5, 4, 3, 0, 2, 1};
        System.out.println(computeTrap(uc6));
        System.out.println(computeBestTrap(uc6));

        //UC7: Ans 1
        int[] uc7 = {4, 2, 3};
        System.out.println(computeTrap(uc7));
        System.out.println(computeBestTrap(uc7));
    }

    public static int computeBestTrap(int[] input) { //Time: O(n) , Space; O(1)
        int left = 0;
        int right = input.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int sum = 0;

        while (left < right) {
            if (input[left] < input[right]) {
                if (input[left] >= leftMax) {
                    leftMax = input[left];
                } else {
                    sum += leftMax - input[left];
                }

                ++left;
            } else {
                if (input[right] >= rightMax) {
                    rightMax = input[right];
                } else {
                    sum += rightMax - input[right];
                }

                --right;
            }
        }


        return sum;
    }

    public static int computeTrap(int[] input) { //Time: O(n) , Space; O(n)

        if (input == null || input.length == 0)
            return 0;

        int[] nextMax = new int[input.length];

        setNextMax(input, nextMax); //immediate element which should be max

        return computeTrap(input, nextMax);
    }

    private static int computeTrap(int[] input, int[] nextMax) {
        int i = 0;
        int sum = 0;
        int min = 0;
        int max = 0;

        while (i < input.length) {
            if (input[i] > 0 && nextMax[i] > 0) { //set min, max to first positive value and nextMax
                min = Math.min(input[i], nextMax[i]);
                max = nextMax[i];
                break;
            }

            i++;
        }

        i++;

        while (i < input.length) {
            if (input[i] < max) { //set the difference till max is reached
                sum += Math.abs(min - input[i]);
                i++;
                continue;
            }

            if (input[i] >= max) {
                min = Math.min(input[i], nextMax[i]);
                max = nextMax[i];
            }

            i++;
        }

        return sum;
    }

    private static void setNextMax(int[] input, int[] nextMax) {
        Stack<Integer> stack = new Stack();

        int last = input[input.length - 1];
        stack.push(last);
        nextMax[input.length - 1] = -1;

        for (int i = input.length - 2; i >= 0; i--) {
            int curr = input[i];
            last = stack.pop();

            while (last < curr) {
                if (stack.isEmpty()) {
                    nextMax[i] = last;
                    stack.push(curr);
                    break;
                }

                last = stack.pop();
            }

            if (last >= curr) {
                nextMax[i] = last;
                stack.push(last);
                stack.push(curr);
            }
        }
    }
}

