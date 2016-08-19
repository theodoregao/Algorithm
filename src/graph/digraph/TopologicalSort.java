package graph.digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TopologicalSort<Key> {
    
    private Iterable<Key> order;
    
    public TopologicalSort(Digraph<Key> digraph) {
        if (new Cycle<Key>(digraph).isDag()) {
            order = new DfsOrder<Key>(digraph).reversePost();
        }
    }
    
    public boolean hasTopologicalOrder() {
        return order != null;
    }
    
    public Iterable<Key> order() {
        return order;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/dag.txt"));
        Digraph<Integer> digraph = new Digraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) digraph.addEdge(scanner.nextInt(), scanner.nextInt());
        scanner.close();
        System.out.println(digraph);
        
        TopologicalSort<Integer> topologicalSort = new TopologicalSort<>(digraph);
        if (topologicalSort.hasTopologicalOrder()) for (Integer v: topologicalSort.order) System.out.print(v + " ");
    }

}
