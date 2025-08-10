/*
Given a sorted array, create a new array containing squares of all the number of the input array in the sorted order.

Example 1:
Input: [-2, -1, 0, 2, 3]
Output: [0, 1, 4, 4, 9]

Example 2:
Input: [-3, -1, 0, 1, 2]
Output: [0 1 1 4 9]

Leetcode:
https://leetcode.com/problems/squares-of-a-sorted-array/description/
*/
class SquaringASortedArray {
    public static int[] squaringASortedArray(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        int idx = arr.length - 1;
        int[] res = new int[arr.length];
        while (i <= j) {
            int ai = Math.abs(arr[i]);
            int aj = Math.abs(arr[j]);
            int max = Math.max(ai, aj);
            
            res[idx--] = (int) Math.pow(max, 2);
            
            if (ai < aj) {
                j--;
            } else {
                i++;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = {-2, -1, 0, 2, 3};
        int[] res = squaringASortedArray(arr);
        printArray(res);

        arr = new int[]{-3, -1, 0, 1, 2};
        int[] res2 = squaringASortedArray(arr);
        printArray(res2);
    }
    
    private static void printArray(int[] arr) {
        for(int n: arr) {
            System.out.print(n + ",");
        }
        System.out.println();
    }
}


