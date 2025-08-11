/*
Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.

Example 1:
Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
Output: [[1,3], [4,7], [8,12]]
Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].

Example 2:
Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
Output: [[1,3], [4,12]]
Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].

Example 3:
Input: Intervals=[[2,3],[5,7]], New Interval=[1,4]
Output: [[1,4], [5,7]]
Explanation: After insertion, since [1,4] overlaps with [2,3], we merged them into one [1,4].
*/
import java.util.*;
class InsertInterval {
    private static class Interval implements Comparable<Interval> {
        private int start;
        private int end;
        private Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public String toString() {
            return "{" + start + "," + end + "}";
        }
        @Override
        public int compareTo(Interval other) {
            return Integer.compare(this.start, other.start);
        }
        // if last interval overlaps current
        // (current starts before last ends)
        public boolean overlaps(Interval last) {
            return this.start <= last.end;
        }
    }
    public static List<Interval> insertInterval(int[][] matrix, int[] arr) {
        List<Interval> intervals = toIntervals(matrix);
        // already sorted
        // Collections.sort(intervals);
        
        LinkedList<Interval> merged = new LinkedList<>();
        
        // skip all the intervals that end before the new one starts
        int i = 0;
        Interval interval = new Interval(arr[0], arr[1]);
        while (!interval.overlaps(intervals.get(i))) {
            merged.add(intervals.get(i++));
        }
        
        // merge all intervals that overlap with the new one
        while (i < intervals.size() && intervals.get(i).overlaps(interval)) {
            interval.start = Math.min(interval.start, intervals.get(i).start);
            interval.end = Math.max(interval.end, intervals.get(i).end);
            i++;
        }
        merged.add(interval);

        // add intervals that start after new one ended
        while(i < intervals.size()) {
            merged.add(intervals.get(i++));
        }
        
        return merged;
    }
    
    private static List<Interval> toIntervals(int[][] matrix) {
        List<Interval> intervals = new ArrayList<>();
        for(int[] arr: matrix) {
            intervals.add(new Interval(arr[0], arr[1]));
        }
        return intervals;
    }
    
    private static void printIntervals(List<Interval> intervals) {
        for(Interval interval: intervals) {
            System.out.print(interval + ", ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[][] intervals = {{1,3}, {5,7}, {8,12}};
        int[] interval = {4,6};
        List<Interval> res = insertInterval(intervals, interval);
        printIntervals(res);

        intervals = new int[][]{{1,3}, {5,7}, {8,12}};
        interval = new int[]{4,10};
        res = insertInterval(intervals, interval);
        printIntervals(res);

        intervals = new int[][]{{2,3}, {5,7}};
        interval = new int[]{1,4};
        res = insertInterval(intervals, interval);
        printIntervals(res);
    }
}