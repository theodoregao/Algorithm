package sort.simplify;

class HeapSort {
    
    private int[] items;
    
    public HeapSort(int[] items) {
        this.items = items;
    }
    
    public void sort() {
        int n = items.length;
        for (int i = n / 2; i >= 1; i--) sink(i, n);
        while (n > 1) {
            swap(items, 1, n--);
            sink(1, n);
        }
    }
    
    private void sink(int k, int n) {
        while (k * 2 <= n) {
            int j = k * 2;
            if (j < n && less(items, j, j + 1)) j++;
            if (!less(items, k, j)) break;
            swap(items, k, j);
            k = j;
        }
    }
    
    private static boolean less(int[] items, int i, int j) {
        return items[i - 1] < items[j - 1];
    }
    
    private static void swap(int[] items, int i, int j) {
        int temp = items[i - 1];
        items[i - 1] = items[j - 1];
        items[j - 1] = temp;
    }

}
