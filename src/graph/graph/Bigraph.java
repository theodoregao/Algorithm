package graph.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
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
        for (Key v: graph.vertex()) if (!marked.contains(v) && isBigraph) bfs(graph, v);
    }
    
    public boolean isBigraph() {
        return isBigraph;
    }
    
    private void bfs(Graph<Key> graph, Key v) {
        Queue<Key> queue = new LinkedList<>();
        queue.add(v);
        while (isBigraph && !queue.isEmpty()) {
            v = queue.poll();
            marked.add(v);
            for (Key u: graph.adj(v)) if (!marked.contains(u)) {
                color.put(u, !color.get(v));
                queue.offer(u);
            }
            else {
                if (color.get(v) == color.get(u)) {
                    isBigraph = false;
                    return;
                }
            }
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
