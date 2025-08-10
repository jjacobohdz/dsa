/*
Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space; after removing the duplicates in-place return the new length of the array.

Example 1:
Input: [2, 3, 3, 3, 6, 9, 9]
Output: 4
Explanation: The first four elements after removing the duplicates will be [2, 3, 6, 9].

Example 2:
Input: [2, 2, 2, 11]
Output: 2
Explanation: The first two elements after removing the duplicates will be [2, 11].

Leetcode:
https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
*/
class RemoveDuplicates {
    public static int removeDuplicates(int[] arr) {
        int j = 1;
        for(int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                arr[j++] = arr[i + 1];
            }
        }
        return j;
    }
    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 3, 6, 9, 9};
        int res = removeDuplicates(arr);
        System.out.println(res);

        arr = new int[]{2, 2, 2, 11};
        res = removeDuplicates(arr);
        System.out.println(res);
    }
}


