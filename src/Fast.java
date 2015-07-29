import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Fast {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        HashSet<String> set = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            Point tmp = new Point(in.readInt(), in.readInt());
            points[i] = tmp;
            if (!set.contains(tmp.toString())) {
                tmp.draw();
                set.add(tmp.toString());
            }
        }
        HashMap<Double, HashSet<String>> map = new HashMap<>();
        Arrays.sort(points);
        for (int i = 0; i < n; i++) {
            Point p = points[i];
            Point[] otherPoint = new Point[n - 1 - i];
            System.arraycopy(points, i+1, otherPoint, 0, n - i - 1);
            Arrays.sort(otherPoint, p.SLOPE_ORDER);
            int low = 0, high = 0;
            while (high < n - i - 1) {
                while (high < n - i - 1 && p.SLOPE_ORDER.compare(otherPoint[high], otherPoint[low]) == 0) {
                    high++;
                }
                if (high - low > 2) {
                    myDraw(otherPoint, p, low, high, map);
                }
                low = high;
            }
        }
    }
    
    private static void myDraw(Point[] points, Point p, int lo, int hi, HashMap<Double,HashSet<String>> map) {
        Point[] line = new Point[hi - lo + 1];
        line[0] = p;
        for (int i = lo, j = 1; i < hi; i++, j++) {
            line[j] = points[i];
        }
        Arrays.sort(line);
        Double slope = line[0].slopeTo(line[hi - lo]);
        if (map.get(slope) != null && map.get(slope).contains(line[hi - lo].toString())) {
            return;
        }
        line[0].drawTo(line[hi - lo]);
        System.out.print(line[0]);
        for (int i = 1; i < line.length; i++) {
            System.out.print(" -> ");
            System.out.print(line[i]);
        }
        System.out.println();
        HashSet<String> set = map.get(slope);
        if (set == null) {
            set = new HashSet<String>();
        }
        set.add(line[hi - lo].toString());
        map.put(slope, set);
    }
}