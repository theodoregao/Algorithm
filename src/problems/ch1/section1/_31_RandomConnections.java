package problems.ch1.section1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.31 Random connections. Write a program that takes as command-line arguments
an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size
.05 on the circumference of a circle, and then, with probability p for each pair of points,
draws a gray line connecting them.
 * @author Theodore
 *
 */
public class _31_RandomConnections {

    public static void main(String[] args) {
        
        final int POINT_COUNT = 8;
        final double ROUND_X = .5;
        final double ROUND_Y = .5;
        final double RADIUS = .4;
        
        final double POSIBILITY = 0.8;
        
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.circle(ROUND_X, ROUND_Y, RADIUS);

        StdDraw.setPenRadius(0);
        StdDraw.setPenColor(StdDraw.RED);
        final double deltaRadian = 2 * Math.PI / POINT_COUNT;
        double radian = 0;
        double x, y;
        
        Point[] points = new Point[POINT_COUNT];
        for (int i = 0; i < POINT_COUNT; i++, radian += deltaRadian) {
            x = ROUND_X + RADIUS * Math.sin(radian);
            y = ROUND_Y + RADIUS * Math.cos(radian);
            points[i] = new Point(x, y);
            StdDraw.point(x, y);
        }
        
        for (int i = 0; i < POINT_COUNT; i++) {
            for (int j = i + 1; j < POINT_COUNT; j++) {
                if (StdRandom.bernoulli(POSIBILITY)) {
                    Point p1 = points[i];
                    Point p2 = points[j];
                    StdDraw.line(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
    
    private static class Point {
        double x, y;
        
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
