/*
Create an algorithm to output all the different ways in which3 persons(1, 2, 3) can be seated in 3 chairs
*/
import java.util.*;
class Permutations {
    public static List<List<Integer>> permutations(int n) {
        List<List<Integer>> permutations = new ArrayList<>();
        backtrack(permutations, new ArrayList<>(), n);
        return permutations;
    }
    
    private static void backtrack(List<List<Integer>> permutations, List<Integer> permutation, int n) {
        if (permutation.size() == n) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        
        for(int i = 1; i <= n; i++) {
            if (permutation.contains(i)) continue;
            permutation.add(i);
            backtrack(permutations, permutation, n);
            permutation.remove(permutation.size() - 1);
        }
    }
    
    private static void printPermutations(List<List<Integer>> permutations) {
        for(List<Integer> permutation: permutations) {
            System.out.println(permutation);
        }
        System.out.println("====");
    }
    
    public static void main(String[] args) {
        List<List<Integer>> permutations = permutations(3);
        printPermutations(permutations);

        permutations = permutations(4);
        printPermutations(permutations);

        permutations = permutations(5);
        printPermutations(permutations);
    }
}