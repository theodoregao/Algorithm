package problems.ch1.section2;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.1 Write a Point2D client that takes an integer value N from the command line,
generates N random points in the unit square, and computes the distance separating
the closest pair of points.
 * @author shun
 *
 */
public class _01_Point2D {
    
    public static void main(String[] args) {
        final int n = 3;
        double[] points = new double[n];
        
        for (int i = 0; i < n; i++) points[i] = StdRandom.uniform();
        
        double minDistance = Double.POSITIVE_INFINITY;
        Arrays.sort(points);
        for (int i = 0; i < n - 1; i++) {
            if (minDistance > points[i + 1] - points[i]) minDistance = points[i + 1] - points[i];
        }
        
        System.out.println(minDistance);
    }

}
