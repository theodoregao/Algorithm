package sort;

import java.util.Random;

public class ShellSort {
    
    public static void sort(int[] items) {
        int h = 1; while (h * 3 < items.length) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = 0; i < items.length; i++)
                for (int j = i; j >= h && items[j - h] > items[j]; j -= h)
                    SortUtil.swap(items, j - h, j);
            h /= 3;
        }
    }
    
    public static void main(String[] args) {
        int size = 10000000;
        int[] items = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) items[i] = random.nextInt();
        sort(items);
        System.out.println(SortUtil.isSorted(items));
    }

}
