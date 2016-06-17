package sort;

import collections.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

class SortUtil {
    
    static void swap(Comparable[] comparables, int i, int j) {
        Comparable temp = comparables[i];
        comparables[i] = comparables[j];
        comparables[j] = temp;
    }
    
    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    static boolean isSorted(Comparable[] comparables) {
        for (int i = 1; i < comparables.length; i++)
            if (less(comparables[i], comparables[i - 1]))
                return false;
        return true;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int[] num = StdRandom.sample(0, size * 10, size);
//        for (int i = 0; i < size; i++) num[i] = i % 2;
//        Arrays.sort(num);
        Stopwatch stopwatch = new Stopwatch();
        Integer[] nums = new Integer[size];
        for (int i = 0; i < nums.length; i++) nums[i] = num[i];
        QuickSort.threeWaySort(nums);
        System.out.println(stopwatch.elapsedTime());
        System.out.println("sorted " + SortUtil.isSorted(nums));
    }
}