package graph.graph;

import edu.princeton.cs.algs4.In;

public class Cycle<Key> {

    private boolean[] marked;
    private boolean hasCycle;
    
    public Cycle(Graph<Key> graph) {
        marked = new boolean[graph.size()];
        for (Key v: graph.keys()) if (!marked[graph.getIndex(v)] && !hasCycle) dfs(graph, v, v);
    }
    
    private void dfs(Graph<Key> graph, Key v, Key source) {
        if (hasCycle) return;
        marked[graph.getIndex(v)] = true;
        for (Key w: graph.adj(v))
            if (!marked[graph.getIndex(w)]) dfs(graph, w, v);
            else if (!source.equals(w)) hasCycle = true;
    }
    
    public boolean hasCycle() {
        return hasCycle;
    }
    
    public static void main(String[] args) {
        In in = new In("data/tinyG.txt");
        
        Graph<Integer> graph = new Graph<>(in.readInt());
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        Cycle<Integer> cycle = new Cycle<Integer>(graph);
        System.out.println("has cycle: " + cycle.hasCycle);
    }
}
