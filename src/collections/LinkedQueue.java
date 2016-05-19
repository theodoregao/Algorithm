package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Queue<Item> {
    
    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    @Override
    public void enqueue(Item item) {
        Node<Item> newNode = new Node<Item>(item, null);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            last.next = newNode;
            last = newNode;
        }
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

}
