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
            swap(1, n--);
            sink(1, n);
        }
    }
    
    private void sink(int k, int n) {
        while (k * 2 <= n) {
            int j = k * 2;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }
    
    private boolean less(int i, int j) {
        return items[i - 1] < items[j - 1];
    }
    
    private void swap(int i, int j) {
        int temp = items[i - 1];
        items[i - 1] = items[j - 1];
        items[j - 1] = temp;
    }

}
