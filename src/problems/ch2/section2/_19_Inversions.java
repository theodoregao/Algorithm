package problems.ch2.section2;

import collections.impl.StdRandom;

public class _19_Inversions {
    
    long count;
    
    public static void main(String[] args) {
        int size = 100000;
        new _19_Inversions().sort(StdRandom.sample(0, 10 * size, size));
    }
    
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
        System.out.println(count);
    }
    
    void sort(int[] nums, int lo, int hi) {
        int n = hi - lo + 1;
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && nums[j] < nums[j - 1]; j--)
                swap(nums, j, j - 1);
        }
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        count++;
    }
}
