package com.nishank.algo.problems.list;

/**
 * You are given an array [a1 To an] and we have to construct another array [b1 To bn]
 * where bi = a1*a2*…*an/ai. you are allowed to use only constant space and the time complexity is O(n).
 * No divisions are allowed.
 * <p>
 * Soln:
 * <p>
 * In outpur array, keep incremental product from backward and in input array, keep incremental product from forward.
 * From index i in output array, result will be
 * <p>
 * b[i ]= a[i-1] * b[i+1]
 * <p>
 * Ex-
 * <p>
 * a = [8, 2, 10, 4]
 * o =[]
 * <p>
 * Step 1
 * o =  [640, 80, 40, 4]
 * <p>
 * Step 2
 * a = [8, 16, 160, 640]
 * <p>
 * Step 3
 * <p>
 * o = [80, 320, 64, 160]
 * <p>
 * <p>
 * Created by Nishank Gupta on 18-Jul-18.
 */
public class FactorialExclusive {

    public static void main(String[] args) {
        int[] input = {2, 5, 12, 10, 4};

        int[] result = factorialExclusive(input);

        if (input != null) {
            for (int a : result) {
                System.out.println(a);
            }
        }
    }

    private static int[] factorialExclusive(int[] input) {

        if (input == null || input.length == 0) {
            return input;
        }

        int size = input.length;
        int[] out = new int[size];


        //product from backward
        out[size - 1] = input[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            out[i] = input[i] * out[i + 1];
        }

        //product from forward
        for (int i = 1; i < size; i++) {
            input[i] = input[i] * input[i - 1];
        }

        //generate result
        if (size > 1) {
            out[0] = out[1];

            for (int i = 1; i < size - 1; i++) {
                out[i] = input[i - 1] * out[i + 1];
            }

            out[size - 1] = input[size - 2];
        }

        return out;
    }
}
