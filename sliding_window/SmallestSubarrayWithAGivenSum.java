/*
Given an array of positive numbers and a positive number ‘S’, find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’. Return 0, if no such subarray exists.

Example 1:
Input: [2, 1, 5, 2, 3, 2], S=7 
Output: 2
Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].

Example 2:
Input: [2, 1, 5, 2, 8], S=7 
Output: 1
Explanation: The smallest subarray with a sum greater than or equal to '7' is [8].

Example 3:
Input: [3, 4, 1, 1, 6], S=8 
Output: 3
Explanation: Smallest subarrays with a sum greater than or equal to '8' are [3, 4, 1] or [1, 1, 6].
*/
class SmallestSubarrayWithAGivenSum {
    public static int smallestSubarrayWithAGivenSum(int[] arr, int s) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (end < arr.length) {
            sum += arr[end++];
            while (sum >= s) {
                min = Math.min(min, end - start);
                sum -= arr[start++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 2, 3, 2};
        int s = 7;
        int min = smallestSubarrayWithAGivenSum(arr, s);
        System.out.println(min);
        
        arr = new int[]{2, 1, 5, 2, 8};
        s = 7;
        min = smallestSubarrayWithAGivenSum(arr, s);
        System.out.println(min);

        arr = new int[]{3, 4, 1, 1, 6};
        s = 8;
        min = smallestSubarrayWithAGivenSum(arr, s);
        System.out.println(min);
    }
}