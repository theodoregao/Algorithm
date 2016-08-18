package problems.ch1.section4;

import java.util.Arrays;

import collections.impl.StdRandom;

public class _12_CompareTwoArrays {
    
    static void compareTwoArrays(int[] a, int[] b) {
        int value = Integer.MIN_VALUE;
        for (int i = 0, j = 0; i < a.length && j < b.length; ) {
            if (a[i] == b[j] && a[i] != value) {
                System.out.print(a[i] + " ");
                value = a[i];
                i++;
                j++;
            }
            else if (a[i] > b[j]) j++;
            else i++;
        }
    }
    
    public static void main(String[] args) {
        final int size = 100;
        
        int[] a = new int[size];
        int[] b = new int[size];
        
        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniform(size);
            b[i] = StdRandom.uniform(size);
        }
        
        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = 0; i < size; i++) System.out.print(a[i] + " ");
        System.out.println();
        for (int i = 0; i < size; i++) System.out.print(b[i] + " ");
        System.out.println();
        
        compareTwoArrays(a, b);
    }

}
