public class Brute {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01); // make the points a bit larger

        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();

        Point[] points = new Point[n];

        int x, y;

        for (int i = 0; i < n; i++) {
            x = in.readInt();
            y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        
        Selection.sort(points);

        double pq, pr, ps;
        Point[] segment = new Point[4];

        for (int p = 0; p < n; p++) {
            segment[0] = points[p];
            for (int q = p + 1; q < n; q++) {
                segment[1] = points[q];
                pq = points[p].slopeTo(points[q]);
                if (pq == Double.NEGATIVE_INFINITY)
                    continue;
                for (int r = q + 1; r < n; r++) {
                    segment[2] = points[r];
                    pr = points[p].slopeTo(points[r]);
                    if (pq != pr)
                        continue;
                    for (int s = r + 1; s < n; s++) {
                        ps = points[p].slopeTo(points[s]);
                        if (pq == ps) {
                            segment[3] = points[s];
                            Selection.sort(segment);
                            System.out.println(segment[0].toString() + " -> "
                                    + segment[1].toString() + " -> "
                                    + segment[2].toString() + " -> "
                                    + segment[3].toString());
                            segment[0].drawTo(segment[3]);
                        }
                    }
                }
            }
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}