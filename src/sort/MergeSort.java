package sort;

public class MergeSort {
    
    private static int[] aux;
    
    public static void sort(int[] items) {
        aux = new int[items.length];
        sortInternal(items, 0, items.length - 1);
        aux = null;
    }
    
    private static void sortInternal(int[] items, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >>> 1;
        sortInternal(items, lo, mid);
        sortInternal(items, mid + 1, hi);
        merge(items, lo, mid, hi);
    }
    
    private static void merge(int[] items, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = items[k];
        for (int k = lo; k <= hi; k++)
            if (i > mid) items[k] = aux[j++];
            else if (j > hi) items[k] = aux[i++];
            else if (aux[j] < aux[i]) items[k] = aux[j++];
            else items[k] = aux[i++];
    }
    
    public static void bottomUpSort(int[] items) {
        aux = new int[items.length];
        for (int size = 1; size < items.length; size += size)
            for (int i = 0; i < items.length - size; i += size + size)
                merge(items, i, i + size - 1, Math.min(i + size + size - 1, items.length - 1));
        aux = null;
    }
    
    public static void main(String[] args) {
        int[] items = SortUtil.randomInts(9999999);
        sort(items);
        System.out.println(SortUtil.isSorted(items));
    }

}
