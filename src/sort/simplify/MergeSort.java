package sort.simplify;

class MergeSort {
    
    private static int[] aux;
    
    public static void sort(int[] items) {
        // 2 steps: aux, sort internal
        aux = new int[items.length];
        sortInternal(items, 0, items.length - 1);
        aux = null;
    }
    
    private static void merge(int[] items, int lo, int mid, int hi) {
        // 3 steps: i, j, aux, merge
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = items[k];
        for (int k = lo; k <= hi; k++) {
            // 4 branches
            if (i > mid) items[k] = aux[j++];
            else if (j > hi) items[k] = aux[i++];
            else if (aux[j] < aux[i]) items[k] = aux[j++];
            else items[k] = aux[i++];
        }
    }

    private static void sortInternal(int[] items, int lo, int hi) {
        // 3 steps: return, mid, sub sort & merge
        if (hi <= lo) return;
        int mid = (lo + hi) >>> 1;
        sortInternal(items, lo, mid);
        sortInternal(items, mid + 1, hi);
        merge(items, lo, mid, hi);
    }
    
    public static void bottomUpSort(int[] items) {
        int n = items.length;
        aux = new int[items.length];
        for (int sz = 1; sz < n; sz *= 2)
            for (int lo = 0; lo < n - sz; lo += sz + sz)
                merge(items, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
        aux = null;
    }
}
