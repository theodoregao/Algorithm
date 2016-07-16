package collections;

public class RedBlackTree<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    
    private Node root;

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = Node.BLACK;
        int rank = rank(key);
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
    
    private Node put(Node node, Key key, Value value) {
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
    
    public int height() {
        return height(root);
    }
    
    private int height(Node node) {
        if (node == null) return 0;
        else return node.height;
    }

    @Override
    public Key min() {
        return min(root).key;
    }
    
    private Node min(Node node) {
        return (node == null || node.left == null) ? node : min(node.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }
    
    private Node max(Node node) {
        return (node == null || node.right == null) ? node : max(node.right);
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
        Node min = min(root);
        root = deleteMin(root);
        if (min != null && min.succ != null) min.succ.pred = null;
    }
    
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.reset();
        return node;
    }

    @Override
    public void deleteMax() {
        Node max = max(root);
        root = deleteMax(root);
        if (max != null && max.pred != null) max.pred.succ = null;
    }
    
    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.reset();
        return node;
    }

    @Override
    public void delete(Key key) {
        Node node = select(root, rank(key));
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
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.reset();
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
        Queue<Key> queue = new LinkedQueue<>();
        Node node = min(root);
        while (node != null) {
            queue.enqueue(node.key);
            node = node.succ;
        }
        return queue;
    }
    
    public Iterable<Key> loopKeys() {
        Queue<Key> queue = new LinkedQueue<>();
        Stack<Node> nodes = new LinkedStack<>();
        Node currentNode = root;
        do {
            if (currentNode != null) {
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }
            else if (!nodes.isEmpty()) {
                currentNode = nodes.pop();
                queue.enqueue(currentNode.key);
                currentNode = currentNode.right;
            }
        } while (currentNode != null || !nodes.isEmpty());
        
        return queue;
    }
    
    public Iterable<Key> levelKeys() {
        Queue<Key> queue = new LinkedQueue<>();
        Queue<Node> nodes = new LinkedQueue<>();
        Node node;
        if (root != null) nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            node = nodes.dequeue();
            if (node.left != null) nodes.enqueue(node.left);
            if (node.right != null) nodes.enqueue(node.right);
            queue.enqueue(node.key);
        }
        return queue;
    }

//    private Iterable<Key> recursiveKeys() {
//        return keys(min(), max());
//    }

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
    
    private Iterable<Node> nodes() {
        Queue<Node> queue = new LinkedQueue<>();
        nodes(root, queue);
        return queue;
    }
    
    private void nodes(Node node, Queue<Node> queue) {
        if (node == null) return;
        nodes(node.left, queue);
        queue.enqueue(node);
        nodes(node.right, queue);
    }
    
    public void clear() {
        clear(root);
        root = null;
    }
    
    private void clear(Node node) {
        if (node == null) return;
        clear(node.left);
        clear(node.right);
        node.key = null;
        node.value = null;
        node.left = null;
        node.right = null;
    }

    private class Node {

        private static final boolean RED = true;
        private static final boolean BLACK = false;
        
        private Key key;
        private Value value;
        private Node left, right, pred, succ;
        private int size;
        private int height;
        private boolean color;
        
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
            height = 1;
            pred = null;
            succ = null;
            color = RED;
        }
        
        public Node rotateLeft() {
            if (isRed(right) && !isRed(left)) {
                Node x = right;
                right = x.left;
                x.left = this;
                x.color = color;
                color = RED;
                reset();
                x.reset();
                return x;
            }
            return this;
        }
        
        public Node rotateRight() {
            if (isRed(left) && isRed(left.left)) {
                Node x = left;
                left = x.right;
                x.right = this;
                x.color = color;
                color = RED;
                reset();
                x.reset();
                return x;
            }
            return this;
        }
        
        public void flipColors() {
            if (isRed(left) && isRed(right)) {
                color = RED;
                left.color = BLACK;
                right.color = BLACK;
            }
        }
        
        private boolean isRed(Node node) {
            return node != null && node.color;
        }
        
        public int resolveSize() {
            return resolveSize(this);
        }
        
        private int resolveSize(Node node) {
            if (node == null) return 0;
            return resolveSize(node.left) + resolveSize(node.right) + 1;
        }
        
        public int resolveHeight() {
            return resolveHeight(this);
        }
        
        private int resolveHeight(Node node) {
            if (node == null) return 0;
            return Math.max(resolveHeight(node.left), resolveHeight(node.right)) + 1;
        }
        
        public void reset() {
            resetSize();
            resetHeight();
        }
        
        public int resetSize() {
            return setSize(((left == null) ? 0 : left.size) + ((right == null) ? 0 : right.size) + 1);
        }
        
        public int setSize(int size) {
            return this.size = size;
        }
        
        public int resetHeight() {
            return setHeight(Math.max(((left == null) ? 0 : left.height), ((right == null) ? 0 : right.height)) + 1);
        }
        
        public int setHeight(int height) {
            return this.height = height;
        }
        
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(Node.class.getSimpleName());
            stringBuilder.append("{")
            .append("key=").append(key)
            .append(", value=").append(value)
            .append(", size=").append(size)
            .append(", height=").append(height)
