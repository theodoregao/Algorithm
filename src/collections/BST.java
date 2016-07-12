package collections;

public class BST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    
    private Node root;

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }
    
    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }
    
    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }
    
    private int size(Node node) {
        if (node == null) return 0;
        else return node.size;
    }

    @Override
    public Key min() {
        return min(root).key;
    }
    
    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }
    
    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    @Override
    public Key floor(Key key) {
        Node floor = floor(root, key);
        if (floor == null) return null;
        else return floor.key;
    }
    
    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left, key);
        Node floor = floor(node.right, key);
        if (floor != null) return floor;
        else return node;
    }

    @Override
    public Key ceiling(Key key) {
        Node ceiling = ceiling(root, key);
        if (ceiling == null) return null;
        else return ceiling.key;
    }
    
    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0) return ceiling(node.right, key);
        Node ceiling = ceiling(node.left, key);
        if (ceiling != null) return ceiling;
        else return node;
    }

    @Override
    public int rank(Key key) {
        return rank(root, key);
    }
    
    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return size(node.left);
        else if (cmp < 0) return rank(node.left, key);
        else return size(node.left) + rank(node.right, key) + 1;
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }
    
    private Node select(Node node, int k) {
        if (node == null) return null;
        int leftSize = size(node.left);
        if (leftSize == k) return node;
        else if (leftSize > k) return select(node.left, k);
        else return select(node.right, k - leftSize - 1);
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }
    
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }
    
    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }
    
    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public int size(Key lo, Key hi) {
        Queue<Key> queue = new LinkedQueue<Key>();
        keys(root, queue, lo, hi);
        return queue.size();
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedQueue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(node.key);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
    }
    
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int size;
        
        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }
    
    // test word count
//    public static void main(String[] args) {
//        int minLength = 8;
//        SymbolTable<String, Integer> st = new BST<String, Integer>();
//        In in = new In("data/tale.txt");
//  
//        while (!in.isEmpty()) {
//            String word = in.readString();
//            if (word.length() < minLength) continue;
//            if (!st.contains(word)) st.put(word, 1);
//            else st.put(word, st.get(word) + 1);
//        }
//  
//        String max = "";
//        st.put(max, 0);
//        for (String word: st.keys())
//            if (st.get(word) > st.get(max))
//                max = word;
//        System.out.println(max + ": " + st.get(max));
//    }
    
    // test floor & ceiling
//    public static void main(String[] args) {
//        SymbolTable<Integer, Integer> st = new BST<Integer, Integer>();
//        st.put(1, 0);
//        st.put(10, 1);
//        st.put(100, 2);
//  
//        for (Integer key: st.keys())
//            System.out.println(key + ": " + st.get(key));
//
//        System.out.println("floor");
//        System.out.println(st.floor(0));
//        System.out.println(st.floor(1));
//        System.out.println(st.floor(9));
//        System.out.println(st.floor(10));
//        System.out.println(st.floor(99));
//        System.out.println(st.floor(100));
//        System.out.println(st.floor(101));
//
//        System.out.println("ceiling");
//        System.out.println(st.ceiling(0));
//        System.out.println(st.ceiling(1));
//        System.out.println(st.ceiling(2));
//        System.out.println(st.ceiling(9));
//        System.out.println(st.ceiling(10));
//        System.out.println(st.ceiling(11));
//        System.out.println(st.ceiling(99));
//        System.out.println(st.ceiling(100));
//        System.out.println(st.ceiling(101));
//    }
    
    public static void main(String[] args) {
        SymbolTable<String, Integer> st = new BST<>();
        st.put("C++", 0);
        st.put("C++", 1);
        st.put("Java", 0);
        st.put("Python", 0);
        st.put("C#", 0);
        st.put("JavaScript", 0);
        st.put("Ruby", 0);
        st.put("Swift", 0);
        st.put("Go", 0);
        
        st.put("Python", 1);
        st.put("Java", 1);
        st.put("C#", 1);
        
        for (String key: st.keys()) {
            System.out.println(key + ": " + st.get(key));
        }
        
        System.out.println(st.min());
        System.out.println(st.max());
        
        // test delete
        st.delete("C++");
        st.delete("Python");
        
        for (String key: st.keys()) {
            System.out.println(key + ": " + st.get(key));
        }

        System.out.println(st.min());
        System.out.println(st.max());
        
        st.delete("C#");
        st.delete("Swift");

        // test min, max
        for (String key: st.keys()) {
            System.out.println(key + ": " + st.get(key));
        }
        System.out.println(st.min());
        System.out.println(st.max());
        
        // test size
        System.out.println(st.size());
        
        // test rank, select
        System.out.println("" + st.rank("Gn"));
        System.out.println("" + st.rank("Go"));
        System.out.println("" + st.rank("Gp"));
        System.out.println("" + st.rank("Jav"));
        System.out.println("" + st.rank("Java"));
        System.out.println("" + st.rank("JavaA"));
        System.out.println("" + st.rank("Rubx"));
        System.out.println("" + st.rank("Ruby"));
        System.out.println("" + st.rank("Rubz"));
        for (int i = 0; i < st.size(); i++)
            System.out.println(st.select(i));
    }
}
