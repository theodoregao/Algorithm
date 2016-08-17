package graph;

import edu.princeton.cs.algs4.In;

public class DepthFirstSearch<Key> {
    
    private Graph<Key> graph;
    private boolean[] marked;
    private Key[] edgeFrom;
    private int count;
    
    public DepthFirstSearch(Graph<Key> graph, Key source) {
        marked = new boolean[graph.vertexCount()];
        edgeFrom = (Key[]) new Object[graph.vertexCount()];
        this.graph = graph;
        dfs(source);
    }
    
    private void dfs(Key v) {
        marked[graph.getIndex(v)] = true;
        count++;
        for (Key w: graph.adj(v))
            if (!marked[graph.getIndex(w)]) {
                edgeFrom[graph.getIndex(w)] = v;
                dfs(w);
            }
    }
    
    private boolean marked(Key v) {
        return marked[graph.getIndex(v)];
    }
    
    public boolean hasPathTo(Key v) {
        return marked(v);
    }
    
    public int count() {
        return count;
    }
    
    public static void main(String[] args) {
        In in = new In("data/cities.txt");
        Graph<String> cities = new Graph<>();
        while (in.hasNextLine()) cities.addEdge(in.readString(), in.readString());
        System.out.println(cities.toString());
        
        DepthFirstSearch<String> dfs = new DepthFirstSearch<>(cities, "CA");
        System.out.println(dfs.count);
        
        for (String city: cities.vertexes()) System.out.println("CA has path to " + city + " : " + dfs.hasPathTo(city));
    }

}