//            .append(", left=").append(key(left))
//            .append(", right=").append(key(right))
//            .append(", pred=").append(key(pred))
//            .append(", succ=").append(key(succ))
            .append("}");
            return stringBuilder.toString();
        }
        
//        private String key(Node node) {
//            if (node == null) return "null";
//            else return node.key.toString();
//        }
    }
    
    private void print() {
        print(root);
    }
    
    private void print(Node node) {
        if (node == null) return;
        print(node.left);
        System.out.println(node);
        print(node.right);
    }
    
    private static void testFloorCeiling() {
        SymbolTable<Integer, Integer> st = new RedBlackTree<Integer, Integer>();
        st.put(1, 0);
        st.put(10, 1);
        st.put(100, 2);
  
        for (Integer key: st.keys())
            System.out.println(key + ": " + st.get(key));

        System.out.println("floor");
        System.out.println(st.floor(0));
        System.out.println(st.floor(1));
        System.out.println(st.floor(9));
        System.out.println(st.floor(10));
        System.out.println(st.floor(99));
        System.out.println(st.floor(100));
        System.out.println(st.floor(101));

        System.out.println("ceiling");
        System.out.println(st.ceiling(0));
        System.out.println(st.ceiling(1));
        System.out.println(st.ceiling(2));
        System.out.println(st.ceiling(9));
        System.out.println(st.ceiling(10));
        System.out.println(st.ceiling(11));
        System.out.println(st.ceiling(99));
        System.out.println(st.ceiling(100));
        System.out.println(st.ceiling(101));
    }
    
    private static void testSizeHeight() {
        RedBlackTree<String, Integer> bst = new RedBlackTree<>();
        bst.put("C++", 0);
        bst.put("C++", 1);
        bst.put("Java", 0);
        bst.put("Python", 0);
        bst.put("C#", 0);
        bst.put("JavaScript", 0);
        bst.put("Ruby", 0);
        bst.put("Swift", 0);
        bst.put("Go", 0);
        
        bst.put("Python", 1);
        bst.put("Java", 1);
        bst.put("C#", 1);
        
        bst.print();
        System.out.println();
        System.out.println("size: " + bst.size());
        System.out.println("height: " + bst.height());
        System.out.println("==================================================");
        System.out.println();
        
        bst.delete("C++");
        bst.delete("Ruby");

        bst.print();
        System.out.println();
        System.out.println("size: " + bst.size());
        System.out.println("height: " + bst.height());
        System.out.println("==================================================");
        System.out.println();
        
        bst.delete("JavaScript");
        bst.put("SQL", 1024);
        bst.put("Android", 0);

        bst.print();
        System.out.println();
        System.out.println("size: " + bst.size());
        System.out.println("height: " + bst.height());
        System.out.println("==================================================");
        System.out.println();
    }
    
    private static void testBasic() {
        System.out.println("******************testBasic()******************");
        RedBlackTree<String, Integer> bst = new RedBlackTree<>();
        bst.put("C++", 1);
        bst.put("Java", 0);
        bst.put("Python", 0);
        bst.put("C#", 0);
        bst.put("JavaScript", 0);
        bst.put("Ruby", 0);
        bst.put("Swift", 0);
        bst.put("Go", 0);
        
        bst.put("Python", 1);
        bst.put("Java", 1);
        bst.put("C#", 1);
        
        for (String key: bst.keys()) {
            System.out.println(key + ": " + bst.get(key));
        }

        System.out.println("min: " + bst.min());
        System.out.println("max: " + bst.max());
        
        System.out.println();
        
        // test delete
        bst.delete("C++");
        bst.delete("Python");

        bst.delete("C#");
        bst.delete("Swift");

        // test min, max
        for (String key: bst.keys()) {
            System.out.println(key + ": " + bst.get(key));
        }

        System.out.println("min: " + bst.min());
        System.out.println("max: " + bst.max());
    }
    
    private static void testIterator() {
        System.out.println("******************testIterator()******************");
        System.out.println("test RBT iterator sorted");
        System.out.println("=====================================================");
        int size = 10000;
        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
        for (int i = 0; i < size; i++) redBlackTree.put(i, null);
        
        System.out.println("RBT iterator sorted succeed: " + isSorted(redBlackTree));
        System.out.println();
    }
    
    private static <Key extends Comparable<Key>, Value> boolean isSorted(RedBlackTree<Key, Value> redBlackTree) {
        System.out.println("size: " + redBlackTree.size());
        System.out.println("height: " + redBlackTree.height());
        System.out.println("min: " + redBlackTree.min());
        System.out.println("max: " + redBlackTree.max());
        Key min = redBlackTree.min();
        for (Key key: redBlackTree.keys()) {
            if (key.compareTo(min) < 0) {
                System.out.println("failed at: " + key);
                return false;
            }
            min = key;
        }
        return true;
    }
    
    private static void testClear() {
        RedBlackTree<String, Integer> bst = new RedBlackTree<>();
        bst.put("C++", 0);
        bst.put("C++", 1);
        bst.put("Java", 0);
        bst.put("Python", 0);
        bst.put("C#", 0);
        bst.put("JavaScript", 0);
        bst.put("Ruby", 0);
        bst.put("Swift", 0);
        bst.put("Go", 0);
        
        bst.put("Python", 1);
        bst.put("Java", 1);
        bst.put("C#", 1);
        
        bst.clear();
        
        for (String key: bst.keys()) {
            System.out.println(key);
            System.out.println(key + ": " + bst.get(key));
        }
    }
    
    private boolean isBinaryTree() {
        return isBinaryTree(root);
    }
    
    private boolean isBinaryTree(Node node) {
        if (node == null) return true;
        return isBinaryTree(node.left) && isBinaryTree(node.right) &&
                (node.left == null ? 0 : node.left.resolveSize()) + (node.right == null ? 0 : node.right.resolveSize()) + 1 == node.size;
    }
    
    private boolean isOrdered() {
        Node preNode = null;
        for (Node node: nodes()) {
            if (preNode != null) if (preNode.key.compareTo(node.key) > 0) return false;
            preNode = node;
        }
        return true;
    }
    
    private boolean hasNoDuplicates() {
        Node preNode = null;
        for (Node node: nodes()) {
            if (preNode != null) if (preNode.key.compareTo(node.key) == 0) return false;
            preNode = node;
        }
        return true;
    }
    
    private boolean isBST() {
        return isBinaryTree()
                && isOrdered()
                && hasNoDuplicates();
    }
    
    private static void testBst() {
        RedBlackTree<String, Integer> bst = new RedBlackTree<>();
        bst.put("C++", 0);
        bst.put("C++", 1);
        bst.put("Java", 0);
        bst.put("Python", 0);
        bst.put("C#", 0);
        bst.put("JavaScript", 0);
        bst.put("Ruby", 0);
        bst.put("Swift", 0);
        bst.put("Go", 0);
      
        bst.put("Python", 1);
        bst.put("Java", 1);
        bst.put("C#", 1);
        
        bst.print();

//        bst.root.right.height = 1;
//        bst.root.right.size = 1;
        
//        bst.root.left.key = "Java";
//        bst.root.right.key = "C#";
        
//        bst.root.right.key = "C++";
        
        System.out.println(bst.isBST());
    }
    
    private static void stressTestBst() {
        RedBlackTree<Integer, Integer> bst = new RedBlackTree<>();
        for (int i = 0; i < 1000; i++) bst.put(i, i);
        System.out.println(bst.isBST());
    }
    
    private static void testSelectRank() {
        RedBlackTree<String, Integer> bst = new RedBlackTree<>();
        bst.put("C++", 0);
        bst.put("C++", 1);
        bst.put("Java", 0);
        bst.put("Python", 0);
        bst.put("C#", 0);
        bst.put("JavaScript", 0);
        bst.put("Ruby", 0);
        bst.put("Swift", 0);
        bst.put("Go", 0);
      
        bst.put("Python", 1);
        bst.put("Java", 1);
        bst.put("C#", 1);
        
        bst.print();
        
        int i = 0;
        for (String key: bst.keys()) {
            System.out.println(key);
            System.out.println(i);
            System.out.println(bst.select(i++));
            System.out.println(bst.rank(key));
            System.out.println();
        }

//        System.out.println("" + bst.select(-1));
//        System.out.println("" + bst.select(bst.size()));
        System.out.println("" + bst.rank("A"));
        System.out.println("" + bst.rank("Z"));
    }
    
