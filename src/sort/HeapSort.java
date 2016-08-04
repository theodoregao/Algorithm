package sort;

import java.util.Random;

public class HeapSort {
    
    private static int[] items;
    
    public static void sort(int[] items) {
        HeapSort.items = items;
        int n = items.length;
        for (int k = n / 2; k >= 1; k--) sink(k, n);
        while (n > 1) {
            swap(1, n--);
            sink(1, n);
        }
        HeapSort.items = null;
    }
    
    private static void sink(int k, int n) {
        while (k * 2 <= n) {
            int j = k * 2;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }
    
    private static boolean less(int i, int j) {
        return items[i - 1] < items[j - 1];
    }
    
    private static void swap(int i, int j) {
        int temp = items[i - 1];
        items[i - 1] = items[j - 1];
        items[j - 1] = temp;
    }
    
    public static void main(String[] args) {
        int size = 9999999;
        int[] items = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) items[i] = random.nextInt();
        sort(items);
        System.out.println(SortUtil.isSorted(items));
    }

}
