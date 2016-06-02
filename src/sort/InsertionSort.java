package sort;

public class InsertionSort {
    
    public static void sort(Comparable[] comparables) {
        int n = comparables.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1 && SortUtil.less(comparables[j], comparables[j - 1]); j--)
                SortUtil.swap(comparables, j, j - 1);
        }
    }
    
    public static void optimizedSort(Comparable[] comparables) {
        int n = comparables.length;
        
        for (int i = n - 1; i > 0; i--)
            if (SortUtil.less(comparables[i], comparables[i - 1]))
                SortUtil.swap(comparables, i, i - 1);
        
        Comparable temp;
        int i, j;
        for (i = 1; i < n; i++) {
            temp = comparables[i];
            for (j = i; SortUtil.less(temp, comparables[j - 1]); j--)
                comparables[j] = comparables[j - 1];
            comparables[j] = temp;
        }
    }
}
