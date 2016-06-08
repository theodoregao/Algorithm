package problems.ch2.section2;

import collections.LinkedQueue;
import collections.Queue;
import collections.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class _15_BottomUpMergeQueue<Item extends Comparable> {
    
    public void sort(Item[] comparables) {
        Queue<Queue<Item>> queues = new LinkedQueue<>();
        
        for (int i = 0; i < comparables.length; i++) {
            Queue<Item> queue = new LinkedQueue<Item>();
            queue.enqueue(comparables[i]);
            queues.enqueue(queue);
        }
        
        while (queues.size() > 1) queues.enqueue(merge(queues.dequeue(), queues.dequeue()));
        
        Queue<Item> queue = queues.dequeue();
        for (int i = 0; i < comparables.length; i++) comparables[i] = queue.dequeue();
    }
    
    private Queue<Item> merge(Queue<Item> p, Queue<Item> q) {
        Queue<Item> queue = new LinkedQueue<Item>();
        
        while (!p.isEmpty() && !q.isEmpty()) {
            queue.enqueue(p.peek().compareTo(q.peek()) < 0 ? p.dequeue() : q.dequeue());
        }
        while (!p.isEmpty()) queue.enqueue(p.dequeue());
        while (!q.isEmpty()) queue.enqueue(q.dequeue());
        return queue;
    }

    boolean less(Item a, Item b) {
        return a.compareTo(b) < 0;
    }
    
    boolean isSorted(Item[] comparables) {
        for (int i = 1; i < comparables.length; i++)
            if (less(comparables[i], comparables[i - 1]))
                return false;
        return true;
    }

    public static void main(String[] args) {
        int size = 1000000;
        int[] num = StdRandom.sample(0, size * 10, size);
//        Arrays.sort(num);
        Stopwatch stopwatch = new Stopwatch();
        Integer[] nums = new Integer[size];
        for (int i = 0; i < nums.length; i++) nums[i] = num[i];
        _15_BottomUpMergeQueue<Integer> sorter = new _15_BottomUpMergeQueue<Integer>();
        sorter.sort(nums);
        System.out.println(stopwatch.elapsedTime());
        System.out.println("sorted " + sorter.isSorted(nums));
    }
}
