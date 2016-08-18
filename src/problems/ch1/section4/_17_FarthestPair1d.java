package problems.ch1.section4;

import java.util.Arrays;

import collections.impl.StdRandom;

public class _17_FarthestPair1d {
    
    public static double farthestPair(double[] a) {
        Arrays.sort(a);
//        for (int i = 0; i < a.length; i++) System.out.println(a[i]);
//        System.out.println();
        return a[a.length - 1] - a[0];
    }
    
    public static void main(String[] args) {
        StdRandom.setSeed("shun".hashCode());
        double[] a = StdRandom.sample(-10.0, 1, 20);
//        for (int i = 0; i < a.length; i++) System.out.println(a[i]);
//        System.out.println();
        
        System.out.println(farthestPair(a));
    }

}
