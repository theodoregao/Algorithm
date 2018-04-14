package graph.digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphAlgos {
    
    static class Graph {
        
        private final Map<Integer, Set<Integer>> edges = new HashMap<>();
        
        public void addEdge(int v, int u) {
            if (!edges.containsKey(v)) edges.put(v, new HashSet<Integer>());
            if (!edges.containsKey(u)) edges.put(u, new HashSet<Integer>());
            edges.get(v).add(u);
            edges.get(u).add(v);
        }
        
        public Iterable<Integer> adj(int v) { return edges.get(v); }
        public Iterable<Integer> vertex() { return edges.keySet(); }
        public int size() { return edges.keySet().size(); }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(Graph.class.getSimpleName());
            sb.append("{");
            for (int v: vertex()) {
                sb.append("\n");
                sb.append(v + ": ");
                for (int u: adj(v)) sb.append(u + ", ");
            }
            sb.append("}");
            return sb.toString();
        }
    }
    
    static class Digraph {
        
        private final Map<Integer, Set<Integer>> edges = new HashMap<>();
        
        public Digraph reverse() {
            Digraph digraph = new Digraph();
            for (int v: vertex()) for (int u: adj(v)) digraph.addEdge(u, v);
            return digraph;
        }
        
        public void addEdge(int v, int u) {
            if (!edges.containsKey(v)) edges.put(v, new HashSet<Integer>());
            if (!edges.containsKey(u)) edges.put(u, new HashSet<Integer>());
            edges.get(v).add(u);
        }
        
        public Iterable<Integer> adj(int v) { return edges.get(v); }
        public Iterable<Integer> vertex() { return edges.keySet(); }
        public int size() { return edges.keySet().size(); }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(Digraph.class.getSimpleName());
            sb.append("{");
            for (int v: vertex()) {
                sb.append("\n");
                sb.append(v + ": ");
                for (int u: adj(v)) sb.append(u + ", ");
            }
            sb.append("}");
            return sb.toString();
        }
    }
    
    static class DfsOrder {
        private Set<Integer> marked;
        private Queue<Integer> pre;
        private Queue<Integer> post;
        private Stack<Integer> reversePost;
        
        public DfsOrder(Digraph digraph) {
            marked = new HashSet<>();
            pre = new LinkedList<>();
            post = new LinkedList<>();
            reversePost = new Stack<>();
            for (int v: digraph.vertex())
                if (!marked.contains(v)) dfs(digraph, v);
        }
        
        private void dfs(Digraph digraph, int v) {
            marked.add(v);
            pre.offer(v);
            for (int u: digraph.adj(v)) if (!marked.contains(u)) dfs(digraph, u);
            post.offer(v);
            reversePost.push(v);
        }

        public Iterable<Integer> pre() { return pre; }
        public Iterable<Integer> post() { return post; }
        public Iterable<Integer> reversePost() { return reversePost; }
    }
    
    static class Cycle {
        private Set<Integer> marked;
        private Stack<Integer> cycle;
        private Set<Integer> inCycle;
        
        public Cycle(Digraph digraph) {
            marked = new HashSet<>();
            inCycle = new HashSet<>();
            
            for (int v: digraph.vertex()) if (!marked.contains(v)) dfs(digraph, v);
        }
        
        private void dfs(Digraph digraph, int v) {
            marked.add(v);
            inCycle.add(v);
            for (int u: digraph.adj(v)) if (!marked.contains(u)) dfs(digraph, u);
            else if (inCycle.contains(u)) {
                cycle = new Stack<>();
                cycle.push(u);
                break;
            }
            inCycle.remove(v);
            if (cycle != null) cycle.push(v);
        }
        
        public boolean hasCycle() { return cycle != null; }
        public Stack<Integer> cycle() { return cycle; }
        public boolean isDag() { return cycle == null; }
    }
    
    static class TopologicalSort {
        private Iterable<Integer> order;
        
        public TopologicalSort(Digraph digraph) {
            if (new Cycle(digraph).isDag()) order = new DfsOrder(digraph.reverse()).reversePost();
        }
        
        public boolean hasTopologicalSort() { return order != null; }
        public Iterable<Integer> order() { return order; }
    }
    
    static class StrongConnectedComponent {
        private Set<Integer> marked;
        private int[] ids;
        private int count;
        
        public StrongConnectedComponent(Digraph digraph) {
            marked = new HashSet<>();
            ids = new int[digraph.size()];
            
            DfsOrder dfsOrder = new DfsOrder(digraph.reverse());
            for (int v: dfsOrder.reversePost()) if (!marked.contains(v)) {
                System.out.println("->" + v);
                dfs(digraph, v);
                count++;
            }
        }
        
        private void dfs(Digraph digraph, int v) {
            marked.add(v);
            ids[v] = count;
            for (int u: digraph.adj(v)) if (!marked.contains(u)) {
                dfs(digraph, u);
            }
        }
        
        public int count() { return count; }
        public int get(int v) { return ids[v]; }
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
        Digraph digraph = new Digraph();
        digraph.addEdge(5, 3);
        digraph.addEdge(4, 5);
        digraph.addEdge(4, 1);
        digraph.addEdge(3, 5);
        digraph.addEdge(0, 1);
        digraph.addEdge(2, 3);
        digraph.addEdge(2, 4);
        digraph.addEdge(1, 4);
        digraph.addEdge(1, 2);
        System.out.println(digraph.toString());
        System.out.println(digraph.reverse().toString());

        System.out.println();
        Cycle cycle = new Cycle(digraph);
        System.out.println(cycle.hasCycle());
//      for (int p: cycle.cycle()) System.out.println(p);
        
        System.out.println();
        DfsOrder dfsOrder = new DfsOrder(digraph);
        for (int p: dfsOrder.pre()) System.out.println(p);
        System.out.println();
        for (int p: dfsOrder.post()) System.out.println(p);
        System.out.println();
        for (int p: dfsOrder.reversePost()) System.out.println(p);
        
        System.out.println();
        System.out.println(new TopologicalSort(digraph).hasTopologicalSort());
//      for (int p: new TopologicalSort(digraph).order) System.out.println(p);
        
        System.out.println();
        StrongConnectedComponent strongConnectedComponent = new StrongConnectedComponent(digraph);
        System.out.println(strongConnectedComponent.count());
        for (int p: digraph.vertex()) System.out.println(p + ": " + strongConnectedComponent.get(p));
    }

}