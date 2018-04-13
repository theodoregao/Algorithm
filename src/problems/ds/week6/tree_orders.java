package problems.ds.week6;
import java.util.*;
import java.io.*;

public class tree_orders {
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

	public class TreeOrders {
	    
	    Tree tree;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
	        int n;
			n = in.nextInt();
			tree = new Tree(n);
			for (int i = 0; i < n; i++) {
                tree.add(i, in.nextInt(), in.nextInt(), in.nextInt());
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
            inOrder(result, tree.root());
			return result;
		}
		
		void inOrder(List<Integer> list, Node node) {
		    if (node == null) return;
            inOrder(list, node.left);
		    list.add(node.val);
            inOrder(list, node.right);
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
            preOrder(result, tree.root());   
			return result;
		}
        
        void preOrder(List<Integer> list, Node node) {
            if (node == null) return;
            list.add(node.val);
            preOrder(list, node.left);
            preOrder(list, node.right);
        }

		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
            postOrder(result, tree.root());     
			return result;
		}
        
        void postOrder(List<Integer> list, Node node) {
            if (node == null) return;
            postOrder(list, node.left);
            postOrder(list, node.right);
            list.add(node.val);
        }
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
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
