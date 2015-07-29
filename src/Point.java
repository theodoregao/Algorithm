import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        
        SLOPE_ORDER = new Comparator<Point>() {
            
            @Override
            public int compare(Point p1, Point p2) {
                Double slope1 = new Double(slopeTo(p1));
                Double slope2 = new Double(slopeTo(p2));
                return (int)(slope1.compareTo(slope2));
            }
        };
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that == null) throw new NullPointerException();
        if (x == that.x && y == that.y) return Double.NEGATIVE_INFINITY;
        if (x == that.x) return Double.POSITIVE_INFINITY;
        if (y == that.y) return +0;
        return 1.0 * (that.y - y) / (that.x - x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that == null) throw new NullPointerException();
        if (y == that.y) return x - that.x;
        else return y - that.y;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        
    }
}
