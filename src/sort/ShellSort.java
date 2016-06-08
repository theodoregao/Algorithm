package sort;

public class ShellSort {
    
    public static void sort(Comparable[] comparables) {
        int n = comparables.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && SortUtil.less(comparables[j], comparables[j - h]); j -= h)
                    SortUtil.swap(comparables, j, j - h);
            }
            h /= 3;
        }
    }
    
    public static void sort(int[] nums) {
        int n = nums.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h)
                    SortUtil.swap(nums, j, j - h);
            }
            h /= 3;
        }
    }

}
