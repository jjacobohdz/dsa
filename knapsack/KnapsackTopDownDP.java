/*
Given two integer arrays to represent weights and profits of ‘N’ items, 
we need to find a subset of these items which will give us maximum profit such that their cumulative weight is not more than a given number ‘C’. 
Each item can only be selected once, which means either we put an item in the knapsack or we skip it.

Example 1:
weights = {1,2,3,5}
profits = {1,6,10,16}
capacity = 7

Output = profit = 22

Example 2:
weights = {1,2,3,5}
profits = {1,6,10,16}
capacity = 6

Output = profit = 17
*/
import java.util.*;
/*
01 knapsack (item is put or not in the knapsack)

Adding memoization to cache already results already stored.
For the cache, we use the number of elements and the capacity since both they will be variable at runtime (ie. for a given index (element) and capacity combination, what is the maximum profit)
*/
class KnapsackTopDownDP {
    
    public static int knapsack(int[] weights, int[] profits, int capacity) {
        return knapsack(weights, profits, capacity, 0, new Integer[weights.length + 1][capacity + 1]);
    }
    
    private static int knapsack(int[] weights, int[] profits, int capacity, int index, Integer[][] cache) {
        if (index >= profits.length || capacity <= 0) {
            return 0;
        }
        
        if (cache[index][capacity] == null) {
            int take = 0;
            if (weights[index] <= capacity) {
                take = profits[index] + knapsack(weights, profits, capacity - weights[index], index + 1, cache);
            }
            int skip = knapsack(weights, profits, capacity, index + 1,cache);
            
            cache[index][capacity] = Math.max(take, skip);
        }
        
        return cache[index][capacity];
    }
    
    public static void main(String[] args) {
        int[] weights = {1,2,3,5};
        int[] profits = {1,6,10,16};
        int capacity = 7;
        System.out.println(knapsack(weights, profits, capacity));

        weights = new int[]{1,2,3,5};
        profits = new int[]{1,6,10,16};
        capacity = 6;
        System.out.println(knapsack(weights, profits, capacity));
    }
}