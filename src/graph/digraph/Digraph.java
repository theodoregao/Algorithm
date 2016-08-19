package graph.digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import collections.Map;
import collections.impl.st.LinearProbingHashST;

public class Digraph<Key> {
    
    private final Map<Key, Set<Key>> vertex;
    private int edgeCount;

    public Digraph() {
        vertex = new LinearProbingHashST<>();
    }
    
    public Digraph(Digraph<Key> digraph) {
        vertex = new LinearProbingHashST<>();
        for (Key v: digraph.vertex()) for (Key w: digraph.adj(v)) addEdge(v, w);
    }
    
    public Digraph<Key> copy() {
        return new Digraph<>(this);
    }
    
    public Digraph<Key> reverse() {
        Digraph<Key> digraph = new Digraph<>();
        for (Key v: vertex()) for (Key w: adj(v)) digraph.addEdge(w, v);
        return digraph;
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
    
    public Set<Key> adj(Key v) {
        if (!vertex.contains(v)) return new HashSet<Key>();
        return vertex.get(v);
    }
    
    public void delete(Key v) {
        Set<Key> edges = vertex.get(v);
        edgeCount -= edges.size();
        vertex.delete(v);
    }
    
    public void delete(Key v, Key w) {
        if (vertex.contains(v) && vertex.contains(w)) {
            vertex.get(v).remove(w);
            edgeCount--;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(Digraph.class.getSimpleName());
        stringBuilder.append(": vertex: ").append(size()).append(" {\n");
        for (Key v: vertex.keys()) {
            stringBuilder.append("\t").append(v).append(": [");
            for (Key w: adj(v)) stringBuilder.append(w).append(", ");
            stringBuilder.append("]\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/tinyDG.txt"));
        Digraph<Integer> digraph = new Digraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) digraph.addEdge(scanner.nextInt(), scanner.nextInt());
        scanner.close();
        System.out.println(digraph);
        System.out.println(digraph.reverse());
    }
    
}
