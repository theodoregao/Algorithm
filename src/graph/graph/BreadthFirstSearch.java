package graph.graph;

import java.util.HashSet;
import java.util.Set;

import collections.Map;
import collections.Queue;
import collections.Stack;
import collections.impl.queue.LinkedQueue;
import collections.impl.st.SeparateChainingHashST;
import collections.impl.stack.LinkedStack;
import edu.princeton.cs.algs4.In;

public class BreadthFirstSearch<Key> {
    
    private Set<Key> marked;
    private Map<Key, Key> edgeFrom;
    private Key source;
    
    public BreadthFirstSearch(Graph<Key> graph, Key source) {
        this.source = source;
        marked = new HashSet<>();
        edgeFrom = new SeparateChainingHashST<>();
        bfs(graph, source);
    }
    
    private void bfs(Graph<Key> graph, Key v) {
        marked.add(v);
        Queue<Key> queue = new LinkedQueue<Key>();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            Key vertex = queue.dequeue();
            for (Key w: graph.adj(vertex)) if (!marked.contains(w)) {
                marked.add(w);
                edgeFrom.put(w, vertex);
                queue.enqueue(w);
            }
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
        for ( ; !source.equals(v); v = edgeFrom.get(v)) path.push(v);
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
        
        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(graph, 0);
        
        for (Integer v: graph.vertex()) {
            if (bfs.hasPathTo(v)) {
                System.out.println("path to " + v);
                for (Integer w: bfs.pathTo(v)) System.out.print(w + " ");
                System.out.println();
            }
        }
    }

}
