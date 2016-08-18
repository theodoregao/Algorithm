package practice.ch3.bst;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import collections.Bag;
import collections.impl.StdRandom;
import collections.impl.bag.LinkedBag;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class KdTree {
    
    private static final double POINT_SIZE = 0.01;
    
    static class TwodTree extends RedBlackTree<Point, Point> {
        
        @Override
        public Node put(Node node, Point key, Point value) {
            if (node == null) return new Node(key, value);
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                Node left = node.left;
                node.left = put(node.left, key, value);
                if (left == null) key.direction = !node.key.direction;
            }
            else if (cmp > 0) {
                Node right = node.right;
                node.right = put(node.right, key, value);
                if (right == null) key.direction = !node.key.direction;
            }
            else node.value = value;
            
//            node = node.rotateLeft();
//            node = node.rotateRight();
//            node.flipColors();
            
            node.reset();
            return node;
        }
        
        public Set<Point> intersects(Shape shape) {
            Set<Point> points = new HashSet<>();
            intersects(points, new Rectangle(new Point(0, 0), new Point(1, 1)), shape, root);
            return points;
        }
        
        private void intersects(Set<Point> points, Rectangle area, Shape target, Node node) {
            if (area == null || node == null || area == null || !area.intersects(target)) return;
            Point point = node.key;
            point.draw();
            if (target.contains(point)) {
                points.add(point);
                point.draw(Color.RED);
            }
            if (node.left != null) intersects(points, point.direction ? area.left(point.x) : area.down(point.y), target, node.left);
            if (node.right != null) intersects(points, point.direction ? area.right(point.x) : area.up(point.y), target, node.right);
        }
        
        private Point candidate;
        public Point closestPoint(Point target) {
            candidate = null;
            closestPoint(root, new Rectangle(new Point(0, 0), new Point(1, 1)), target);
            Point closestPoint = candidate;
            candidate = null;
            return closestPoint;
        }

        private void closestPoint(Node node, Rectangle area, Point target) {
            if (node == null || area == null || !area.intersects(new Circle(target, target.distance(candidate)))) return;
            Point point = node.key;
            point.draw();
//            StdIn.readLine();
            if (target.distance(point) < target.distance(candidate)) {
                candidate = point;
                new Circle(target, target.distance(candidate)).draw();
            }

            if (node.left != null) closestPoint(node.left, point.direction ? area.left(point.x) : area.down(point.y), target);
            if (node.right != null) closestPoint(node.right, point.direction ? area.right(point.x) : area.up(point.y), target);
        }
        
        public Point closestPointBruteForce(Point point) {
            candidate = null;
            closestPointBruteForce(root, point);
            Point closestPoint = candidate;
            candidate = null;
            return closestPoint;
        }
        
        private void closestPointBruteForce(Node node, Point target) {
            if (node == null) return;
            Point point = node.key;
//            point.draw();
//            StdIn.readLine();
            if (target.distance(point) < target.distance(candidate)) {
                candidate = point;
//                new Circle(target, target.distance(candidate)).draw();
            }
            
            closestPointBruteForce(node.left, target);
            closestPointBruteForce(node.right, target);
        }
        
        private void draw() {
            Rectangle rectangle = new Rectangle(new Point(0, 0), new Point(1, 1));
            draw(rectangle, root);
        }
        
        private void draw(Rectangle rectangle, Node node) {
            if (node == null || rectangle == null) return;
            Point point = node.key;
            if (point.direction) rectangle.drawVerticalLine(point.x);
            else rectangle.drawHorizontalLine(point.y);
            if (node.left != null) draw(point.direction ? rectangle.left(point.x) : rectangle.down(point.y), node.left);
            if (node.right != null) draw(point.direction ? rectangle.right(point.x) : rectangle.up(point.y), node.right);
        }
    }
    
    static class Point implements Comparable<Point> {

        private static final boolean HORIZONTAL = true;
        private static final boolean VERTICAL = false;
        
        double x, y;
        boolean direction;
        
        Point(double x, double y) {
            this.x = x;
            this.y = y;
            direction = HORIZONTAL;
        }
        
        double distance(Point point) {
            if (point == null) return Double.MAX_VALUE;
            return Math.sqrt((x - point.x) * (x - point.x) + (y - point.y) * (y - point.y));
        }
        
        void draw() {
            draw(Color.BLACK);
        }
        
        void draw(Color color) {
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius(POINT_SIZE);
            StdDraw.point(x, y);
        }

        @Override
        public int compareTo(Point point) {
            double delta = point.direction == HORIZONTAL ? x - point.x : y - point.y;
            return delta == 0 ? 0 : (delta < 0 ? - 1 : 1);
        }
        
        @Override
        public String toString() {
            return Point.class.getSimpleName() + "{" + "dir=" + direction + ", x=" + x + ", y=" + y + "}";
        }

        private static Bag<Point> randomPoints(int size) {
            Bag<Point> bag = new LinkedBag<>();
            for (int i = 0; i < size; i++) bag.add(randomPoint());
            return bag;
        }
        
        private static Point randomPoint() {
            return new Point(StdRandom.uniform(), StdRandom.uniform());
        }
    }
    
    static class Line {
        Point p, q;
        Line(Point p, Point q) {
            this.p = p;
            this.q = q;
        }
        
        void draw() {
            draw(Color.BLACK);
        }
        
        void draw(Color color) {
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius();
            StdDraw.line(p.x, p.y, q.x, q.y);
        }
    }
    
    static interface Shape {
        boolean contains(Point point);
        boolean intersects(Shape shape);
        void draw();
        void draw(Color color);
    }
    
    static class Rectangle implements Shape {
        Point p, q;
        Rectangle(Point p, Point q) {
            this.p = new Point(Math.min(p.x, q.x), Math.min(p.y, q.y));
            this.q = new Point(Math.max(p.x, q.x), Math.max(p.y, q.y));
        }
        
        @Override
        public boolean contains(Point point) {
            return point != null && p.x <= point.x && point.x <= q.x && p.y <= point.y && point.y <= q.y;
        }
        
        @Override
        public boolean intersects(Shape shape) {
            if (shape instanceof Rectangle) return intersects((Rectangle) shape);
            else if (shape instanceof Circle) return intersects((Circle) shape);
            return false;
        }
        
        boolean intersects(Circle circle) {
            return KdTree.intersect(this, circle);
        }
        
        boolean intersects(Rectangle rectangle) {
            return !(p.x > rectangle.q.x ||
                    q.x < rectangle.p.x ||
                    p.y > rectangle.q.y ||
                    q.y < rectangle.p.y);
        }
        
        Rectangle left(double x) {
            if (x < p.x) return null;
            if (x > q.x) return this;
            return new Rectangle(p, new Point(x, q.y));
        }
        
        Rectangle right(double x) {
            if (x < p.x) return this;
            if (x > q.x) return null;
            return new Rectangle(new Point(x, p.y), q);
        }
        
        Rectangle down(double y) {
            if (y < p.y) return null;
            if (y > q.y) return this;
            return new Rectangle(p, new Point(q.x, y));
        }
        
        Rectangle up(double y) {
            if (y < p.y) return this;
            if (y > q.y) return null;
            return new Rectangle(new Point(p.x, y), q);
        }
        
        Point center() {
            return new Point((p.x + q.x) / 2, (p.y + q.y) / 2);
        }
        
        double dx() {
            return q.x - p.x;
        }
        
        double dy() {
            return q.y - p.y;
        }
        
        @Override
        public void draw() {
            draw(Color.BLUE);
        }
        
        @Override
        public void draw(Color color) {
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius();
            StdDraw.rectangle((p.x + q.x) / 2, (p.y + q.y) / 2, (q.x - p.x) / 2, (q.y - p.y) / 2);
        }
        
        void drawVerticalLine(double x) {
            if (x < p.x || x > q.x) return;
            StdDraw.setPenColor();
            StdDraw.setPenRadius();
            new Line(new Point(x, p.y), new Point(x, q.y)).draw();;
        }
        
        void drawHorizontalLine(double y) {
            if (y < p.y || y > q.y) return;
            StdDraw.setPenColor();
            StdDraw.setPenRadius();
            new Line(new Point(p.x, y), new Point(q.x, y)).draw();;
        }
        
        Circle incircle() {
            double x = (p.x + q.x) / 2;
            double y = (p.y + q.y) / 2;
            double dx = (q.x - p.x) / 2;
            double dy = (q.y - p.y) / 2;
            return new Circle(new Point(x, y), Math.min(dx, dy));
        }
        
        static Rectangle randomRectangle() {
            return new Rectangle(new Point(StdRandom.uniform(), StdRandom.uniform()), new Point(StdRandom.uniform(), StdRandom.uniform()));
        }
    }
    
    static class Circle implements Shape {
        Point center;
        double radius;
        
        Circle(Point center, double radius) {
            this.center = center;
            this.radius = radius;
        }

        
        @Override
        public boolean contains(Point point) {
            return point != null && radius >= point.distance(center);
        }
        
        @Override
        public boolean intersects(Shape shape) {
            if (shape instanceof Rectangle) return intersects((Rectangle) shape);
            else if (shape instanceof Circle) return intersects((Circle) shape);
            return false;
        }
        
        boolean intersects(Rectangle rectangle) {
            return KdTree.intersect(rectangle, this);
        }
        
        boolean intersects(Circle circle) {
            if (circle == null) return false;
            return center.distance(circle.center) < radius + circle.radius;
        }
        
        @Override
        public void draw() {
            draw(Color.BLACK);
        }
        
        @Override
        public void draw(Color color) {
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius();
            StdDraw.circle(center.x, center.y, radius);
        }
        
        Rectangle outRectangle() {
            return new Rectangle(new Point(center.x - radius, center.y - radius), new Point(center.x + radius, center.y + radius));
        }
        
        static Circle randomCircle() {
            return Rectangle.randomRectangle().incircle();
        }
    }
    
    private static boolean intersect(Rectangle rectangle, Circle circle) {
        if (rectangle == null || circle == null) return false;
        double dx = Math.abs(circle.center.x - rectangle.center().x);
        double dy = Math.abs(circle.center.y - rectangle.center().y);
        
        if ((dx > rectangle.dx() / 2 + circle.radius) || (dy > rectangle.dy() / 2 + circle.radius)) return false;
        if (dx <= rectangle.dx() / 2 || dy <= rectangle.dy() / 2) return true;
        
        double distance = Math.pow((dx - rectangle.dx() / 2), 2) + Math.pow((dy - rectangle.dy() / 2), 2);
        return distance <= circle.radius * circle.radius;
    }
    
    public static void main(String[] args) {
        testClosestPoint();
    }
    
    private static void testTwodTreeDraw() {
        Bag<Point> points = Point.randomPoints(1000);
        TwodTree twodTree = new TwodTree();
        
        for (Point pt: points) {
            pt.draw(Color.GRAY);
            twodTree.put(pt, pt);
        }
        
        twodTree.draw();
    }
    
    private static void testCircleIntersectWithRectangle() {
        while (true) {
            Rectangle rectangle = Rectangle.randomRectangle();
            Circle circle = Circle.randomCircle();
            
            StdDraw.clear();
            if (circle.intersects(rectangle)) {
                circle.draw(Color.RED);
                rectangle.draw(Color.RED);
            }
            else {
                circle.draw();
                rectangle.draw();
            }
            
            StdIn.readLine();
        }
    }
    
    private static void testRectangleIntersectWithRectangle() {
        while (true) {
            Rectangle rectangle = Rectangle.randomRectangle();
            Rectangle rectangleOther = Rectangle.randomRectangle();
            
            StdDraw.clear();
            if (rectangleOther.intersects(rectangle)) {
                rectangleOther.draw(Color.RED);
                rectangle.draw(Color.RED);
            }
            else {
                rectangleOther.draw();
                rectangle.draw();
            }
            
            StdIn.readLine();
        }
    }
    
    private static void testClosestPoint() {
        Bag<Point> points = Point.randomPoints(1000);
        TwodTree twodTree = new TwodTree();
        
        Point point = Point.randomPoint();
        point.draw(Color.BLUE);
        
        for (Point pt: points) {
            pt.draw(Color.GRAY);
            twodTree.put(pt, pt);
        }
        twodTree.draw();
        
        twodTree.closestPointBruteForce(point).draw(Color.ORANGE);
        
        twodTree.closestPoint(point).draw(Color.RED);
    }
    
    private static void testCircleContainsPoint() {
        Bag<Point> points = Point.randomPoints(10000);
        TwodTree twodTree = new TwodTree();
        
        Circle circle = Circle.randomCircle();
        circle.draw();
        
        for (Point point: points) {
            point.draw(Color.GRAY);
            twodTree.put(point, point);
        }
        
//        for (Point point: twodTree.levelKeys()) System.out.println(point);
        
        for (Point point: twodTree.intersects(circle)) point.draw(Color.RED);
//        circle.draw(Color.RED);
    }
    
    private static void testRectancleContainsPoint() {
        Bag<Point> points = Point.randomPoints(10000);
        TwodTree twodTree = new TwodTree();
        
        Rectangle rectangle = Rectangle.randomRectangle();
        rectangle.draw();
        
        for (Point point: points) {
            point.draw(Color.GRAY);
            twodTree.put(point, point);
        }
        
//        for (Point point: twodTree.levelKeys()) System.out.println(point);
        
        for (Point point: twodTree.intersects(rectangle)) point.draw(Color.RED);
//        rectangle.draw(Color.RED);
    }

}
