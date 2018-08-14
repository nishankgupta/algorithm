package com.nishank.algo.problems.list;

import java.util.*;
import java.util.stream.Collectors;

/**
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 * <p>
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according
 * to the following rules:
 * <p>
 * 1) Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * 2) Every worker in the paid group must be paid at least their minimum wage expectation.
 * <p>
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * <p>
 * Example 2:
 * <p>
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately
 * <p>
 * Soln:
 * <p>
 * Brute force solution is implemented here. Pick a worker iteratively and consider that as base wage for that
 * quality. Update minimum wage for each quality. Filter out all the workers who are above the minimum wage for their
 * quality. Sort the result and first K would be the answer.
 * <p>
 * Time: O(n2logn)
 * Space: O(n)
 * <p>
 * Efficient solution is implemented here: https://leetcode.com/articles/minimum-cost-to-hire-k-workers/
 * <p>
 * Created by Nishank Gupta on 14-Aug-18.
 */
public class HireKWorker {

    public static void main(String[] args) {
        int[] quality = {10, 20, 5};
        int[] wage = {70, 50, 30};
        int K = 2;
        System.out.println(new HireKWorker().mincostToHireWorkers(quality, wage, K));
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {

        Map<Integer, Double> qualityWageMap = new HashMap();
        List<QualityWage> qualityWageList = new ArrayList();

        for (int i = 0; i < quality.length; i++) {
            QualityWage qualityWage = new QualityWage(quality[i], wage[i]);
            qualityWageMap.putIfAbsent(quality[i], (double) 0);
            qualityWageList.add(qualityWage);
        }

        //Collections.sort(qualityWageList);

        //System.out.println(qualityWageList);
        Double cost = Double.POSITIVE_INFINITY;

        for (QualityWage qualityWage : qualityWageList) {
            int basePrice = qualityWage.wage;
            int baseQuality = qualityWage.quality;
            int workerCount = K;
            Double workerCost = Double.valueOf(0);
            List<QualityWage> workerSelection = new ArrayList();

            qualityWageMap.entrySet().forEach(entry -> {
                entry.setValue(((double) entry.getKey() / baseQuality) * basePrice);
            });

            List<QualityWage> eligibleWorkers = qualityWageList.stream().map(worker -> {
                worker.paidWage = qualityWageMap.get(worker.quality);
                return worker;
            }).filter(worker -> worker.wage <= worker.paidWage).collect(Collectors.toList());

            Collections.sort(eligibleWorkers);

            if (eligibleWorkers.size() >= K) {
                workerSelection = eligibleWorkers.stream().limit(K).collect(Collectors.toList());
                workerCost = workerSelection.stream().mapToDouble(worker -> worker.paidWage).sum();
                //System.out.println("Cost:" + workerCost + "\n" + workerSelection);
                cost = Math.min(cost, workerCost);
            }
        }

        return cost;
    }

    private class QualityWage implements Comparable<QualityWage> {
        int quality;
        int wage;
        double paidWage;

        public QualityWage(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            paidWage = wage;
        }

        @Override
        public int compareTo(QualityWage o) {

            if (this.paidWage == o.paidWage)
                return 0;
            if (this.paidWage < o.paidWage)
                return -1;

            return 1;
        }

        @Override
        public String toString() {
            return "Quality:" + quality + ",Wage:" + wage + ",PaidWage:" + paidWage;
        }
    }
}
