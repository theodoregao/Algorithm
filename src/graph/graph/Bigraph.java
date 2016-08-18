package graph.graph;

import edu.princeton.cs.algs4.In;

public class Bigraph<Key> {
    
    private boolean[] marked;
    private boolean[] color;
    private boolean isBigraph;
    
    public Bigraph(Graph<Key> graph) {
        marked = new boolean[graph.size()];
        color = new boolean[graph.size()];
        isBigraph = true;
        for (Key key: graph.keys()) if (!marked[graph.getIndex(key)] && isBigraph) dfs(graph, key);
    }
    
    public boolean isBigraph() {
        return isBigraph;
    }
    
    private void dfs(Graph<Key> graph, Key v) {
        if (!isBigraph) return;
        int index = graph.getIndex(v);
        marked[index] = true;
        for (Key w: graph.adj(v))
            if (!marked[graph.getIndex(w)]) {
                color[graph.getIndex(w)] = !color[index];
                dfs(graph, w);
            }
            else if (color[graph.getIndex(w)] == color[index]) {
                isBigraph = false;
                return;
            }
    }
    
    public static void main(String[] args) {
        In in = new In("data/tinyG.txt");
        
        Graph<Integer> graph = new Graph<>(in.readInt());
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        Bigraph<Integer> bigraph = new Bigraph<>(graph);
        System.out.println("is bigraph: " + bigraph.isBigraph());
    }

}
