package sort;

import collections.StdRandom;

public class QuickSort {
    
    public static void sort(Comparable[] comparables) {
        StdRandom.shuffle(comparables);
        sort(comparables, 0, comparables.length - 1);
    }
    
    private static void sort(Comparable[] comparables, int lo, int hi) {
        if (lo >= hi) return;
        
//        if (hi - lo < 12) {
//            InsertionSort.sort(comparables, lo, hi);
//            return;
//        }
        
        int j = partition(comparables, lo, hi);
        sort(comparables, lo, j - 1);
        sort(comparables, j + 1, hi);
    }
    
    private static int partition(Comparable[] comparables, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable comparable = comparables[lo];
        while (true) {
            while (SortUtil.less(comparables[++i], comparable)) if (i == hi) break;
            while (SortUtil.less(comparable, comparables[--j])) ;//if (j == lo) break;
            if (i >= j) break;
            SortUtil.swap(comparables, i, j);
        }
        SortUtil.swap(comparables, lo, j);
        return j;
    }
    
    public static void threeWaySort(Comparable[] comparables) {
        StdRandom.shuffle(comparables);
        threeWaySort(comparables, 0, comparables.length - 1);
    }
    
    private static void threeWaySort(Comparable[] comparables, int lo, int hi) {
        if (hi <= lo) return;
        
//        if (hi - lo < 12) {
//            InsertionSort.sort(comparables, lo, hi);
//            return;
//        }
        
        int lt = lo, i = lo + 1, gt = hi;
        Comparable comparable = comparables[lo];
        while (i <= gt) {
            int cmp = comparables[i].compareTo(comparable);
            if (cmp < 0) SortUtil.swap(comparables, i++, lt++);
            else if (cmp > 0) SortUtil.swap(comparables, i, gt--);
            else i++;
        }
        threeWaySort(comparables, lo, lt - 1);
        threeWaySort(comparables, gt + 1, hi);
    }

}
