package graph.graph;

import java.util.HashSet;
import java.util.Set;

import collections.Map;
import collections.impl.st.LinearProbingHashST;

public class EdgeWeightedGraph<Key> {
    
    private final Map<Key, Set<Edge<Key>>> vertex;
    private int edgeCount;

    public EdgeWeightedGraph() {
        vertex = new LinearProbingHashST<>();
    }
    
    public EdgeWeightedGraph(EdgeWeightedGraph<Key> graph) {
        vertex = new LinearProbingHashST<>();
        for (Key v: graph.vertex())
            for (Edge<Key> edge: graph.adj(v))
                addEdge(edge);
    }
    
    public EdgeWeightedGraph<Key> copy() {
        return new EdgeWeightedGraph<>(this);
    }
    
    public int size() {
        return vertex.size();
    }
    
    public int edgeCount() {
        return edgeCount;
    }
    
    public void addEdge(Key v, Key w, double weight) {
        addEdge(new Edge<>(v, w, weight));
    }
    
    public void addEdge(Edge<Key> edge) {
        Key v = edge.either();
        Key w = edge.other(v);
        if (!vertex.contains(v)) vertex.put(v, new HashSet<Edge<Key>>());
        if (!vertex.contains(w)) vertex.put(w, new HashSet<Edge<Key>>());
        vertex.get(v).add(edge);
        vertex.get(w).add(edge);
        edgeCount++;
    }
    
    public boolean hasVertex(Key v) {
        return vertex.contains(v);
    }
    
    public boolean hasEdge(Key v, Key w) {
        return vertex.contains(v) && vertex.get(v).contains(w);
    }
    
    public Iterable<Key> vertex() {
        return vertex.keys();
    }
    
    public Set<Edge<Key>> adj(Key v) {
        if (!vertex.contains(v)) return new HashSet<Edge<Key>>();
        return vertex.get(v);
    }
    
    public Set<Edge<Key>> edges() {
        Set<Edge<Key>> edges = new HashSet<Edge<Key>>();
        for (Key v: vertex.keys()) edges.addAll(vertex.get(v));
        return edges;
    }
    
    public void delete(Key v) {
        Set<Edge<Key>> edges = vertex.get(v);
        for (Edge<Key> edge: edges) {
            vertex.get(edge.other(v)).remove(v);
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
    
    public void delete(Edge<Key> edge) {
        delete(edge.either(), edge.other());
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(EdgeWeightedGraph.class.getSimpleName());
        stringBuilder.append(": vertex: ").append(size()).append(" {\n");
        for (Key v: vertex.keys()) {
            stringBuilder.append("\t").append(v).append(": [");
            for (Edge<Key> edge: adj(v)) stringBuilder.append(edge).append(", ");
            stringBuilder.append("]\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    
}
