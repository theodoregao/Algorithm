package graph.digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import collections.Bag;
import collections.Map;
import collections.Stack;
import collections.impl.bag.LinkedBag;
import collections.impl.st.SeparateChainingHashST;
import collections.impl.stack.LinkedStack;

public class DepthFirstSearch<Key> {
    
    private Set<Key> marked;
    private Map<Key, Key> edgeFrom;
    private Set<Key> sources;
    
    public DepthFirstSearch(Digraph<Key> digraph, Key source) {
        Bag<Key> sources = new LinkedBag<>();
        sources.add(source);
        init(digraph, sources);
    }
    
    public DepthFirstSearch(Digraph<Key> digraph, Key[] sources) {
        Bag<Key> bag = new LinkedBag<>();
        for (Key v: sources) bag.add(v);
        init(digraph, bag);
    }

    public DepthFirstSearch(Digraph<Key> digraph, Bag<Key> sources) {
        init(digraph, sources);
    }
    
    private void init(Digraph<Key> digraph, Bag<Key> sources) {
        marked = new HashSet<Key>();
        edgeFrom = new SeparateChainingHashST<>();
        this.sources = new HashSet<>();
        
        for (Key v: sources) {
            this.sources.add(v);
            edgeFrom.put(v, v);
            if (!marked.contains(v)) dfs(digraph, v);
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
        Stack<Key> path = new LinkedStack<>();
        for ( ; !sources.contains(v); v = edgeFrom.get(v)) path.push(v);
        path.push(v);
        return path;
    }
    
    private void dfs(Digraph<Key> digraph, Key v) {
        marked.add(v);
        
        for (Key w: digraph.adj(v)) if (!marked.contains(w)) {
            edgeFrom.put(w, v);
            dfs(digraph, w);
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
        
        DepthFirstSearch<Integer> dfs = new DepthFirstSearch<>(digraph, new Integer[] {0, 9});
        for (Integer v: digraph.vertex()) if (dfs.hasPathTo(v)) {
            System.out.print(v + ": ");
            for (Integer w: dfs.pathTo(v)) System.out.print(w + " ");
            System.out.println();
        }
        
    }
}
