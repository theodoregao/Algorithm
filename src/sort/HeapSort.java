package sort;

import java.util.Comparator;

public class HeapSort<Item> {
    private Comparator<Item> comparator;
    private Item[] items;
    
    public void sort(Item[] items, Comparator<Item> comparator) {
        this.items = items;
        this.comparator = comparator;
        sort();
    }
    
    private void sort() {
        int n = items.length;
        for (int i = n / 2; i >= 1; i--) sink(i, n);
        while (n > 1) {
            swap(1, n--);
            sink(1, n);
        }
    }
    
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k /= 2;
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
        return comparator.compare(items[i - 1], items[j - 1]) < 0;
    }
    
    private void swap(int i, int j) {
        Item item = items[i - 1];
        items[i - 1] = items[j - 1];
        items[j - 1] = item;
    }
    
}