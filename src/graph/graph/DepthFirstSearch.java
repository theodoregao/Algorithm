package graph.graph;

import java.util.HashSet;
import java.util.Set;

import collections.Map;
import collections.Stack;
import collections.impl.st.LinearProbingHashST;
import collections.impl.stack.LinkedStack;
import edu.princeton.cs.algs4.In;

public class DepthFirstSearch<Key> {
    
    private Set<Key> marked;
    private Map<Key, Key> edgeFrom;
    private Key source;
    
    public DepthFirstSearch(Graph<Key> graph, Key source) {
        this.source = source;
        marked = new HashSet<>();
        edgeFrom = new LinearProbingHashST<>();
        dfs(graph, source);
    }
    
    private void dfs(Graph<Key> graph, Key v) {
        marked.add(v);
        for (Key w: graph.adj(v)) if (!marked.contains(w)) {
            edgeFrom.put(w, v);;
            dfs(graph, w);
        }
    }
    
    private boolean marked(Key v) {
        return marked.contains(v);
    }
    
    public boolean hasPathTo(Key v) {
        return marked(v);
    }
    
    public Iterable<Key> pathTo(Key v) {
        if (!hasPathTo(v)) return null;
        Stack<Key> path = new LinkedStack<Key>();
        for (Key x = v; !source.equals(x); x = edgeFrom.get(x)) path.push(x);
        path.push(source);
        return path;
    }
    
    public static void main(String[] args) {
        In in = new In("data/tinyG.txt");
        
        Graph<Integer> graph = new Graph<>();
        in.readInt();
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        DepthFirstSearch<Integer> dfs = new DepthFirstSearch<>(graph, 0);
        
        for (Integer v: graph.vertex()) {
            if (dfs.hasPathTo(v)) {
                System.out.println("path to " + v);
                for (Integer w: dfs.pathTo(v)) System.out.print(w + " ");
                System.out.println();
            }
        }
    }

}
