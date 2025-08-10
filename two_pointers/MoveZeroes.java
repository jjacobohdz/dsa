/*
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]

Leetcode:
https://leetcode.com/problems/move-zeroes/description/
*/
class MoveZeroes {
    public static int moveZeroes(int[] arr) {
        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != 0) {
                int tmp = arr[j];
                arr[j++] = arr[i];
                arr[i] = tmp;
            }
        }
        return j;
    }
    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        moveZeroes(arr);
        printArray(arr);

        arr = new int[]{0};
        moveZeroes(arr);
        printArray(arr);
    }
    
    private static void printArray(int[] arr) {
        for(int n: arr) {
            System.out.print(n + ",");
        }
        System.out.println();
    }
}


