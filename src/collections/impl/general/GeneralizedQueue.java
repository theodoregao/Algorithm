package collections.impl.general;

import java.util.Iterator;
import java.util.NoSuchElementException;

import collections.impl.LinkedNodeIterator;
import collections.impl.Node;

public class GeneralizedQueue<Item> implements Iterable<Item> {
    
    private Node<Item> first;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new LinkedNodeIterator<>(first);
    }
    
    public void insert(Item item) {
        first = new Node<>(item, first);
        size++;
    }
    
    public int find(Item item) {
        int position = 0;
        Node<Item> node = first;
        while (node != null) {
            if (node.item.equals(item)) return position;
            position++;
            node = node.next;
        }
        return -1;
    }
    
    public Item delete() {
        return delete(0);
    }
    
    public Item delete(int position) {
        if (position >= size) throw new NoSuchElementException();
        Node<Item> previous = null;
        Node<Item> current = first;
        for (int i = 0; i < position; i++) {
            previous = current;
            current = current.next;
        }
        if (previous != null) previous.next = current.next;
        else first = first.next;
        current.next = null;
        size--;
        return current.item;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }

}
