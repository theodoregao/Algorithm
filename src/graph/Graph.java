package graph;

import java.util.HashMap;
import java.util.Map;

import collections.Bag;
import collections.LinkedBag;

public class Graph<Key> {
    
    private static final int DEFAULT_VERTEX_SIZE = 16;
    
    private final Map<Key, Integer> vertex;
    private int edgeCount;
    private Bag<Key>[] adj;
    
    public Graph() {
        this(DEFAULT_VERTEX_SIZE);
    }

    public Graph(int vertexCount) {
        vertex = new HashMap<>();
        resize(vertexCount);
    }
    
    public int vertexCount() {
        return vertex.size();
    }
    
    public int edgeCount() {
        return edgeCount;
    }
    
    public void addEdge(Key v, Key w) {
        if (!vertex.containsKey(v)) {
            if (vertex.size() >= adj.length) resize(adj.length * 2);
            vertex.put(v, vertex.size());
        }
        if (!vertex.containsKey(w)) {
            if (vertex.size() >= adj.length) resize(adj.length * 2);
            vertex.put(w, vertex.size());
        }
        adj[vertex.get(v)].add(w);
        adj[vertex.get(w)].add(v);
        edgeCount++;
    }
    
    private void resize(int capacity) {
        Bag<Key>[] adj = new Bag[capacity];
        for (int i = 0; i < vertexCount(); i++) adj[i] = this.adj[i];
        for (int i = vertexCount(); i < capacity; i++) {
            adj[i] = new LinkedBag<>();
        }
        this.adj = adj;
    }
    
    public Iterable<Key> vertexes() {
        return vertex.keySet();
    }
    
    public Iterable<Key> adj(Key v) {
        if (!vertex.containsKey(v)) return new LinkedBag<Key>();
        return adj[vertex.get(v)];
    }
    
    public int getIndex(Key key) {
        return vertex.get(key);
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(Graph.class.getSimpleName());
        stringBuilder.append(": vertex: ").append(vertexCount())
                    .append(" edge: ").append(edgeCount).append(" {\n");
        for (Key v: vertex.keySet()) {
            stringBuilder.append("\t").append(v).append(": [");
            for (Key w: adj(v)) stringBuilder.append(w).append(", ");
            stringBuilder.append("],\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    
}
