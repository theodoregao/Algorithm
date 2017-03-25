package collections.impl.general;

public class SegmentTree {
    
    private Node root;
    private Node[] items;
    
    public SegmentTree(int[] items) {
        this.items = new Node[items.length];
        root = build(items, 0, items.length - 1);
    }
    
    private Node build(int[] items, int lo, int hi) {
        if (lo == hi) return this.items[lo] = new Node(new Segment(lo, hi), items[lo]);
        int mid = (lo + hi) >> 1;
        Node left = build(items, lo, mid);
        Node right = build(items, mid + 1, hi);
        Node node = new Node(new Segment(lo, hi), Math.max(left.value, right.value));
        node.left = left;
        node.right = right;
        node.left.parent = node;
        node.right.parent = node;
        return node;
    }
    
    public int getMax(Segment segment) {
        return getMax(root, segment);
    }
    
    private int getMax(Node node, Segment segment) {
        if (node == null) return Integer.MIN_VALUE;
        switch (segment.intersect(node.segment)) {
        case FULL:
            return node.value;
            
        case PARTIAL:
            return Math.max(getMax(node.left, segment), getMax(node.right, segment));
            
        case NONE:
        default:
            return Integer.MIN_VALUE;
        }
    }
    
    public void update(int index, int val) {
        Node node = items[index];
        node.value = val;
        while ((node = node.parent) != null) node.value = Math.max(node.left.value, node.right.value);
    }

    static class Node {
        Segment segment;
        Node parent;
        Node left;
        Node right;
        int value;
        Node(Segment segment, int value) {
            this.segment = segment;
            this.value = value;
        }
    }
    
    static class Segment {
        static enum Intersect {
            FULL, PARTIAL, NONE
        }
        int lo, hi;
        Segment(int lo, int hi) {
            this.lo = lo; this.hi = hi;
        }
        Intersect intersect(Segment segment) {
            if (lo <= segment.lo && hi >= segment.hi) return Intersect.FULL;
            else if (lo > segment.hi || hi < segment.lo) return Intersect.NONE;
            else return Intersect.PARTIAL;
        }
    }
    
    public static void main(String[] args) {
        SegmentTree st = new SegmentTree(new int[] {-1,3,5,7,4,0,2,5});
        System.out.println(st.getMax(new Segment(4, 6)));
        st.update(5, 10);
        System.out.println(st.getMax(new Segment(1, 7)));
        System.out.println(st.getMax(new Segment(6, 7)));
    }

}