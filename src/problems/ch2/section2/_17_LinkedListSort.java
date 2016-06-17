package problems.ch2.section2;

import collections.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class _17_LinkedListSort<Item extends Comparable> {
    
//    public Node<Item> sort(Node<Item> head) {
//        if (head == null) return null;
//        if (head.next == null) return head;
//        Node<Item> hp, hq, p, q;
//        hp = p = new Node<Item>();
//        hq = q = new Node<Item>();
//        for (int i = 0; head != null; head = head.next, i++) {
//            if (i % 2 == 0) { p.next = head; p = head; }
//            else { q.next = head; q = head; }
//        }
//        p.next = q.next = null;
//        hp = sort(hp.next);
//        hq = sort(hq.next);
//        return merge(hp, hq);
//    }
    
    public Node<Item> sort(Node<Item> head) {
        if (head == null) return null;
        if (head.next == null) return head;
        Node<Item> hp, hq, hqPre;
        int count = 0;
        hp = head;
        hq = head;
        hqPre = null;
        
        while (head != null) {
            if (count++ % 2 == 0) { hqPre = hq; hq = hq.next; }
            head = head.next;
        }
        
        hqPre.next = null;
        hp = sort(hp);
        hq = sort(hq);
        return merge(hp, hq);
    }
    
    private Node<Item> merge(Node<Item> p, Node<Item> q) {
        
        Node<Item> head = new Node<Item>();
        Node<Item> node = head;
        
        while (p != null && q != null) {
            if (less(p.item, q.item)) { node.next = p; p = p.next; }
            else { node.next = q; q = q.next; }
            node = node.next;
        }
        
        if (p != null) node.next = p;
        if (q != null) node.next = q;
        
        return head.next;
    }
    
    boolean less(Item a, Item b) {
        return a.compareTo(b) < 0;
    }
    
    boolean isSorted(Node<Item> nums) {
        int n = 1;
        Item item = nums.item;
        nums = nums.next;
        while (nums != null) {
            if (less(nums.item, item)) return false;
            item = nums.item;
            nums = nums.next;
            n++;
        }
        System.out.println("size of array: " + n);
        return true;
    }

    public static void main(String[] args) {
        int size = 999999;
        int[] num = StdRandom.sample(0, size * 10, size);
        Stopwatch stopwatch = new Stopwatch();
        Node<Integer> nums = new Node<Integer>();
        Node<Integer> node = nums;
        for (int i = 0; i < num.length; i++) {
            node.next = new Node<Integer>(num[i]);
            node = node.next;
        }
        _17_LinkedListSort<Integer> sorter = new _17_LinkedListSort<>();
        nums = sorter.sort(nums.next);
        System.out.println(stopwatch.elapsedTime());
        System.out.println("sorted " + sorter.isSorted(nums));
    }
}
