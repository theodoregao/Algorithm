package problems.ch1.section4;

import java.util.Arrays;

import collections.impl.StdRandom;

public class _10_BinarySearchMinimalIndex {
    
    static int binarySearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = (lo + hi) / 2;
        
        while (lo <= hi) {
            if (key <= a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            mid = (lo + hi) / 2;
        }
        
        if (lo >= 0 && lo < a.length && a[lo] == key) return lo;
        else return -lo - 1;
    }
    
    public static void main(String[] args) {

        final int seed = "shun".hashCode();
        final int n = 10;
        
        int[] inputs = new int[n];
        StdRandom.setSeed(seed);
        for (int i = 0; i < n; i++) inputs[i] = StdRandom.uniform(n);
        Arrays.sort(inputs);
        for (int i = 0; i < n; i++) System.out.println(inputs[i]);
        
        System.out.println();
        for (int i = 0; i < n; i++)
            System.out.println(i + ": " + binarySearch(inputs, i));
    }

}
