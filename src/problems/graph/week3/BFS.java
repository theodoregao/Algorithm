package problems.graph.week3;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BFS {
    
    private static class Graph {
        private Map<Integer, Set<Integer>> edges = new HashMap<>();
        private Set<Integer> marked = new HashSet<>();
        private int[] distances;
        
        public Graph(int n) {
            for (int i = 1; i <= n; i++) edges.put(i, new HashSet<Integer>());
            distances = new int[n + 1];
            for (int i = 0; i < distances.length; i++) distances[i] = Integer.MAX_VALUE;
        }
        
        public void addEdge(int v, int u) {
            if (!edges.containsKey(v)) edges.put(v, new HashSet<Integer>());
            if (!edges.containsKey(u)) edges.put(u, new HashSet<Integer>());
            edges.get(v).add(u);
            edges.get(u).add(v);
        }
        
        private Iterable<Integer> adj(int v) {
            return edges.get(v);
        }
        
        public void bfs(int v) {
            distances[v] = 0;
            marked.add(v);
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(v);
            while (!queue.isEmpty()) {
                v = queue.poll();
                for (int u: adj(v)) if (!marked.contains(u)) {
                    marked.add(u);
                    distances[u] = distances[v] + 1;
                    queue.offer(u);
                }
            } 
        }
        
        public int distance(int u) {
            return distances[u] == Integer.MAX_VALUE ? -1 : distances[u];
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
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        graph.bfs(x);
        System.out.println(graph.distance(y));
    }
}

