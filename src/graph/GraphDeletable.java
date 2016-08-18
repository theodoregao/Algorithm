package graph;

import java.util.HashSet;
import java.util.Set;

import collections.Map;
import collections.impl.st.LinearProbingHashST;

public class GraphDeletable<Key> {
    
    private final Map<Key, Set<Key>> vertex;
    private int edgeCount;

    public GraphDeletable() {
        vertex = new LinearProbingHashST<>();
    }
    
    public GraphDeletable(GraphDeletable<Key> graph) {
        vertex = new LinearProbingHashST<>();
        for (Key v: graph.keys())
            for (Key w: graph.adj(v))
                addEdge(v, w);
    }
    
    public GraphDeletable<Key> copy(GraphDeletable<Key> graph) {
        return new GraphDeletable<>(this);
    }
    
    public int size() {
        return vertex.size();
    }
    
    public int edgeCount() {
        return edgeCount;
    }
    
    public void addEdge(Key v, Key w) {
        if (!vertex.contains(v)) vertex.put(v, new HashSet<Key>());
        if (!vertex.contains(w)) vertex.put(w, new HashSet<Key>());
        vertex.get(v).add(w);
        vertex.get(w).add(v);
        edgeCount++;
    }
    
    public boolean hasEdge(Key v, Key w) {
        return vertex.contains(v) && vertex.get(v).contains(w);
    }
    
    public Iterable<Key> keys() {
        return vertex.keys();
    }
    
    public Iterable<Key> adj(Key v) {
        if (!vertex.contains(v)) return new HashSet<Key>();
        return vertex.get(v);
    }
    
    public void delete(Key v) {
        Set<Key> edges = vertex.get(v);
        for (Key w: edges) {
            vertex.get(w).remove(v);
            edgeCount--;
        }
        vertex.delete(v);
    }
    
    public void delete(Key v, Key w) {
        if (vertex.contains(v) && vertex.contains(w)) {
            vertex.get(v).remove(w);
            vertex.get(w).remove(v);
            edgeCount--;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(GraphDeletable.class.getSimpleName());
        stringBuilder.append(": vertex: ").append(size()).append(" {\n");
        for (Key v: vertex.keys()) {
            stringBuilder.append("\t").append(v).append(": [");
            for (Key w: adj(v)) stringBuilder.append(w).append(", ");
            stringBuilder.append("]\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    
}
