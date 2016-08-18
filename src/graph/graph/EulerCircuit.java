package graph.graph;

import java.util.HashSet;
import java.util.Set;

import collections.Bag;
import collections.Queue;
import collections.Stack;
import collections.impl.bag.LinkedBag;
import collections.impl.queue.LinkedQueue;
import collections.impl.stack.LinkedStack;
import edu.princeton.cs.algs4.In;

public class EulerCircuit<Key> {
    
    private Graph<Key> graph;
    private Queue<Key> eulerCircuit;
    private Stack<Key> stack;
    private Set<Key> marked;
    private boolean hasEulerCircuit;
    
    public EulerCircuit(Graph<Key> graph) {
        this.graph = new Graph<>(graph);
        eulerCircuit = new LinkedQueue<>();
        stack = new LinkedStack<>();
        marked = new HashSet<>();
        
        hasEulerCircuit = checkHasEulerCircuit();
        
        if (hasEulerCircuit) for (Key v: graph.vertex()) if (!marked.contains(v) && eulerCircuit.size() == 0) dfs(v);
    }
    
    public boolean hasEulerCircuit() {
        return hasEulerCircuit;
    }
    
    private boolean checkHasEulerCircuit() {
        /**
         * 欧拉回路:
         * 1:  图G是连通的，不能有孤立点存在。
         * 2:  对于无向图来说度数为奇数的点个数为0;对于有向图来说每个点的入度必须等于出度。
         * 欧拉通路:
         * 1:  图G是连通的，无孤立点存在。
         * 2:  对于无向图来说，度数为奇数的的点可以有2个或者0个，并且这两个奇点其中一个为起点另外一个为终点。对于有向图来说，可以存在两个点，其入度不等于出度，其中一个入度比出度大1，为路径的起点；另外一个出度比入度大1，为路径的终点。
         */
        if (new ConnectedComponent<Key>(graph).count() != 1) return false;
        
        for (Key v: graph.vertex()) if (graph.adj(v) != null && graph.adj(v).size() % 2 != 0) {
            System.out.println(v + ": " + graph.adj(v));
            return false;
        }
        
        return true;
    }
    
    public Queue<Key> getEulerCircuit() {
        return eulerCircuit;
    }
    
    private void dfs(Key v) {
        marked.add(v);
        stack.push(v);
        
        Bag<Key> adj = new LinkedBag<>();
        
        for (Key w: graph.adj(v)) adj.add(w);
        for (Key w: adj) if (graph.hasEdge(v, w)) {
            graph.delete(v, w);
            dfs(w);
        }
        
        eulerCircuit.enqueue(stack.pop());
    }
    
    public static void main(String[] args) {
        In in = new In("data/euler-circuit.txt");
        
        Graph<Integer> graph = new Graph<>();
        in.readInt();
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        EulerCircuit<Integer> eulerCircuit = new EulerCircuit<>(graph);
        
        if (eulerCircuit.hasEulerCircuit())
            for (Integer key: eulerCircuit.getEulerCircuit())
                System.out.println(key);
        else System.out.println("doesn't has Euler Circuit");
    }

}
