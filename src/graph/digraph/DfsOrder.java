package graph.digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import collections.Queue;
import collections.Stack;
import collections.impl.queue.LinkedQueue;
import collections.impl.stack.LinkedStack;

public class DfsOrder<Key> {
    private Set<Key> marked;
    private Queue<Key> pre;
    private Queue<Key> post;
    private Stack<Key> reversePost;
    
    public DfsOrder(Digraph<Key> digraph) {
        marked = new HashSet<>();
        pre = new LinkedQueue<>();
        post = new LinkedQueue<>();
        reversePost = new LinkedStack<>();
        
        for (Key v: digraph.vertex()) if (!marked.contains(v)) dfs(digraph, v);
    }
    
    private void dfs(Digraph<Key> digraph, Key v) {
        marked.add(v);
        
        pre.enqueue(v);
        for (Key w: digraph.adj(v)) if (!marked.contains(w)) dfs(digraph, w);
        post.enqueue(v);
        reversePost.push(v);
    }
    
    public Iterable<Key> pre() { return pre; }
    public Iterable<Key> post() { return post; }
    public Iterable<Key> reversePost() { return reversePost; }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("data/tinyDG.txt"));
        Digraph<Integer> digraph = new Digraph<>();
        scanner.nextInt();
        scanner.nextInt();
        while (scanner.hasNextInt()) digraph.addEdge(scanner.nextInt(), scanner.nextInt());
        scanner.close();
        System.out.println(digraph);
        
        DfsOrder<Integer> dfsOrder = new DfsOrder<>(digraph);
        for (Integer v: dfsOrder.pre()) System.out.print(v + " ");
        System.out.println();
        for (Integer v: dfsOrder.post()) System.out.print(v + " ");
        System.out.println();
        for (Integer v: dfsOrder.reversePost()) System.out.print(v + " ");
    }
}