package graph.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import collections.Bag;
import collections.Map;
import collections.impl.bag.LinkedBag;
import collections.impl.heap.IndexHeap;
import collections.impl.st.LinearProbingHashST;

// Not working yet
public class PrimMst<Key> {
    
    private Set<Key> marked;
    private Bag<Edge<Key>> mst;
    private Map<Key, Edge<Key>> candidates;
    private IndexHeap<Edge<Key>> pq;
    
    public PrimMst(EdgeWeightedGraph<Key> graph) {
        marked = new HashSet<>();
        mst = new LinkedBag<>();
        candidates = new LinearProbingHashST<>();
        pq = new IndexHeap<Edge<Key>>(new Comparator<Edge<Key>>() {

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
        for (Edge<Key> e: graph.adj(v)) {
            Key w = e.other(v);
            if (!marked.contains(w)) {
                if (!candidates.contains(w)) {
                    candidates.put(w, e);
                    pq.insert(e);
                }
                else if (candidates.get(w).compareTo(e) > 0) {
                    pq.delete(candidates.get(w));
                    candidates.put(w, e);
                    pq.insert(e);
                }
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/largeEWG.txt"));
        EdgeWeightedGraph<Integer> graph = new EdgeWeightedGraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) graph.addEdge(new Edge<Integer>(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble()));
        scanner.close();
        System.out.println(graph.edgeCount());
        
        long timestamp = System.currentTimeMillis();
        PrimMst<Integer> mst = new PrimMst<>(graph);
        System.out.println(mst.weight());
        int edgeCount = 0;
        for (Edge<Integer> edge: mst.edges()) edgeCount++;
        System.out.println(edgeCount);
        System.out.println(System.currentTimeMillis() - timestamp);
    }

}
