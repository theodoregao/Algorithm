package problems.ch2.section2;

import java.util.Arrays;

import collections.impl.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class _18_ShufflingLinkedList<Item> {
    
    static class Node<Item> {
        Item item;
        Node<Item> next;
        
        Node() {
            this(null);
        }
        
        Node(Item item) {
            this(item, null);
        }
        
        Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
    
    public Node<Item> shuffling(Node<Item> head) {
        if (head == null) return null;
        if (head.next == null) return head;
        Node<Item> hp, hq, p, q;
        hp = p = new Node<Item>();
        hq = q = new Node<Item>();
        for (int i = 0; head != null; head = head.next, i++) {
            if (i % 2 == 0) { p.next = head; p = head; }
            else { q.next = head; q = head; }
        }
        p.next = q.next = null;
        hp = shuffling(hp.next);
        hq = shuffling(hq.next);
        return StdRandom.bernoulli() ? merge(hp, hq) : merge(hq, hp);
    }
    
    private Node<Item> merge(Node<Item> p, Node<Item> q) {
        
        Node<Item> head = new Node<Item>();
        Node<Item> node = head;
        
        int count = 0;
        while (p != null && q != null) {
            if (count++ %2 == 0) { node.next = p; p = p.next; }
            else { node.next = q; q = q.next; }
            node = node.next;
        }
        
        if (p != null) node.next = p;
        if (q != null) node.next = q;
        
        return head.next;
    }

    public static void main(String[] args) {
        int size = 1000;
        int[] num = StdRandom.sample(0, size, size);
        Arrays.sort(num);
        Stopwatch stopwatch = new Stopwatch();
        Node<Integer> nums = new Node<Integer>();
        Node<Integer> node = nums;
        for (int i = 0; i < num.length; i++) {
            node.next = new Node<Integer>(num[i]);
            node = node.next;
        }
        _18_ShufflingLinkedList<Integer> shuffling = new _18_ShufflingLinkedList<>();
        nums = shuffling.shuffling(nums.next);
        System.out.println(stopwatch.elapsedTime());
        
        while (nums != null) {
            System.out.println(nums.item);
            nums = nums.next;
        }
    }
}
