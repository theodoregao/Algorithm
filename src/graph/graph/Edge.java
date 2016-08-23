package graph.graph;

public class Edge<Key> implements Comparable<Edge<Key>> {
    private final Key v;
    private final Key w;
    private final double weight;
    
    public Edge(Key v, Key w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    public double weight() {
        return weight;
    }
    
    public Key either() {
        return v;
    }
    
    public Key other() {
        return w;
    }
    
    public Key other(Key vertex) {
        if (vertex.equals(v)) return w;
        else if (vertex.equals(w)) return v;
        else return null;
    }
    
    @Override
    public String toString() {
        return Edge.class.getSimpleName() + "{" + v + "-" + w + ": " + weight + "}";
    }

    @Override
    public int compareTo(Edge<Key> that) {
        if (weight() < that.weight()) return -1;
        else if (weight() > that.weight()) return 1;
        else return 0;
    }
    
}
