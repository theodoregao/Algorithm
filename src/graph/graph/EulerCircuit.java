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
    
    private GraphDeletable<Key> graph;
    private Queue<Key> eulerCircuit;
    private Stack<Key> stack;
    private Set<Key> marked;
    
    public EulerCircuit(GraphDeletable<Key> graph) {
        this.graph = new GraphDeletable<>(graph);
        eulerCircuit = new LinkedQueue<>();
        stack = new LinkedStack<>();
        marked = new HashSet<>();
        
        for (Key v: graph.keys()) if (!marked.contains(v) && eulerCircuit.size() == 0) dfs(v);
    }
    
    public boolean hasEulerCircuit() {
        /**
         * ŷ����·:
         * 1:  ͼG����ͨ�ģ������й�������ڡ�
         * 2:  ��������ͼ��˵����Ϊ�����ĵ����Ϊ0;��������ͼ��˵ÿ�������ȱ�����ڳ��ȡ�
         * ŷ��ͨ·:
         * 1:  ͼG����ͨ�ģ��޹�������ڡ�
         * 2:  ��������ͼ��˵������Ϊ�����ĵĵ������2������0���������������������һ��Ϊ�������һ��Ϊ�յ㡣��������ͼ��˵�����Դ��������㣬����Ȳ����ڳ��ȣ�����һ����ȱȳ��ȴ�1��Ϊ·������㣻����һ�����ȱ���ȴ�1��Ϊ·�����յ㡣
         */
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
        
        GraphDeletable<Integer> graph = new GraphDeletable<>();
        in.readInt();
        in.readInt();
        
        while (in.hasNextLine()) graph.addEdge(in.readInt(), in.readInt());
        System.out.println(graph.toString());
        
        EulerCircuit<Integer> eulerCircuit = new EulerCircuit<Integer>(graph);
        System.out.println(eulerCircuit.getEulerCircuit() != null);
        
        if (eulerCircuit.getEulerCircuit() != null)
            for (Integer key: eulerCircuit.getEulerCircuit())
                System.out.println(key);
    }

}
