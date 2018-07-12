package com.nishank.algo.problems.list;

/**
 * From X company stock prices collected every min for a day, find the max profit that could be made.
 * <p>
 * Conditions:
 * 1) No "shorting"—you need to buy before you can sell
 * 2) you can't buy and sell in the same time step—at least 1 minute has to pass
 * <p>
 * Ex:
 * stock_prices = [10, 7, 5, 8, 11, 9]
 * Result: 6 (Buy: 5, Sell: 11)
 * <p>
 * Soln: As last elment from the list cannot be bought. So iteration will start from back keeping start point as n-1 and
 * profit to min. Max element found so far would be stock_prices[n]. While iteration if current element is less than max
 * then difference is calculated and max profit is substituted. In other case, max element would be replaced.
 * <p>
 * Created by Nishank Gupta on 12-Jul-18.
 */
public class MaxProfitStock {

    public static void main(String[] args) {

        int[] stock_prices = {10, 7, 5, 8, 11, 9};

        int profit = Integer.MIN_VALUE;
        int index = stock_prices.length - 2;
        int maxPrice = stock_prices[stock_prices.length - 1];

        while (index >= 0) {
            int currentPrice = stock_prices[index];
            --index;

            if (currentPrice > maxPrice) {
                maxPrice = currentPrice;
                continue;
            }

            if ((maxPrice - currentPrice) > profit) {
                profit = maxPrice - currentPrice;
            }
        }


        System.out.println("Profit:" + profit);
    }
}
