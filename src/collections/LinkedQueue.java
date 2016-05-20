package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Queue<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;
    
    public LinkedQueue() {
        
    }
    
    public LinkedQueue(Queue<Item> queue) {
        for (Item item: queue) enqueue(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedNodeIterator<Item>(first);
    }

    @Override
    public void enqueue(Item item) {
        if (first == null) first = last = new Node<Item>(item, null);
        else last = last.next = new Node<Item>(item, null);
        size++;
    }

    @Override
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (size == 1) last = null;
        size--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedQueue<Integer>();
        for (int i = 0; i < 10; i++) queue.enqueue(i);
        for (int i : queue) System.out.println(i);
        while (!queue.isEmpty()) System.out.println(queue.dequeue());
    }

}
