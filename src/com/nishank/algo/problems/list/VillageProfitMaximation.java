package com.nishank.algo.problems.list;

import java.util.*;

/**
 * Traverse list of villages to maximize profit. Each has village has some profit Pi and one can go from one village to other
 * only when Vi<Vj and profit of Vj is multiple of Vi.
 *
 * First line of input will be total number of villages
 * Second line of input will be space separated profit of each village in sequence
 *
 * Ex-
 * 6
 * 1 2 3 4 9 8
 *
 * Result: 15 (V0 -> V1 -> V3-> V5) (1->2->4 ->8)
 *
 * Created by Nishank Gupta on 04-Jul-18.
 */
public class VillageProfitMaximation {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String villageCount = s.nextLine();
        String villageProfit = s.nextLine();


        int[] villageProfitArr = new int[Integer.parseInt(villageCount)];

        //initialize cost of each village
        initializeArr(villageProfitArr, villageProfit);

        Map<Integer, List<Integer>> villageAdjMap = new HashMap();
        //prepare map of adjacency list for each village satisfying constraint
        initializeMap(villageAdjMap, villageProfitArr);

        //traverse map by starting at each of the village and finding max sum out of those
        traverseMap(villageAdjMap, villageProfitArr);

    }

    private static void traverseMap(Map<Integer, List<Integer>> villageAdjMap, int[] villageProfitArr) {

        int result = Integer.MIN_VALUE;
        //Map of village sum which is already traversed
        Map<Integer, Integer> villageSumMap = new HashMap();

        for (Integer village : villageAdjMap.keySet()) {

            int villageSum;

            if (villageSumMap.containsKey(village)) {
                villageSum = villageSumMap.get(village);
            } else {
                villageSum = getVillageSum(village, villageSumMap, villageAdjMap, villageProfitArr);
                villageSumMap.put(village, villageSum);
            }


            result = Math.max(result, villageSum);
        }

        System.out.println(result);
    }

    private static int getVillageSum(Integer village, Map<Integer, Integer> villageSumMap, Map<Integer, List<Integer>> villageAdjMap, int[] villageProfitArr) {

        if (villageSumMap.containsKey(village)) {
            return villageSumMap.get(village);
        }

        int result = villageProfitArr[village];

        for (Integer adjVillage : villageAdjMap.get(village)) {
            int villageSum = getVillageSum(adjVillage, villageSumMap, villageAdjMap, villageProfitArr);

            result = Math.max(result, villageProfitArr[village] + villageSum);
        }

        villageSumMap.put(village, result);

        return result;
    }

    private static void initializeMap(Map<Integer, List<Integer>> villageAdjMap, int[] villageProfitArr) {
        int index = 0;

        while (index < villageProfitArr.length) {
            List<Integer> adjList = new ArrayList();
            villageAdjMap.put(index, adjList);

            for (int i = index + 1; i < villageProfitArr.length; i++) {
                if (villageProfitArr[i] % villageProfitArr[index] == 0) {
                    adjList.add(i);
                }
            }

            ++index;
        }

    }

    private static void initializeArr(int[] villageProfitArr, String villageProfit) {
        int index = 0;

        for (String profit : villageProfit.split(" ")) {
            villageProfitArr[index++] = Integer.parseInt(profit);
        }
    }

}
