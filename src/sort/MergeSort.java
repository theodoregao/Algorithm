package sort;

public class MergeSort {
    
    private static Comparable[] aux;
    
    private static void merge(Comparable[] comparables, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        
        for (int k = lo; k <= hi; k++) {
            if (i > mid) comparables[k] = aux[j++];
            else if (j > hi) comparables[k] = aux[i++];
            else if (SortUtil.less(aux[j], aux[i])) comparables[k] = aux[j++];
            else comparables[k] = aux[i++];
        }
    }
    
    private static void merge(Comparable[] comparables, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = comparables[k];
        for (int k = lo; k <= hi; k++) {
            if (i > mid) comparables[k] = aux[j++];
            else if (j > hi) comparables[k] = aux[i++];
            else if (SortUtil.less(aux[j], aux[i])) comparables[k] = aux[j++];
            else comparables[k] = aux[i++];
        }
    }
    
    private static void sort(Comparable[] comparables, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        
//        if (hi - lo < 7) {
//            InsertionSort.optimizedSort(comparables, lo, hi);
//            return;
//        }
        
        int mid = (lo + hi) >>> 1;
        sort(aux, comparables, lo, mid);
        sort(aux, comparables, mid + 1, hi);
//        if (SortUtil.less(aux[mid - 1], aux[mid]))
//            System.arraycopy(aux, lo, comparables, lo, hi - lo + 1);
        
        merge(comparables, aux, lo, mid, hi);
    }
    
    public static void sort(Comparable[] comparables) {
        Comparable[] aux = new Comparable[comparables.length];
        for (int i = 0; i < comparables.length; i++) aux[i] = comparables[i];
        sort(comparables, aux, 0, comparables.length - 1);
    }
    
    public static void bottomUpSort(Comparable[] comparables) {
        int n = comparables.length;
        aux = new Comparable[comparables.length];
        for (int sz = 1; sz < n; sz *= 2)
            for (int lo = 0; lo < n - sz; lo += sz + sz)
                merge(comparables, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
        aux = null;
    }

}
