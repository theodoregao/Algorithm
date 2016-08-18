package problems.ch1.section4;

import java.util.Arrays;

import collections.impl.StdRandom;

public class _08_EqualPairs {
    
    public static void main(String[] args) {
        final int seed = "shun".hashCode();
        final int n = 10000000;
        
        int[] inputs = new int[n];
        StdRandom.setSeed(seed);
        for (int i = 0; i < n; i++) inputs[i] = StdRandom.uniform(n);

        Arrays.sort(inputs);
        
        int count = 0;
        int previous = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++)
            if (previous != inputs[i] && inputs[i] == inputs[i - 1]) {
                previous = inputs[i];
                count++;
            }
        
//        for (int i = 0; i < n; i++) System.out.println(inputs[i]);
//        System.out.println();
        System.out.println(count);
    }

}
