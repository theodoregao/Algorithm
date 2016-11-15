package sort;

import java.util.Random;

public class SelectionSort {

    public static void sort(int[] items) {
        for (int i = 0; i < items.length; i++) {
            int min = i;
            for (int j = i; j < items.length; j++)
                if (items[min] > items[j]) min = j;
            SortUtil.swap(items, i, min);
        }
    }
    
    public static void main(String[] args) {
        int[] items = SortUtil.randomInts(10000);
        sort(items);
        System.out.println(SortUtil.isSorted(items));
    }
    
}
