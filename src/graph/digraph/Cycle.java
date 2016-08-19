package graph.digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import collections.Stack;
import collections.impl.stack.LinkedStack;

public class Cycle<Key> {

    private Set<Key> marked;
    private Stack<Key> cycle;
    private Set<Key> inCycle;

    public Cycle(Digraph<Key> digraph) {
        marked = new HashSet<>();
        inCycle = new HashSet<>();

        for (Key v : digraph.vertex())
            if (cycle == null && !marked.contains(v))
                dfs(digraph, v);
    }
    
    public boolean hasCycle() {
        return cycle != null;
    }
    
    public boolean isDag() {
        return cycle == null;
    }
    
    public Iterable<Key> cycle() {
        return cycle;
    }

    private void dfs(Digraph<Key> digraph, Key v) {
        if (cycle != null) return;
        marked.add(v);
        inCycle.add(v);

        for (Key w : digraph.adj(v))
            if (!marked.contains(w)) {
                dfs(digraph, w);
            } else if (inCycle.contains(w)) {
                cycle = new LinkedStack<>();
                cycle.push(w);
                break;
            }

        inCycle.remove(v);
        if (cycle != null) cycle.push(v);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/tinyDG2.txt"));
        Digraph<Integer> digraph = new Digraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) digraph.addEdge(scanner.nextInt(), scanner.nextInt());
        scanner.close();
        System.out.println(digraph);
        
        Cycle<Integer> cycle = new Cycle<>(digraph);
        System.out.println(cycle.hasCycle());
        if (cycle.hasCycle()) for (Integer v: cycle.cycle()) System.out.print(v + " ");
    }

}
