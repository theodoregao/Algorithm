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
         * ŷ����·:
         * 1:  ͼG����ͨ�ģ������й�������ڡ�
         * 2:  ��������ͼ��˵����Ϊ�����ĵ����Ϊ0;��������ͼ��˵ÿ�������ȱ�����ڳ��ȡ�
         * ŷ��ͨ·:
         * 1:  ͼG����ͨ�ģ��޹�������ڡ�
         * 2:  ��������ͼ��˵������Ϊ�����ĵĵ������2������0���������������������һ��Ϊ�������һ��Ϊ�յ㡣��������ͼ��˵�����Դ��������㣬����Ȳ����ڳ��ȣ�����һ����ȱȳ��ȴ�1��Ϊ·������㣻����һ�����ȱ���ȴ�1��Ϊ·�����յ㡣
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
