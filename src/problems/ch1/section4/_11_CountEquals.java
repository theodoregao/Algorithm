package problems.ch1.section4;

import java.util.Arrays;

import algorithms.BinarySearch;
import collections.impl.StdRandom;

public class _11_CountEquals {
    
    public static void main(String[] args) {

        final int seed = "shun".hashCode();
        final int n = 100;
        
        int[] inputs = new int[n];
        StdRandom.setSeed(seed);
        for (int i = 0; i < n; i++) inputs[i] = StdRandom.uniform(n);
        Arrays.sort(inputs);
        for (int i = 0; i < n; i++) System.out.println(inputs[i]);
        
        System.out.println();
        for (int i = -1; i <= n; i++) {
//            System.out.println(i +
//                    " " + binarySearchLeft(inputs, i) +
////                    " " + Arrays.binarySearch(inputs, i) +
//                    " " + binarySearchRight(inputs, i));
            
            int left = BinarySearch.left(inputs, i);
            int right = BinarySearch.right(inputs, i);
            if (left != right) System.out.println(i + ": " + (right - left + 1));
        }
        
    }

}
