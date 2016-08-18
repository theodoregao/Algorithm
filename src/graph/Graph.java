package graph;

import collections.Bag;
import collections.Map;
import collections.impl.bag.LinkedBag;
import collections.impl.st.LinearProbingHashST;

public class Graph<Key> {
    
    private static final int DEFAULT_VERTEX_SIZE = 16;
    
    private final Map<Key, Integer> vertex;
    private int edgeCount;
    private Bag<Key>[] adj;
    
    public Graph() {
        this(DEFAULT_VERTEX_SIZE);
    }

    public Graph(int vertexCount) {
        vertex = new LinearProbingHashST<>();
        resize(vertexCount);
    }
    
    public int size() {
        return vertex.size();
    }
    
    public int edgeCount() {
        return edgeCount;
    }
    
    public void addEdge(Key v, Key w) {
        if (!vertex.contains(v)) {
            if (vertex.size() >= adj.length) resize(adj.length * 2);
            vertex.put(v, vertex.size());
        }
        if (!vertex.contains(w)) {
            if (vertex.size() >= adj.length) resize(adj.length * 2);
            vertex.put(w, vertex.size());
        }
        adj[vertex.get(v)].add(w);
        adj[vertex.get(w)].add(v);
        edgeCount++;
    }
    
    private void resize(int capacity) {
        Bag<Key>[] adj = new Bag[capacity];
        for (int i = 0; i < size(); i++) adj[i] = this.adj[i];
        for (int i = size(); i < capacity; i++) {
            adj[i] = new LinkedBag<>();
        }
        this.adj = adj;
    }
    
    public Iterable<Key> keys() {
        return vertex.keys();
    }
    
    public Iterable<Key> adj(Key v) {
        if (!vertex.contains(v)) return new LinkedBag<Key>();
        return adj[vertex.get(v)];
    }
    
    public int getIndex(Key key) {
        return vertex.get(key);
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(Graph.class.getSimpleName());
        stringBuilder.append(": vertex: ").append(size())
                    .append(" edge: ").append(edgeCount).append(" {\n");
        for (Key v: vertex.keys()) {
            stringBuilder.append("\t").append(v).append(": [");
            for (Key w: adj(v)) stringBuilder.append(w).append(", ");
            stringBuilder.append("]\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    
}
