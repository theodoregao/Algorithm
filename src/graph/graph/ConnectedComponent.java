package graph.graph;

import java.util.Set;
import java.util.HashSet;

import collections.Map;
import collections.impl.st.SeparateChainingHashST;
import edu.princeton.cs.algs4.In;

public class ConnectedComponent<Key> {

    private Set<Key> marked;
    private Map<Key, Key> ids;
    private Map<Key, Set<Key>> ccs;
    
    public ConnectedComponent(Graph<Key> graph) {
        marked = new HashSet<>();
        ids = new SeparateChainingHashST<>();
        ccs = new SeparateChainingHashST<>();
        
        for (Key v: graph.vertex()) if (!marked.contains(v)) {
            ccs.put(v, new HashSet<Key>());
            dfs(graph, v, v);
        }
    }
    
    public int count() {
        return ccs.size();
    }
    
    public Key id(Key v) {
        return ids.get(v);
    }
    
    public Map<Key, Set<Key>> ccs() {
        return ccs;
    }
    
    public boolean connected(Key v, Key w) {
        return id(v) != null && id(v).equals(id(w));
    }

    private void dfs(Graph<Key> graph, Key v, Key u) {
        marked.add(v);
        ccs.get(u).add(v);
        ids.put(v, u);
        for (Key w: graph.adj(v)) if (!marked.contains(w)) dfs(graph, w, u);
    }
    
    public static void main(String[] args) {
        In in = new In("data/tinyG.txt");
        
        Graph<Integer> graph = new Graph<>();
        in.readInt();
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        ConnectedComponent<Integer> cc = new ConnectedComponent<>(graph);
        System.out.println("connected component count: " + cc.count());
        
        for (Integer set: cc.ccs().keys()) {
            System.out.print(set + ": ");
            for (Integer c: cc.ccs().get(set))
                System.out.print(c + " ");
            System.out.println();
        }
    }
}
