package sort;

public class SortUtil {
    
    public static void swap(int[] items, int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
    
    public static boolean isSorted(int[] items) {
        for (int i = 1; i < items.length; i++)
            if (items[i - 1] > items[i]) return false;
        return true;
    }

}
