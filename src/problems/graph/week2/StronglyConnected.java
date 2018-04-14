package problems.graph.week2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class StronglyConnected {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Graph.Digraph digraph = new Graph.Digraph(n);
        for (int i = 0; i < m; i++) {
            digraph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        
        System.out.println(new Graph.StrongConnectedComponent(digraph).count());
    }

    
    private static class Graph {
        private static class Digraph {
            private Map<Integer, Set<Integer>> edges = new HashMap<>();
            public Digraph(int n) {
                for (int i = 1; i <= n; i++) edges.put(i, new HashSet<Integer>());
            }
            public void addEdge(int v, int u) {
                if (!edges.containsKey(v)) edges.put(v, new HashSet<Integer>());
                if (!edges.containsKey(u)) edges.put(u, new HashSet<Integer>());
                edges.get(v).add(u);
            }
            
            public Iterable<Integer> vertex() {
                return edges.keySet();
            }
            
            public Iterable<Integer> adj(int v) {
                return edges.get(v);
            }
            
            public Digraph reverse() {
                Digraph digraph = new Digraph(size());
                for (int v: vertex()) for (int u: adj(v)) digraph.addEdge(u, v);
                return digraph;
            }
            
            public int size() {
                return edges.keySet().size();
            }
        }
        
        private static class DfsOrder {
            private Set<Integer> marked = new HashSet<>();
            private Queue<Integer> pre = new LinkedList<>();
            private Queue<Integer> post = new LinkedList<>();
            private Stack<Integer> reversePost = new Stack<>();
            
            public DfsOrder(Digraph digraph) {
                for (int v: digraph.vertex())
                    if (!marked.contains(v))
                        dfs(digraph, v);
            }
            
            private void dfs(Digraph digraph, int v) {
                marked.add(v);
                pre.offer(v);
                for (int u: digraph.adj(v))
                    if (!marked.contains(u))
                        dfs(digraph, u);
                post.offer(v);
                reversePost.push(v);
            }
            
            public Iterable<Integer> pre() {
                return pre;
            }
            
            public Iterable<Integer> post() {
                return post;
            }
            
            public Stack<Integer> reversePost() {
                return reversePost;
            }
        }
        
        private static class Cycle {
            private Set<Integer> marked = new HashSet<>();
            private Set<Integer> inCycle = new HashSet<>();
            private Stack<Integer> cycle;
            
            public Cycle(Digraph digraph) {
                for (int v: digraph.vertex())
                    if (!marked.contains(v))
                        dfs(digraph, v);
            }
            
            private void dfs(Digraph digraph, int v) {
                marked.add(v);
                inCycle.add(v);
                for (int u: digraph.adj(v)) {
                    if (!marked.contains(u)) dfs(digraph, u);
                    else if (inCycle.contains(u)) {
                        cycle = new Stack<>();
                        cycle.push(u);
                        break;
                    }
                }
                inCycle.remove(v);
                if (cycle != null) cycle.push(v);
            }
            
            public boolean hasCycle() {
                return cycle != null;
            }
            
            public Iterable<Integer> cycle() {
                return cycle;
            }
            
            public boolean isDag() {
                return cycle == null;
            }
        }
        
        private static class TopologicalSort {
            private Iterable<Integer> order;
            public TopologicalSort(Digraph digraph) {
                if (new Cycle(digraph).isDag())
                    order = new DfsOrder(digraph.reverse()).reversePost();
            }
            
            public boolean hasTopologicalSort() {
                return order != null;
            }
            
            public Iterable<Integer> order() {
                return order;
            }
        }
        
        private static class StrongConnectedComponent {
            private Set<Integer> marked = new HashSet<>();
            private int[] ids;
            private int count;
            
            public StrongConnectedComponent(Digraph digraph) {
                ids = new int[digraph.size() + 1];
                Stack<Integer> reversePost = new DfsOrder(digraph.reverse()).reversePost();
                while (!reversePost.isEmpty()) {
                    int v = reversePost.pop();
                    if (!marked.contains(v)) {
                        dfs(digraph, v);
                        count++;
                    }
                }
            }
            
            private void dfs(Digraph digraph, int v) {
                marked.add(v);
                ids[v] = count;
                for (int u: digraph.adj(v)) if (!marked.contains(u)) dfs(digraph, u);
            }
            
            public int count() {
                return count;
            }
            
            public int id(int v) {
                return ids[v];
            }
        }
    }
}

