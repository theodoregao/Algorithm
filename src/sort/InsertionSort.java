package sort;

import java.util.Random;

public class InsertionSort {
    
    public static void sort(int[] items) {
        for (int i = 0; i < items.length; i++)
            for (int j = i; j >= 1 && items[j - 1] > items[j]; j--)
                SortUtil.swap(items, j - 1, j);
    }
    
    public static void main(String[] args) {
        int size = 10000;
        int[] items = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) items[i] = random.nextInt();
        sort(items);
        System.out.println(SortUtil.isSorted(items));
    }

}
