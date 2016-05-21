package problems.ch1.section4;

import java.util.Arrays;

import collections.StdRandom;

public class _11_CountEquals {
    
    static int binarySearchLeft(int[] a, int key) {
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
    
    static int binarySearchRight(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = (lo + hi + 1) / 2;
        
        while (lo <= hi) {
            if (key < a[mid]) hi = mid - 1;
            else if (key >= a[mid]) lo = mid + 1;
            mid = (lo + hi + 1) / 2;
        }
        
        if (hi >= 0 && hi < a.length && a[hi] == key) return hi;
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
        for (int i = -1; i <= n; i++) {
            System.out.println(i +
                    " " + binarySearchLeft(inputs, i) +
                    " " + Arrays.binarySearch(inputs, i) +
                    " " + binarySearchRight(inputs, i));
        }
        
    }

}
