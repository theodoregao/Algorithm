package graph.graph;

import java.util.HashSet;
import java.util.Set;

import collections.Map;
import collections.impl.st.SeparateChainingHashST;
import edu.princeton.cs.algs4.In;

public class Bigraph<Key> {
    
    private Set<Key> marked;
    private Map<Key, Boolean> color;
    private boolean isBigraph;
    
    public Bigraph(Graph<Key> graph) {
        marked = new HashSet<>();
        color = new SeparateChainingHashST<>();
        isBigraph = true;
        for (Key v: graph.vertex()) if (!marked.contains(v) && isBigraph) dfs(graph, v);
    }
    
    public boolean isBigraph() {
        return isBigraph;
    }
    
    private void dfs(Graph<Key> graph, Key v) {
        if (!isBigraph) return;
        marked.add(v);
        if (!color.contains(v)) color.put(v, false);
        for (Key w: graph.adj(v))
            if (!marked.contains(w)) {
                color.put(w, !color.get(v));
                dfs(graph, w);
            }
            else if (color.get(w) == color.get(v)) {
                isBigraph = false;
                return;
            }
    }
    
    public static void main(String[] args) {
        In in = new In("data/tinyG.txt");
        
        Graph<Integer> graph = new Graph<>();
        in.readInt();
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        Bigraph<Integer> bigraph = new Bigraph<>(graph);
        System.out.println("is bigraph: " + bigraph.isBigraph());
    }

}
