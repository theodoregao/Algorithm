package problems.ch2.section4;

import java.awt.Color;
import java.util.Comparator;

import collections.impl.StdRandom;
import collections.impl.heap.Heap;
import edu.princeton.cs.algs4.StdDraw;

public class _28_SelectionFilter {
    
    public static void main(String[] args) {
        Heap<Point> heap = new Heap<Point>(new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                Double dis1 = o1.x * o1.x + o1.y * o1.y;
                Double dis2 = o2.x * o2.x + o2.y * o2.y;
                return dis2.compareTo(dis1);
            }

        });
        
        for (int i = 0; i < 100; i++) heap.insert(new Point(StdRandom.uniform(), StdRandom.uniform()));

        heap.deleteTop().draw(Color.RED);
        for (Point point : heap) point.draw();
    }

    static class Point {
        double x, y;
        
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
        
        public void draw() {
            draw(new Color(0));
        }
        
        public void draw(Color color) {
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(x, y);
            StdDraw.setPenRadius();
            StdDraw.text(x, y + 0.02, String.format("%.3f", Math.sqrt(x * x + y * y)));
        }
    }
}
