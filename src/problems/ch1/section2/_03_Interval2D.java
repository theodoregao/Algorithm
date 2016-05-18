package problems.ch1.section2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.3 Write an Interval2D client that takes command-line arguments N, min, and max
and generates N random 2D intervals whose width and height are uniformly distributed
between min and max in the unit square. Draw them on StdDraw and print the number
of pairs of intervals that intersect and the number of intervals that are contained in one
another.
 * @author Theodore
 *
 */
public class _03_Interval2D {
    
    public static void main(String[] args) {
        final int n = 4000;
        
        Interval2D[] interval2ds = new Interval2D[n];
        for (int i = 0; i < n; i++) {
            Interval2D interval2d = new Interval2D();
            interval2ds[i] = interval2d;
            
            Point gravityCenter = interval2d.getGravityCenter();
            StdDraw.rectangle(gravityCenter.x, gravityCenter.y, interval2d.dx() / 2, interval2d.dy() / 2);
        }

        int intersectionCount = 0;
        int containCount = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                if (interval2ds[i].intersect(interval2ds[j])) intersectionCount++;
                if (interval2ds[i].contains(interval2ds[j])) containCount++;
            }
        
        System.out.println(intersectionCount);
        System.out.println(containCount);
    }
    
    private static class Interval2D {
        Point pt1, pt2;
        
        public Interval2D() {
            this(StdRandom.uniform(),
                    StdRandom.uniform(),
                    StdRandom.uniform(),
                    StdRandom.uniform());
        }
        
        public Interval2D(double x1, double y1, double x2, double y2) {
            this(new Point(x1, y1), new Point(x2, y2));
        }
        
        Interval2D(Point pt1, Point pt2) {
            this.pt1 = new Point(Math.min(pt1.x, pt2.x), Math.min(pt1.y,  pt2.y));
            this.pt2 = new Point(Math.max(pt1.x, pt2.x), Math.max(pt1.y,  pt2.y));
        }
        
        boolean intersect(Interval2D other) {
            Point gravityCenter1 = getGravityCenter();
            Point gravityCenter2 = other.getGravityCenter();
            
            return gravityCenter1.dx(gravityCenter2) < (dx() + other.dx()) / 2
                    && gravityCenter1.dy(gravityCenter2) < (dy() + other.dy()) / 2;
        }
        
        boolean contains(Interval2D other) {
            if (pt1.x <= other.pt1.x) return pt1.y <= other.pt1.y && pt2.x >= other.pt2.x && pt2.y >= other.pt2.y;
            else return pt1.y >= other.pt1.y && pt2.x <= other.pt2.x && pt2.y <= other.pt2.y;
            
        }
        
        Point getGravityCenter() {
            return new Point((pt1.x + pt2.x) / 2, (pt1.y + pt2.y) / 2);
        }
        
        double dx() {
            return pt1.dx(pt2);
        }
        
        double dy() {
            return pt1.dy(pt2);
        }
    }
    
    private static class Point {
        double x, y;
        
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        double dx(Point other) {
            return Math.abs(x - other.x);
        }
        
        double dy(Point other) {
            return Math.abs(y - other.y);
        }
    }

}
