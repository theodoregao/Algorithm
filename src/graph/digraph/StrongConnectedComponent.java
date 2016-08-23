package graph.digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import collections.Map;
import collections.impl.st.LinearProbingHashST;
import collections.impl.st.SeparateChainingHashST;

public class StrongConnectedComponent<Key> {
    
    private Set<Key> marked;
    private Map<Key, Key> ids;
    private Map<Key, Set<Key>> sccs;
    
    public StrongConnectedComponent(Digraph<Key> digraph) {
        marked = new HashSet<>();
        ids = new LinearProbingHashST<>();
        sccs = new SeparateChainingHashST<>();
        
        for (Key v: new DfsOrder<>(digraph.reverse()).reversePost()) {
            if (!marked.contains(v)) {
                sccs.put(v, new HashSet<Key>());
                dfs(digraph, v, v);
            }
        }
    }
    
    public int count() {
        return sccs.size();
    }
    
    public Key id(Key v) {
        return ids.get(v);
    }
    
    public Map<Key, Set<Key>> sccs() {
        return sccs;
    }
    
    public boolean connected(Key v, Key w) {
        return id(v) != null && id(v).equals(id(w));
    }
    
    private void dfs(Digraph<Key> digraph, Key v, Key u) {
        marked.add(v);
        sccs.get(u).add(v);
        for (Key w: digraph.adj(v)) if (!marked.contains(w)) {
            ids.put(v, u);
            dfs(digraph, w, u);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/scc.txt"));
        Digraph<Integer> digraph = new Digraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) digraph.addEdge(scanner.nextInt(), scanner.nextInt());
        scanner.close();
        System.out.println(digraph);
        
        StrongConnectedComponent<Integer> scc = new StrongConnectedComponent<>(digraph);
        System.out.println("count: " + scc.count());
        for (Integer v: scc.sccs().keys()) {
            System.out.print(v + ": ");
            for (Integer w: scc.sccs.get(v)) System.out.print(w + " ");
            System.out.println();
        }
    }

}
