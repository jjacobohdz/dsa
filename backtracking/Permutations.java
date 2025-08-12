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
		
        /*
		say that n = 3 then all possible permutations are:
			[1, 2, 3]
			[1, 3, 2]
			[2, 1, 3]
			[2, 3, 1]
			[3, 1, 2]
			[3, 2, 1]
		
		1)
		the first permutation will be: 1,2,3
		then call stack will remove the last element: 1,2 and we complete the 2,3 loop
		then call stack will remove the last element: 1 and we complete the 1,2 loop
		
		then 1,3 loop 1,3 will start, then 3,1 doesn't do anything bc 1 already exist in combination
		3,2 will add 2 resultin in 1,3,2 which is the 2nd combination
		
		2)
		since combinations are not unique, we don't use an additional index
		*/
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