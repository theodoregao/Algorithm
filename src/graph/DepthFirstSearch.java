package graph;

import collections.LinkedStack;
import collections.Stack;
import edu.princeton.cs.algs4.In;

public class DepthFirstSearch<Key> {
    
    private Graph<Key> graph;
    private boolean[] marked;
    private Key source;
    private Key[] edgeFrom;
    
    public DepthFirstSearch(Graph<Key> graph, Key source) {
        this.source = source;
        this.graph = graph;
        marked = new boolean[graph.size()];
        edgeFrom = (Key[]) new Object[graph.size()];
        dfs(source);
    }
    
    private void dfs(Key v) {
        marked[graph.getIndex(v)] = true;
        for (Key w: graph.adj(v)) if (!marked[graph.getIndex(w)]) {
            edgeFrom[graph.getIndex(w)] = v;
            dfs(w);
        }
    }
    
    private boolean marked(Key v) {
        return marked[graph.getIndex(v)];
    }
    
    public boolean hasPathTo(Key v) {
        return marked(v);
    }
    
    public Iterable<Key> pathTo(Key v) {
        if (!hasPathTo(v)) return null;
        Stack<Key> path = new LinkedStack<Key>();
        for (Key x = v; !source.equals(x); x = edgeFrom[graph.getIndex(x)]) path.push(x);
        path.push(source);
        return path;
    }
    
    public static void main(String[] args) {
        In in = new In("data/tinyG.txt");
        
        Graph<Integer> graph = new Graph<>(in.readInt());
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        DepthFirstSearch<Integer> dfs = new DepthFirstSearch<>(graph, 0);
        
        for (Integer v: graph.keys()) {
            if (dfs.hasPathTo(v)) {
                System.out.println("path to " + v);
                for (Integer w: dfs.pathTo(v)) System.out.print(w + " ");
                System.out.println();
            }
        }
    }

}
