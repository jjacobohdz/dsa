/*
Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.

You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.

Write a function to return the maximum number of fruits in both the baskets.

Example 1:
Input: Fruit=['A', 'B', 'C', 'A', 'C']
Output: 3
Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']

Example 2:
Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
Output: 5
Explanation: We can put 3 'B' in one basket and two 'C' in the other basket. 
This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']
*/
import java.util.*;
class FruitsIntoBaskets {
    public static int fruitsIntoBaskets(char[] arr) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        while (end < arr.length) {
            char c = arr[end++];
            map.put(c, map.getOrDefault(c, 0) + 1);
            
            while (map.size() > 2) {
                c = arr[start++];
                
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) - 1);
                    
                    if (map.get(c) == 0) {
                        map.remove(c);
                    }
                }
            }
            max = Math.max(max, end - start);
        }
        return max;
    }
    
    public static void main(String[] args) {
        char[] arr ={'A', 'B', 'C', 'A', 'C'};
        int res = fruitsIntoBaskets(arr);
        System.out.println(res);
        
        arr = new char[]{'A', 'B', 'C', 'B', 'B', 'C'};
        res = fruitsIntoBaskets(arr);
        System.out.println(res);
    }
}