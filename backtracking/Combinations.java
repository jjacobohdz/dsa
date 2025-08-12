/*
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

Example 2:
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.
*/
import java.util.*;
class Combinations {
    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        backtrack(combinations, new ArrayList<>(), n, k, 1);
        return combinations;
    }
    
    private static void backtrack(List<List<Integer>> combinations, List<Integer> combination, int n, int k, int index) {
        if (combination.size() == k) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        
        // since combinations are unique, we use an additional index
        for(int i = index; i <= n; i++) {
            combination.add(i);
            backtrack(combinations, combination, n, k, i + 1);
            combination.remove(combination.size() - 1);
        }
    }
    
    private static void printCombinations(List<List<Integer>> combinations) {
        for(List<Integer> combination: combinations) {
            System.out.print(combination + ",");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        List<List<Integer>> combinations = combinations = combinations(4, 2);
        printCombinations(combinations);
        
        combinations = combinations(1, 1);
        printCombinations(combinations);
    }
}