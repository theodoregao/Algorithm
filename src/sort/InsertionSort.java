package sort;

public class InsertionSort {
    
    public static void sort(int[] items) {
        for (int i = 0; i < items.length; i++)
            for (int j = i; j >= 1 && items[j - 1] > items[j]; j--)
                SortUtil.swap(items, j - 1, j);
    }
    
    public static void main(String[] args) {
        int[] items = SortUtil.randomInts(10000);
        sort(items);
        System.out.println(SortUtil.isSorted(items));
    }

}
