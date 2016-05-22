package problems.ch1.section4;

import java.util.Arrays;

import collections.StdRandom;

public class _16_ClosestPair1d {
    
    public static double closestPair(double[] a) {
        double distance = Double.POSITIVE_INFINITY;
        Arrays.sort(a);
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i + 1] - a[i] < distance) {
                distance = a[i + 1] - a[i];
            }
        }
        
        return distance;
    }
    
    public static void main(String[] args) {
        StdRandom.setSeed("shun".hashCode());
        double[] a = StdRandom.sample(-10.0, 1, 20);
//        for (int i = 0; i < a.length; i++) System.out.println(a[i]);
        
        System.out.println();
        System.out.println(closestPair(a));
    }

}
