package graph.digraph;

import java.util.HashSet;
import java.util.Set;

import collections.Bag;
import collections.Map;
import collections.impl.st.SeparateChainingHashST;

public class DepthFirstSearch<Key> {
    
    private Set<Key> marked;
    private Map<Key, Key> edgeFrom;
    private Bag<Key> sources;

    public DepthFirstSearch(Digraph<Key> digraph, Bag<Key> sources) {
        marked = new HashSet<Key>();
        edgeFrom = new SeparateChainingHashST<>();
        this.sources = sources;
        
        for (Key v: sources) if (!marked.contains(v)) dfs(digraph, v);
    }
    
    private void dfs(Digraph<Key> digraph, Key v) {
        
    }
    
}
