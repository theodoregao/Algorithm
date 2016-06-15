package problems.ch2.section5;

import java.util.Comparator;

import collections.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

public class _26_SimplePolygon {
    
    static class Point2D {
        double x, y;
        
        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public void draw() {
            StdDraw.text(x, y + 0.01, String.format("(%.3f, %.3f)", x, y));
        }
        
        public void draw(int pos) {
            StdDraw.text(x, y + 0.01, String.format("%d (%.3f, %.3f)", pos, x, y));
        }
        
        @Override
        public String toString() {
            return String.format("%s {%.2f, %.2f}", Point2D.class.getSimpleName(), x, y);
        }
        
        public Comparator<Point2D> getComparator() {
            return comparator;
        }
        
        private Comparator<Point2D> comparator = new Comparator<_26_SimplePolygon.Point2D>() {
            
            @Override
            public int compare(Point2D pt1, Point2D pt2) {
                double cmp = cosAlpha(pt1) - cosAlpha(pt2);
                if (cmp == 0) cmp = distance(pt1) - distance(pt2);
                if (cmp < 0) return -1;
                else if (cmp > 0) return 1;
                else return 0;
            }
            
            private double cosAlpha(Point2D pt) {
                if (pt.x == x && pt.y == y) return Double.NEGATIVE_INFINITY;
                double dx = pt.x - x;
                double dy = pt.y - y;
                return -dx / Math.sqrt(dx * dx + dy * dy);
            }
            
            private double distance(Point2D pt) {
                return (pt.x - x) * (pt.x - x) + (pt.y - y) * (pt.y - y);
            }
        };
        
        public static Comparator<Point2D> POINT_COMPARATOR = new Comparator<_26_SimplePolygon.Point2D>() {
            
            @Override
            public int compare(Point2D o1, Point2D o2) {
                double cmp = o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y;
                if (cmp < 0) return -1;
                else if (cmp > 0) return 1;
                else return 0;
            }
        };
    }
    
    public static void main(String[] args) {
        int n = 10;
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) points[i] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
        
        HeapSort<Point2D> heapSort = new HeapSort<_26_SimplePolygon.Point2D>();
        heapSort.sort(points, Point2D.POINT_COMPARATOR);
        for (int i = 0; i < n; i++) System.out.println(points[i]);
        
        System.out.println();
        heapSort.sort(points, points[0].getComparator());
        for (int i = 0; i < n; i++) {
            System.out.println(points[i]);
            Point2D pt1 = points[i % n];
            Point2D pt2 = points[(i + 1) % n];
            pt1.draw(i);
            StdDraw.line(pt1.x, pt1.y, pt2.x, pt2.y);
        }
    }
    
    static class HeapSort<Item> {
        
        private Comparator<Item> comparator;
        private Item[] items;
        
        public void sort(Item[] items, Comparator<Item> comparator) {
            this.items = items;
            this.comparator = comparator;
            sort();
        }
        
        private void sort() {
            int n = items.length;
            for (int i = n / 2; i >= 1; i--) sink(i, n);
            while (n > 1) {
                swap(1, n--);
                sink(1, n);
            }
        }
        
        private void swim(int k) {
            while (k > 1 && less(k / 2, k)) {
                swap(k, k / 2);
                k /= 2;
            }
        }
        
        private void sink(int k, int n) {
            while (k * 2 <= n) {
                int j = k * 2;
                if (j < n && less(j, j + 1)) j++;
                if (!less(k, j)) break;
                swap(k, j);
                k = j;
            }
        }
        
        private boolean less(int i, int j) {
            return comparator.compare(items[i - 1], items[j - 1]) < 0;
        }
        
        private void swap(int i, int j) {
            Item item = items[i - 1];
            items[i - 1] = items[j - 1];
            items[j - 1] = item;
        }
        
    }

}
