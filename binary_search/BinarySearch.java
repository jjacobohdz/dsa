public class BinarySearch {
	public static int iterativeBinarySearch(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
		    int middle = left + (right - left) / 2;
		    if (arr[middle] == target) {
		        return middle;
		    } else if (arr[middle] < target) {
		        left = middle + 1;
		    } else {
		        right = middle - 1;
		    }
		}
		return -1;
	}
	
	public static int recursiveBinarySearch(int[] arr, int target) {
	    return recursiveBinarySearch(arr, target, 0, arr.length - 1);
	}
	
	private static int recursiveBinarySearch(int[] arr, int target, int left, int right) {
	    if (left > right) {
	        return -1;
	    }
	    int middle = left + (right - left) / 2;
	    if (arr[middle] == target) {
	        return middle;
	    } else if (arr[middle] < target) {
	        return recursiveBinarySearch(arr, target, middle + 1, right);
	    } else {
	        return recursiveBinarySearch(arr, target, left, middle - 1);
	    }
	}
	
	public static void main(String[] args) {
		int[] arr = {0,1,2,3,4,5,6,7,8,9};
		System.out.println("iterative");
		int idx = iterativeBinarySearch(arr, 5);
		System.out.println(idx);
		idx = iterativeBinarySearch(arr, 9);
		System.out.println(idx);
		idx = iterativeBinarySearch(arr, 0);
		System.out.println(idx);
		idx = iterativeBinarySearch(arr, 10);
		System.out.println(idx);
		
		System.out.println("recursive");
		idx = recursiveBinarySearch(arr, 5);
		System.out.println(idx);
		idx = recursiveBinarySearch(arr, 9);
		System.out.println(idx);
		idx = recursiveBinarySearch(arr, 0);
		System.out.println(idx);
		idx = recursiveBinarySearch(arr, 10);
		System.out.println(idx);
	}
}