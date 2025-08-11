/*
Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.

Example 1:
Intervals: [[1,4], [2,5], [7,9]]
Output: [[1,5], [7,9]]
Explanation: Since the first two intervals [1,4] and [2,5] overlap, we merged them into 
one [1,5].

Example 2:
Intervals: [[6,7], [2,4], [5,9]]
Output: [[2,4], [5,9]]
Explanation: Since the intervals [6,7] and [5,9] overlap, we merged them into one [5,9].
 
Example 3:
Intervals: [[1,4], [2,6], [3,5]]
Output: [[1,6]]
Explanation: Since all the given intervals overlap, we merged them into one.
*/
import java.util.*;
class MergeIntervals {
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
        public boolean overlaps(Interval other) {
            return this.start <= other.end;
        }
    }
    public static List<Interval> mergeIntervals(int[][] arr) {
        List<Interval> intervals = toIntervals(arr);
        Collections.sort(intervals);
        LinkedList<Interval> merged = new LinkedList<>();

        for(Interval interval: intervals) {
            if (merged.isEmpty() || !interval.overlaps(merged.getLast())) {
                merged.add(interval);
            } else {
                // overlaps
                int start = Math.min(interval.start, merged.getLast().start);
                int end = Math.max(interval.end, merged.getLast().end);
                merged.getLast().start = start;
                merged.getLast().end = end;
            }
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
        int[][] arr = {{1,4}, {2,5}, {7,9}};
        List<Interval> intervals = mergeIntervals(arr);
        printIntervals(intervals);

        arr = new int[][]{{6,7}, {2,4}, {5,9}};
        intervals = mergeIntervals(arr);
        printIntervals(intervals);

        arr = new int[][]{{1,4}, {2,6}, {3,5}};
        intervals = mergeIntervals(arr);
        printIntervals(intervals);
    }
}