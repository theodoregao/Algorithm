package practice.ch3.bst;

import java.awt.Color;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import collections.Heap;
import collections.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

public class OrthogonalLineSegmentItersection {
    
    public static void main(String[] args) {
        Heap<Line> priorityQueue = new Heap<>(new Comparator<Line>() {

            @Override
            public int compare(Line line, Line other) {
                double delta = other.getX() - line.getX();
                return delta == 0 ? 0 : (delta < 0 ? -1 : 1);
            }
        });
        
        RedBlackTree<Double, Line> rbt = new RedBlackTree<>();
        
        Set<Line> lines = new HashSet<>();
        Set<Point> points = new HashSet<>();
        
        for (Line line: generateLines(100)) priorityQueue.insert(line);
        
        for (Line line: priorityQueue) line.draw();
        
        while (!priorityQueue.isEmpty()) {
            Line line = priorityQueue.deleteTop();
            if (line.isHorizontal && !line.isVisited) {
                rbt.put(line.p.y, line);
                line.visit();
                priorityQueue.insert(line);
            }
            else if (line.isHorizontal) {
                rbt.delete(line.p.y);
            }
            else for (Double y: rbt.keys(line.p.y, line.q.y)) {
                lines.add(line);
                lines.add(rbt.get(y));
                points.add(new Point(line.p.x, y));
            }
        }
        
//        for (Line line: lines) line.draw(Color.RED);
        for (Point point: points) point.draw();
    }
    
    private static Set<Line> generateLines(int size) {
        Set<Line> lines = new HashSet<>();
        while (lines.size() < size) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            double delta = StdRandom.uniform() * (StdRandom.bernoulli() ? 1 : -1);
            if (StdRandom.bernoulli()) lines.add(new Line(new Point(x, y), new Point(x, Math.max(0, Math.min(y + delta, 1.0)))));
            else lines.add(new Line(new Point(x, y), new Point(Math.max(0, Math.min(x + delta, 1.0)), y)));
        }
        return lines;
    }
    
    static class Line {
        Point p, q;
        boolean isHorizontal;
        boolean isVisited;
        
        Line(Point p, Point q) {
            if (p.x < q.x || p.y < q.y) {
                this.p = p;
                this.q = q;
            }
            else {
                this.p = q;
                this.q = p;
            }
            isHorizontal = p.x != q.x;
            isVisited = false;
        }
        
        void visit() {
            isVisited = true;
        }
        
        double getX() {
            return isVisited ? q.x : p.x;
        }
        
        void draw() {
            draw(Color.black);
        }
        
        void draw(Color color) {
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius();
            StdDraw.line(p.x, p.y, q.x, q.y);
        }
        
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Line && ((Line) obj).p == p && ((Line) obj).q == q;
        }
    }
    
    static class Point {
        double x, y;
        
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public void draw() {
            draw(Color.RED);
        }
        
        public void draw(Color color) {
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(x, y);
        }
        
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Point && equals((Point) obj);
        }
        
        public boolean equals(Point other) {
            return other != null && x == other.x  && y == other.y;
        }
    }

}
