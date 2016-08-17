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
        In in = new In("data/mediumG.txt");
        
        Graph<Integer> vertex = new Graph<>(in.readInt());
        in.readInt();
        
        while (in.hasNextLine()) vertex.addEdge(in.readInt(), in.readInt());
        System.out.println(vertex.toString());
        
        DepthFirstSearch<Integer> dfs = new DepthFirstSearch<>(vertex, 0);
        System.out.println(dfs.count);
        
        for (Integer key: vertex.vertexes()) System.out.println("0 has path to " + key + " : " + dfs.hasPathTo(key));
    }

}
