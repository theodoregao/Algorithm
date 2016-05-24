package problems.ch1.section4;

import java.util.Arrays;

import collections.StdRandom;

public class _18_LocalMinimumOfArray {
    
    public static int localMinimumOfArray(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = (lo + hi) >>> 1;
        
        while (lo < hi) {
            if (mid > 1 && a[mid] > a[mid - 1]) hi = mid - 1;
            else if (mid < a.length - 1 && a[mid] > a[mid + 1]) lo = mid + 1;
            else return mid;
            mid = (lo + hi) >>> 1;
        }
        
        return mid;
    }
    
    public static void main(String[] args) {
//        StdRandom.setSeed("shun".hashCode());
        int[] a = StdRandom.sample(-200, 200, 100);
//        Arrays.sort(a);
//        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
//            int item = a[i];
//            a[i] = a[j];
//            a[j] = item;
//        }
//        for (int i = 0; i < a.length; i++) System.out.println(i + ": " + a[i]);
        System.out.println();
        int index = localMinimumOfArray(a);
        System.out.println(index + ": " + a[index]);
        utils.Arrays.visualize(a, index);
    }

}
