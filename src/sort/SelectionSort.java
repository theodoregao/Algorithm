package sort;

public class SelectionSort {
    
    public static void sort(Comparable[] comparables) {
        int n = comparables.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (SortUtil.less(comparables[j], comparables[min])) min = j;
            SortUtil.swap(comparables, i, min);
        }
    }

}
