package sort;

public class InsertionSort {
    
    public static void sort(Comparable[] comparables) {
        sort(comparables, 0, comparables.length - 1);
    }
    
    public static void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }
    
    static void sort(Comparable[] comparables, int lo, int hi) {
        int n = hi - lo + 1;
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && SortUtil.less(comparables[j], comparables[j - 1]); j--)
                SortUtil.swap(comparables, j, j - 1);
        }
    }
    
    static void sort(int[] nums, int lo, int hi) {
        int n = hi - lo + 1;
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && nums[j] < nums[j - 1]; j--)
                SortUtil.swap(nums, j, j - 1);
        }
    }
    
    public static void optimizeSort(Comparable[] comparables) {
        optimizedSort(comparables, 0, comparables.length - 1);
    }
    
    static void optimizedSort(Comparable[] comparables, int lo, int hi) {
        int n = comparables.length;
        
        for (int i = hi; i > lo; i--)
            if (SortUtil.less(comparables[i], comparables[i - 1]))
                SortUtil.swap(comparables, i, i - 1);
        
        Comparable temp;
        int i, j;
        for (i = lo + 1; i <= hi; i++) {
            temp = comparables[i];
            for (j = i; SortUtil.less(temp, comparables[j - 1]); j--)
                comparables[j] = comparables[j - 1];
            comparables[j] = temp;
        }
    }
}
