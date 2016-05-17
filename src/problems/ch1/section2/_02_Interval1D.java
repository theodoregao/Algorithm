package problems.ch1.section2;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.2 Write an Interval1D client that takes an int value N as command-line argu-
ment, reads N intervals (each defined by a pair of double values) from standard input,
and prints all pairs that intersect.
 * @author shun
 *
 */
public class _02_Interval1D {
    
    public static void main(String[] args) {
        final int n = 100000;
        
        Interval[] intervals = new Interval[n];
        
        for (int i = 0; i < n; i++) {
            intervals[i] = Interval.randomInterval();
        }
        
        Arrays.sort(intervals);
        
//        for (int i = 0; i < intervals.length; i++) System.out.println(intervals[i]);
        
        quickFind(intervals);
        slowFind(intervals);
    }
    
    private static void slowFind(Interval[] intervals) {
        int n = intervals.length;
        long count = 0;
        long compareTime = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Interval first = intervals[i];
                Interval second = intervals[j];
                compareTime++;
                if (first.intersect(second)) {
//                    System.out.println(first + " intersect with " + second);
                    count++;
                }
            }
        }
        System.out.println(count + " / " + compareTime);
    }
    
    private static void quickFind(Interval[] intrvals) {
        int n = intrvals.length;
        long count = 0;
        long compareTime = 0;
        for (int i = 0; i < n; i++) {
            Interval interval = new Interval(intrvals[i].left, intrvals[i].right);
            int left = Arrays.binarySearch(intrvals, interval);
            interval.left = interval.right;
            int right = Math.abs(Arrays.binarySearch(intrvals, interval));
//            System.out.println(left + " : " + right);
            count += right - left - 2;
        }
        System.out.println(count);
    }
    
    private static class Interval implements Comparable<Interval> {
        double left, right;
        
        public Interval(double left, double right) {
            this.left = Math.min(left, right);
            this.right = Math.max(left, right);
        }
        
        public boolean intersect(Interval other) {
            return !(other.left > right || other.right < left);
        }
        
        @Override
        public String toString() {
            return "(" + left + ", " + right + ")";
        }
        
        public static Interval randomInterval() {
            return new Interval(StdRandom.uniform(), StdRandom.uniform());
        }

        @Override
        public int compareTo(Interval other) {
            return Double.compare(left, other.left);
        }
    }

}
