package sort.simplify;

import java.util.Random;

class QuickSort {
    
    public static void sort(int[] items) {
        // 2 steps: shuffle & sort internal
        shuffle(items);
        sortInternal(items, 0, items.length - 1);
    }
    
    private static void shuffle(int[] items) {
        Random random = new Random();
        int n = items.length;
        for (int i = 0; i < n; i++)
            SortUtil.swap(items, i, i + random.nextInt(n - i));
    }
    
    private static void sortInternal(int[] items, int lo, int hi) {
        // 3 steps: return, partition, sort left & right
        if (lo >= hi) return;
        int j = partition(items, lo, hi);
        sortInternal(items, lo, j - 1);
        sortInternal(items, j + 1, hi);
    }
    
    private static int partition(int[] items, int lo, int hi) {
        // 4 steps: i j, find i j, swap i j, swap lo j
        int i = lo, j = hi + 1;
        while (true) {
            while (items[++i] < items[lo]) if (i == hi) break;
            while (items[lo] < items[--j]) /*if (j == lo) break*/;
            if (i >= j) break;
            SortUtil.swap(items, i, j);
        }
        SortUtil.swap(items, lo, j);
        return j;
    }
    
    public static void threeWaySort(int[] items) {
        // 2 stemps: shuffle & three way sort internal
        shuffle(items);
        threeWaySortInternal(items, 0, items.length - 1);
    }
    
    private static void threeWaySortInternal(int items[], int lo, int hi) {
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

}
