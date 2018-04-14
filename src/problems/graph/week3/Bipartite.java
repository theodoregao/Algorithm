package problems.graph.week3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Bipartite {
    private static class Graph {
        private Map<Integer, Set<Integer>> edges = new HashMap<>();
        
        public Graph(int n) {
            for (int i = 1; i <= n; i++) edges.put(i, new HashSet<Integer>());
        }
        
        public void addEdge(int v, int u) {
            if (!edges.containsKey(v)) edges.put(v, new HashSet<Integer>());
            if (!edges.containsKey(u)) edges.put(u, new HashSet<Integer>());
            edges.get(v).add(u);
            edges.get(u).add(v);
        }
        
        public Iterable<Integer> vertex() {
            return edges.keySet();
        }
        
        public Iterable<Integer> adj(int v) {
            return edges.get(v);
        }
        
        public int size() {
            return edges.size();
        }
    }
    
    private static class Bigraph {
        private Set<Integer> marked = new HashSet<>();
        private boolean[] colors;
        private boolean isBigraph;
        
        public Bigraph(Graph graph) {
            isBigraph = graph.size() >= 2;
            colors = new boolean[graph.size() + 1];
            for (int v: graph.vertex()) if (isBigraph && !marked.contains(v)) bfs(graph, v);
        }
        
        private void bfs(Graph graph, int v) {
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(v);
            while (isBigraph && !queue.isEmpty()) {
                v = queue.poll();
                marked.add(v);
                for (int u: graph.adj(v)) if (!marked.contains(u)) {
                    colors[u] = !colors[v];
                    queue.offer(u);
                }
                else {
                    if (colors[v] == colors[u]) {
                        isBigraph = false;
                        return;
                    }
                }
            } 
        }
        
        public boolean isBigraph() {
            return isBigraph;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        System.out.println(new Bigraph(graph).isBigraph() ? 1 : 0);
    }
}

