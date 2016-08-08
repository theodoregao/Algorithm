package practice.ch3.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import collections.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

public class IntervalTree {
    
    private Node root;
    
    static class Interval implements Comparable<Interval> {
        double lo, hi;
        
        public Interval(double lo, double hi) {
            this.lo = lo;
            this.hi = hi;
        }
        
        public boolean intersects(Interval interval) {
            return !(lo > interval.hi || hi < interval.lo);
        }
        
        public void draw(double deltaX, double deltaY, double delta, int height) {
            StdDraw.line((lo - delta) * 2 * deltaX, height * deltaY, ((hi - delta) * 2 - 1) * deltaX, height * deltaY);
            System.out.println(this);
        }
        
        @Override
        public String toString() {
            return String.format(Interval.class.getSimpleName() + "[%.4f, %.4f]", lo, hi);
        }
        
        public static Interval randomInterval() {
            double a = StdRandom.uniform();
            double b = StdRandom.uniform();
            return new Interval(Math.min(a, b), Math.max(a, b));
        }

        @Override
        public int compareTo(Interval other) {
            if (hi == other.hi) return 0;
            else if (hi > other.hi) return 1;
            else return -1;
        }
    }
    
    class Node {
        static final boolean RED = true;
        static final boolean BLK = false;
        double key;
        Interval value, interval;
        int size, height;
        Node left, right;
        boolean color;
        
        Node (Interval value) {
            this.key = value.lo;
            this.value = value;
            interval = value;
            size = height = 1;
            color = RED;
        }
        
        void reset() {
            size = (left == null ? 0 : left.size) + (right == null ? 0 : right.size) + 1;
            height = Math.max(left == null ? 0 : left.height, right == null ? 0 : right.height) + 1;
            interval = new Interval(
                    min(interval.lo, left == null ? interval.lo : left.interval.lo, right == null ? interval.lo : right.interval.lo),
                    max(interval.hi, left == null ? interval.hi : left.interval.hi, right == null ? interval.hi : right.interval.hi));
        }
        
        private double min(double a, double b, double c) {
            return Math.min(a, Math.min(b, c));
        }
        
        private double max(double a, double b, double c) {
            return Math.max(a, Math.max(b, c));
        }
        
        boolean isRed(Node node) {
            return node != null && node.color;
        }
        
        void flipColor() {
            if (isRed(left) && isRed(right)) {
                left.color = BLK;
                right.color = BLK;
                color = RED;
            }
        }
        
        Node rotateLeft() {
            if (isRed(right) && !isRed(left)) {
                Node node = right;
                right = node.left;
                node.left = this;
                node.color = color;
                color = RED;
                reset();
                node.reset();
                return node;
            }
            return this;
        }
        
        Node rotateRight() {
            if (isRed(left) && isRed(left.left)) {
                Node node = left;
                left = node.right;
                node.right = this;
                node.color = color;
                color = RED;
                reset();
                node.reset();
                return node;
            }
            return this;
        }
    }
    
    public void put(Interval interval) {
        root = put(root, interval);
        root.color = Node.BLK;
    }
    
    private Node put(Node node, Interval interval) {
        if (node == null) return new Node(interval);
        double cmp = interval.lo - node.key;
        if (cmp < 0) node.left = put(node.left, interval);
        else if (cmp > 0) node.right = put(node.right, interval);
        else node.value = interval;
        
        node = node.rotateLeft();
        node = node.rotateRight();
        node.flipColor();
        
        node.reset();
        return node;
    }

    static int count = 0;
    public Interval intersects(Interval interval) {
        return intersects(root, interval);
    }
    
    private Interval intersects(Node node, Interval interval) {
        if (node == null) return null;
        count++;
        if (node.value.intersects(interval)) return node.value;
        else if (node.left == null || node.left.interval.hi < interval.lo) return intersects(node.right, interval);
        else return intersects(node.left, interval);
    }
    
    public List<Interval> intersectsAll(Interval interval) {
        List<Interval> intervals = new ArrayList<>();
        intersectsAll(root, intervals, interval);
//        intersectsAllBruteForce(root, intervals, interval);
        return intervals;
    }
    
    private void intersectsAll(Node node, List<Interval> intervals, Interval interval) {
        if (node == null || !node.interval.intersects(interval)) return;
        count++;
//        if (node.left == null || node.left.maxhi < interval.lo) intersectsAll(node.right, intervals, interval);
//        else {
//            intersectsAll(node.left, intervals, interval);
//            intersectsAll(node.right, intervals, interval);
//        }
        if (node.left != null) intersectsAll(node.left, intervals, interval);
        if (node.value.intersects(interval)) intervals.add(node.value);
        if (node.right != null) intersectsAll(node.right, intervals, interval);
    }
    
    private void intersectsAllBruteForce(Node node, List<Interval> intervals, Interval interval) {
        if (node == null) return;
        count++;
        if (node.left != null) intersectsAllBruteForce(node.left, intervals, interval);
        if (node.value.intersects(interval)) intervals.add(node.value);
        if (node.right != null) intersectsAllBruteForce(node.right, intervals, interval);
    }
    
    public int size() {
        return size(root);
    }
    
    private int size(Node node) {
        return node == null ? 0 : node.size;
    }
    
    public int height() {
        return height(root);
    }
    
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
    
    public void print() {
        print(root);
    }
    
    private void print(Node node) {
        if (node == null) return;
        if (node.left != null) print(node.left);
        System.out.println(node.value);
        if (node.right != null) print(node.right);
    }
    
    public static void main(String[] args) {
        StdRandom.setSeed("gao".hashCode());
        int size = 1000000;
        IntervalTree intervalTree = new IntervalTree();
        Interval[] intervals = new Interval[size];
        for (int i = 0; i < size; i++) intervals[i] = Interval.randomInterval();
        Arrays.sort(intervals);
        for (int i = 0; i < size; i++) intervalTree.put(intervals[i]);
        intervalTree.put(new Interval(1.0, 1.01));
        System.out.println(intervalTree.root.interval);
//        System.out.println(intervalTree.size());
//        System.out.println(intervalTree.height());
//        intervalTree.print();
        System.out.println();
        
//        Interval target = new Interval(1.005, 1.009);
//        System.out.println(intervalTree.intersects(target));
        Interval target = new Interval(0.99995, 0.99996);
        List<Interval> intersectIntervals = intervalTree.intersectsAll(target);
        System.out.println(intersectIntervals.size());
        for (Interval interval: intersectIntervals) System.out.println(interval);
        System.out.println(count);
    }

}
