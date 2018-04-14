package graph.digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
    
    static class Digraph {
        
        private final Map<Integer, Set<Integer>> edges = new HashMap<>();
        
        public Digraph reverse() {
            Digraph digraph = new Digraph();
            for (int p: vertex()) for (int q: adj(p)) digraph.addEdge(q, p);
            return digraph;
        }
        
        public void addEdge(int p, int q) {
            if (!edges.containsKey(p)) edges.put(p, new HashSet<Integer>());
            if (!edges.containsKey(q)) edges.put(q, new HashSet<Integer>());
            edges.get(p).add(q);
        }
        
        public Iterable<Integer> adj(int p) { return edges.get(p); }
        public Iterable<Integer> vertex() { return edges.keySet(); }
        public int size() { return edges.keySet().size(); }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(Digraph.class.getSimpleName());
            sb.append("{");
            for (int p: vertex()) {
                sb.append("\n");
                sb.append(p + ": ");
                for (int q: adj(p)) sb.append(q + ", ");
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
            for (int p: digraph.vertex())
                if (!marked.contains(p)) dfs(digraph, p);
        }
        
        private void dfs(Digraph digraph, int p) {
            marked.add(p);
            pre.offer(p);
            for (int q: digraph.adj(p)) if (!marked.contains(q)) dfs(digraph, q);
            post.offer(p);
            reversePost.push(p);
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
            
            for (int p: digraph.vertex()) if (!marked.contains(p)) dfs(digraph, p);
        }
        
        private void dfs(Digraph digraph, int p) {
            marked.add(p);
            inCycle.add(p);
            for (int q: digraph.adj(p)) if (!marked.contains(q)) dfs(digraph, q);
            else if (inCycle.contains(q)) {
                cycle = new Stack<>();
                cycle.push(q);
                break;
            }
            inCycle.remove(p);
            if (cycle != null) cycle.push(p);
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
            for (int p: dfsOrder.reversePost()) if (!marked.contains(p)) {
                System.out.println("->" + p);
                dfs(digraph, p);
                count++;
            }
        }
        
        private void dfs(Digraph digraph, int p) {
            marked.add(p);
            ids[p] = count;
            for (int q: digraph.adj(p)) if (!marked.contains(q)) {
                dfs(digraph, q);
            }
        }
        
        public int count() { return count; }
        public int get(int p) { return ids[p]; }
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