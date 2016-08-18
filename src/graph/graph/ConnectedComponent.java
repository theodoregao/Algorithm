package graph.graph;

import edu.princeton.cs.algs4.In;

public class ConnectedComponent<Key> {

    private Graph<Key> graph;
    private boolean[] marked;
    private int[] ids;
    private int count;
    
    public ConnectedComponent(Graph<Key> graph) {
        this.graph = graph;
        marked = new boolean[graph.size()];
        ids = new int[graph.size()];
        count = 0;
        
        for (Key v: graph.keys()) if (!marked[graph.getIndex(v)]) { count++; dfs(v); }
    }
    
    public int count() {
        return count;
    }
    
    public int id(Key v) {
        return ids[graph.getIndex(v)];
    }
    
    public boolean connected(Key v, Key w) {
        return id(v) == id(w) && id(v) != 0;
    }

    private void dfs(Key v) {
        int index = graph.getIndex(v);
        marked[index] = true;
        ids[index] = count;
        for (Key w: graph.adj(v)) if (!marked[graph.getIndex(w)]) dfs(w);
    }
    
    public static void main(String[] args) {
        In in = new In("data/tinyG.txt");
        
        Graph<Integer> graph = new Graph<>(in.readInt());
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        ConnectedComponent<Integer> cc = new ConnectedComponent<>(graph);
        System.out.println("connected component count: " + cc.count);
    }
}
