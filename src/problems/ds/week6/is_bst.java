package problems.ds.week6;
import java.util.*;
import java.io.*;

public class is_bst {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class IsBST {
        Tree tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            int n = in.nextInt();
            tree = new Tree(n);
            for (int i = 0; i < n; i++) {
                tree.add(i, in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            Node root = tree.root();
          return root == null || (inOrder(root, root.left, Integer.MIN_VALUE, root.val) && inOrder(root, root.right, root.val, Integer.MAX_VALUE));
        }
        
        boolean inOrder(Node parent, Node node, int min, int max) {
            if (node == null) return true;
            if (node.val < min || node.val > max) return false;
            if (parent.left == node) {
                return inOrder(node, node.left, min, node.val)
                        && inOrder(node, node.right, node.val, parent.val);
            }
            else {
                return inOrder(node, node.left, parent.val, node.val)
                        && inOrder(node, node.right, node.val, max);
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
    
    private static class Node {
        public int val;
        public Node left, right;
    }
    
    private static class Tree {
        Map<Integer, Node> nodes = new HashMap<>();
        Set<Integer> roots = new HashSet<>();
        Node root;
        
        public Tree(int n) {
            for (int i = 0; i < n; i++) roots.add(i);
            nodes = new HashMap<>();
        }

        public void add(int i, int value, int left, int right) {
            if (!nodes.containsKey(i)) nodes.put(i, new Node());
            if (left != -1 && !nodes.containsKey(left)) nodes.put(left, new Node());
            if (right != -1 && !nodes.containsKey(right)) nodes.put(right, new Node());
            
            Node node = nodes.get(i);
            node.val = value;
            if (left != -1) {
                roots.remove(left);
                node.left = nodes.get(left);
            }
            if (right != -1) {
                roots.remove(right);
                node.right = nodes.get(right);
            }
        }
        
        public Node root() {
            if (root == null && nodes.size() > 0) root = nodes.get(roots.iterator().next());
            return root;
        }
    }
}
