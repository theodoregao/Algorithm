package sort;

import java.util.Random;

public class QuickSort {
    
    public static void sort(int items[]) {
        shuffle(items);
        sortInternal(items, 0, items.length - 1);
    }
    
    private static void sortInternal(int[] items, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(items, lo, hi);
        sortInternal(items, lo, j - 1);
        sortInternal(items, j + 1, hi);
    }
    
    private static int partition(int[] items, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (items[lo] > items[++i]) if (i >= hi) break;
            while (items[lo] < items[--j]) ;
            if (i >= j) break;
            SortUtil.swap(items, i, j);
        }
        SortUtil.swap(items, lo, j);
        return j;
    }
    
    private static void shuffle(int[] items) {
        Random random = new Random();
        for (int i = 0; i < items.length; i++)
            SortUtil.swap(items, i, i + random.nextInt(items.length - i));
    }
    
    public static void threeWaySort(int[] items) {
        shuffle(items);
        threeWaySortInternal(items, 0, items.length - 1);
    }
    
    private static void threeWaySortInternal(int[] items, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        int v = items[lo];
        while (i <= gt) {
            if (items[i] < v) SortUtil.swap(items, i++, lt++);
            else if (items[i] > v) SortUtil.swap(items, i, gt--);
            else i++;
        }
        threeWaySortInternal(items, lo, lt - 1);
        threeWaySortInternal(items, gt + 1, hi);
    }
    
    public static void main(String[] args) {
        int[] items = SortUtil.randomInts(9999999);
        threeWaySort(items);
        System.out.println(SortUtil.isSorted(items));
    }

}
