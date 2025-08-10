/*
Given an array of positive numbers and a positive number ‘k’ find the maximum sum of any contiguous subarray of size ‘k’.

Example 1:
Input: [2, 1, 5, 1, 3, 2], k=3 
Output: 9
Explanation: Subarray with maximum sum is [5, 1, 3].

Example 2:
Input: [2, 3, 4, 1, 5], k=2 
Output: 7
Explanation: Subarray with maximum sum is [3, 4].
*/
class MaximumSubarrayOfSizeK {
    public static int maximumSubarrayOfSizeK(int[] arr, int k) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        while (end < arr.length) {
            sum += arr[end++];
            if (end - start >= k) {
                max = Math.max(max, sum);
                sum -= arr[start++];
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        int max = maximumSubarrayOfSizeK(arr, k);
        System.out.println(max);
        
        arr = new int[]{2, 3, 4, 1, 5};
        k = 2;
        max = maximumSubarrayOfSizeK(arr, k);
        System.out.println(max);
    }
}