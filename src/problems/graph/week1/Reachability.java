package problems.graph.week1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Reachability {
    
    private static class Graph {
        private Map<Integer, Set<Integer>> edges;
        private Set<Integer> visited;
        private int id;
        private int[] ids;
        
        public Graph(int n, int[][] adj) {
            edges = new HashMap<>();
            visited = new HashSet<>();
            for (int i = 0; i < n; i++) edges.put(i, new HashSet<Integer>());
            for (int[] edge: adj) {
                edges.get(edge[0]).add(edge[1]);
                edges.get(edge[1]).add(edge[0]);
            }
            
            ids = new int[n];
            
            for (int v = 0; v < n; v++) if (!visited.contains(v)) {
                dfs(v);
                id++;
            }
        }
        
        private void dfs(int v) {
            visited.add(v);
            ids[v] = id;
            for (int w: edges.get(v)) if (!visited.contains(w)) dfs(w);
        }
        
        public boolean connected(int v, int w) {
            return ids[v] == ids[w];
        }
        
        public int count() {
            return id;
        }
        
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] adj = new int[m][2];
        for (int i = 0; i < m; i++) {
            adj[i][0] = scanner.nextInt() - 1;
            adj[i][1] = scanner.nextInt() - 1;
        }
        Graph graph = new Graph(n, adj);
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(graph.connected(x, y) ? 1 : 0);
    }
}

