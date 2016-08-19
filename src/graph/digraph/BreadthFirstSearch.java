package graph.digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import collections.Bag;
import collections.Map;
import collections.Queue;
import collections.Stack;
import collections.impl.bag.LinkedBag;
import collections.impl.queue.LinkedQueue;
import collections.impl.st.LinearProbingHashST;
import collections.impl.stack.LinkedStack;

public class BreadthFirstSearch<Key> {
    
    private Set<Key> marked;
    private Map<Key, Key> edgeFrom;
    private Set<Key> sources;
    
    public BreadthFirstSearch(Digraph<Key> digraph, Key source) {
        Bag<Key> sources = new LinkedBag<>();
        sources.add(source);
        init(digraph, sources);
    }
    
    public BreadthFirstSearch(Digraph<Key> digraph, Bag<Key> sources) {
        init(digraph, sources);
    }
    
    public BreadthFirstSearch(Digraph<Key> digraph, Key[] sources) {
        Bag<Key> bag = new LinkedBag<>();
        for (Key v: sources) bag.add(v);
        init(digraph, bag);
    }
    
    private void init(Digraph<Key> digraph, Bag<Key> sources) {
        marked = new HashSet<>();
        edgeFrom = new LinearProbingHashST<>();
        this.sources = new HashSet<>();
        for (Key v: sources) this.sources.add(v);
        bfs(digraph);
    }
    
    private boolean marked(Key v) {
        return marked.contains(v);
    }
    
    public boolean hasPathTo(Key v) {
        return marked(v);
    }
    
    public Iterable<Key> pathTo(Key v) {
        if (!hasPathTo(v)) return null;
        Stack<Key> path = new LinkedStack<>();
        for ( ; !sources.contains(v); v = edgeFrom.get(v)) path.push(v);
        path.push(v);
        return path;
    }
    
    private void bfs(Digraph<Key> digraph) {
        Queue<Key> queue = new LinkedQueue<>();
        for (Key v: sources) {
            marked.add(v);
            queue.enqueue(v);
        }
        
        while (!queue.isEmpty()) {
            Key v = queue.dequeue();
            for (Key w: digraph.adj(v)) if (!marked.contains(w)) {
                marked.add(w);
                queue.enqueue(w);
                edgeFrom.put(w, v);
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/tinyDG.txt"));
        Digraph<Integer> digraph = new Digraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) digraph.addEdge(scanner.nextInt(), scanner.nextInt());
        scanner.close();
        System.out.println(digraph);
        
        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(digraph, new Integer[] {0, 7, 9});
        for (Integer v: digraph.vertex()) if (bfs.hasPathTo(v)) {
            System.out.print(v + ": ");
            for (Integer w: bfs.pathTo(v)) System.out.print(w + " ");
            System.out.println();
        }
        
    }
}
