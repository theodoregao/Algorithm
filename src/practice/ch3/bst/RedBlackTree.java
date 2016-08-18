package practice.ch3.bst;

import java.util.ArrayList;
import java.util.List;

import collections.Queue;
import collections.impl.queue.LinkedQueue;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    
    Node root;
    
    class Node {
        static final boolean RED = true;
        static final boolean BLK = false;
        Key key;
        Value value;
        int height, size;
        Node left, right, pred, succ;
        boolean color;
        
        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            height = size = 1;
            color = RED;
        }
        
        void reset() {
            size = (left == null ? 0 : left.size) + (right == null ? 0 : right.size) + 1;
            height = Math.max(left == null ? 0 : left.height, right == null ? 0 : right.height) + 1;
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
        
        void flipColors() {
            if (isRed(left) && isRed(right)) {
                color = RED;
                left.color = BLK;
                right.color = BLK;
            }
        }
        
        boolean isRed(Node node) {
            return node != null && node.color;
        }
    }
    
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = Node.BLK;
        int rank = rank(root, key);
        Node node = select(root, rank);
        if (rank > 0) {
            Node pred = select(root, rank - 1);
            pred.succ = node;
            node.pred = pred;
        }
        if (rank < size() - 1) {
            Node succ = select(root, rank + 1);
            succ.pred = node;
            node.succ = succ;
        }
    }

    public Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        
        node = node.rotateLeft();
        node = node.rotateRight();
        node.flipColors();
        
        node.reset();
        return node;
    }
    
    public Value get(Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }
    
    private Node get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        else if (cmp < 0) return get(node.left, key);
        else return get(node.right, key);
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
    
    public int rank(Key key) {
        return rank(root, key);
    }
    
    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return size(node.left);
        else if (cmp < 0) return rank(node.left, key);
        else return size(node.left) + 1 + rank(node.right, key);
    }
    
    public Key select(int k) {
        Node node = select(root, k);
        return node == null ? null : node.key;
    }
    
    private Node select(Node node, int k) {
        if (node == null) return null;
        int leftSize = size(node.left);
        if (leftSize == k) return node;
        else if (leftSize > k) return select(node.left, k);
        else return select(node.right, k - leftSize - 1);
    }
    
    public Key min() {
        Node min = min(root);
        return min == null ? null : min.key;
    }
    
    private Node min(Node node) {
        if (node == null || node.left == null) return node;
        return min(node.left);
    }
    
    public Key max() {
        Node max = max(root);
        return max == null ? null : max.key;
    }
    
    private Node max(Node node) {
        if (node == null || node.right == null) return node;
        return max(node.right);
    }
    
    public void deleteMin() {
        Node min = min(root);
        root = deleteMin(root);
        if (min != null && min.succ != null) min.succ.pred = null;
    }
    
    private Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.reset();
        return node;
    }
    
    public void deleteMax() {
        Node max = max(root);
        root = deleteMax(root);
        if (max != null && max.pred != null) max.pred.succ = null;
    }
    
    private Node deleteMax(Node node) {
        if (node == null) return null;
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.reset();
        return node;
    }
    
    public void delete(Key key) {
        Node node = select(root, rank(root, key));
        root = delete(root, key);
        if (node != null) {
            if (node.pred != null) node.pred.succ = node.succ;
            if (node.succ != null) node.succ.pred = node.pred;
        }
    }
    
    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            
            Node rightMin = min(node.right);
            rightMin.right = deleteMin(node.right);
            rightMin.left = node.left;
            node = rightMin;
        }
        
        node.reset();
        return node;
    }
    
    public List<Key> keys() {
//        return keys(min(), max());
        List<Key> keys = new ArrayList<>();
        Node node = min(root);
        while (node != null) {
            keys.add(node.key);
            node = node.succ;
        }
        return keys;
    }
    
    public List<Key> keys(Key lo, Key hi) {
        List<Key> keys = new ArrayList<>();
        keys(keys, root, lo, hi);
        return keys;
    }
    
    private void keys(List<Key> keys, Node node, Key lo, Key hi) {
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(keys, node.left, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) keys.add(node.key);
        if (cmphi > 0) keys(keys, node.right, lo, hi);
    }
    
    public List<Key> levelKeys() {
        List<Key> keys = new ArrayList<>();
        Queue<Node> queue = new LinkedQueue<>();
        Node node;
        if (root != null) queue.enqueue(root);
        while (!queue.isEmpty()) {
            node = queue.dequeue();
            keys.add(node.key);
            if (node.left != null) queue.enqueue(node.left);
            if (node.right != null) queue.enqueue(node.right);
        }
        return keys;
    }
    
    public static void main(String[] args) {
        int size = 1024*1024 - 1;
        RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
        for (int i = 0; i < size; i++) rbt.put(i, i);
        for (int i = 1000; i < 10000; i++) rbt.delete(i);
        int count = 0;
        for (Integer key: rbt.keys()) count++;
        System.out.println(count);
        System.out.println(rbt.height());
    }
}
