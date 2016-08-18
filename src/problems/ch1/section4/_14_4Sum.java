package problems.ch1.section4;

import java.util.Arrays;

import algorithms.BinarySearch;
import collections.impl.StdRandom;

public class _14_4Sum {
    
    static void fourSum(int[] a, int sum) {
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + " ");
        System.out.println();
        
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    int x = sum - a[i] - a[j] - a[k];
                    if (x > a[k] && BinarySearch.search(a, x, k + 1, a.length - 1) > 0) {
                        System.out.println(String.format("%d, %d, %d, %d", a[i], a[j], a[k], x));
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        fourSum(StdRandom.sample(-100, 100, 20), 100);
    }

}