//    private static void stressTest() {
//        System.out.println("******************stressTest()******************");
//        Stopwatch stopwatch = new Stopwatch();
//        System.out.println("stess test RBT iterator sorted");
//        System.out.println("=====================================================");
//        int size = 10000000;
//        
//        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
//        for (int i = 0; i < size; i++) {
//            redBlackTree.put(i, null);
//        }
//        System.out.println(stopwatch.elapsedTime());
//        System.out.println("RBT iterator sorted succeed: " + isSorted(redBlackTree));
//        System.out.println();
//        
//        stopwatch = new Stopwatch();
//        System.out.println("stress test RBT delete");
//        Random random = new Random();
//        for (int i = 0; i < size; i++) {
//            redBlackTree.put(random.nextInt(size * 100), null);
////            redBlackTree.put(random.nextInt(size), null);
//            redBlackTree.delete(random.nextInt(size * 100));
//        }
//        System.out.println(stopwatch.elapsedTime());
//        System.out.println("RBT iterator sorted succeed: " + isSorted(redBlackTree));
//        System.out.println();
//    }
    
    // test rank, select
    public static void main(String[] args) {
//        testBasic();
//        testFloorCeiling();
//        testSizeHeight();
//        testSelectRank();
//        testBst();
//        testIterator();
//        stressTest();
    }
}
