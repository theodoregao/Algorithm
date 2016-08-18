package graph.graph;

import collections.Queue;
import collections.Stack;
import collections.impl.queue.LinkedQueue;
import collections.impl.stack.LinkedStack;
import edu.princeton.cs.algs4.In;

public class BreadthFirstSearch<Key> {
    
    private Graph<Key> graph;
    private boolean[] marked;
    private Key source;
    private Key[] edgeFrom;
    
    public BreadthFirstSearch(Graph<Key> graph, Key source) {
        this.source = source;
        this.graph = graph;
        marked = new boolean[graph.size()];
        edgeFrom = (Key[]) new Object[graph.size()];
        bfs(source);
    }
    
    private void bfs(Key v) {
        marked[graph.getIndex(v)] = true;
        Queue<Key> queue = new LinkedQueue<Key>();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            Key vertex = queue.dequeue();
            for (Key w: graph.adj(vertex)) if (!marked[graph.getIndex(w)]) {
                marked[graph.getIndex(w)] = true;
                edgeFrom[graph.getIndex(w)] = vertex;
                queue.enqueue(w);
            }
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
        
        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(graph, 0);
        
        for (Integer v: graph.keys()) {
            if (bfs.hasPathTo(v)) {
                System.out.println("path to " + v);
                for (Integer w: bfs.pathTo(v)) System.out.print(w + " ");
                System.out.println();
            }
        }
    }

}
