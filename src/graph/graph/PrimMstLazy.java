package graph.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import collections.Bag;
import collections.impl.bag.LinkedBag;
import collections.impl.heap.Heap;

public class PrimMstLazy<Key> {
    
    private Set<Key> marked;
    private Bag<Edge<Key>> mst;
    private Heap<Edge<Key>> pq;
    
    public PrimMstLazy(EdgeWeightedGraph<Key> graph) {
        marked = new HashSet<>();
        mst = new LinkedBag<>();
        pq = new Heap<Edge<Key>>(new Comparator<Edge<Key>>() {

            @Override
            public int compare(Edge<Key> o1, Edge<Key> o2) {
                return o2.compareTo(o1);
            }
            
        });
        
        for (Key u: graph.vertex()) if (!marked.contains(u)) {
            visit(graph, u);
            while (!pq.isEmpty()) {
                Edge<Key> e = pq.deleteTop();
                Key v = e.either();
                Key w = e.other();
                if (marked.contains(v) && marked.contains(w)) continue;
                mst.add(e);
                if (!marked.contains(v)) visit(graph, v);
                if (!marked.contains(w)) visit(graph, w);
            }
        }
    }
    
    public Iterable<Edge<Key>> edges() {
        return mst;
    }
    
    public double weight() {
        double weight = 0;
        for (Edge<Key> e: mst) weight += e.weight();
        return weight;
    }
    
    private void visit(EdgeWeightedGraph<Key> graph, Key v) {
        marked.add(v);
        for (Edge<Key> e: graph.adj(v)) if (!marked.contains(e.other(v))) pq.insert(e);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/largeEWG.txt"));
        EdgeWeightedGraph<Integer> graph = new EdgeWeightedGraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) graph.addEdge(new Edge<Integer>(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble()));
        scanner.close();
        System.out.println(graph.edgeCount());
        
        PrimMstLazy<Integer> mst = new PrimMstLazy<>(graph);
        System.out.println(mst.weight());
        int edgeCount = 0;
        for (Edge<Integer> edge: mst.edges()) edgeCount++;
        System.out.println(edgeCount);
    }

}
