package graph.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

import collections.Bag;
import collections.Map;
import collections.impl.bag.LinkedBag;
import collections.impl.general.UnionFind;
import collections.impl.heap.Heap;
import collections.impl.st.LinearProbingHashST;

public class KruskalMst<Key> {
    
    private Bag<Edge<Key>> mst;
    private Heap<Edge<Key>> pq;
    private UnionFind uf;
    private Map<Key, Integer> index;
    
    public KruskalMst(EdgeWeightedGraph<Key> graph) {
        mst = new LinkedBag<>();
        pq = new Heap<Edge<Key>>(new Comparator<Edge<Key>>() {

            @Override
            public int compare(Edge<Key> o1, Edge<Key> o2) {
                return o2.compareTo(o1);
            }
            
        });
        
        for (Edge<Key> edge: graph.edges()) pq.insert(edge);
        index = new LinearProbingHashST<>();
        for (Key v: graph.vertex()) index.put(v, index.size());
        uf = new UnionFind(index.size());
        
        while (!pq.isEmpty() && uf.count() > 1) {
            Edge<Key> edge = pq.deleteTop();
            Key v = edge.either();
            Key w = edge.other();
            if (!uf.connected(index.get(v), index.get(w))) {
                uf.union(index.get(v), index.get(w));
                mst.add(edge);
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
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/largeEWG.txt"));
        EdgeWeightedGraph<Integer> graph = new EdgeWeightedGraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) graph.addEdge(new Edge<Integer>(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble()));
        scanner.close();
        System.out.println(graph.edgeCount());

        long timestamp = System.currentTimeMillis();
        KruskalMst<Integer> mst = new KruskalMst<>(graph);
        System.out.println(mst.weight());
        int edgeCount = 0;
        for (Edge<Integer> edge: mst.edges()) edgeCount++;
        System.out.println(edgeCount);
        System.out.println(System.currentTimeMillis() - timestamp);
    }

}
