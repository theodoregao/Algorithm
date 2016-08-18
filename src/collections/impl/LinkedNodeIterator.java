package collections.impl;

import java.util.Iterator;

public class LinkedNodeIterator<Item> implements Iterator<Item> {
    
    private Node<Item> current;
    private int size;
    
    public LinkedNodeIterator(Node<Item> current) {
        this(current,  Integer.MAX_VALUE);
    }
    
    public LinkedNodeIterator(Node<Item> current, int size) {
        this.current = current;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return current != null && size > 0;
    }

    @Override
    public Item next() {
        Item item = current.item;
        current = current.next;
        size--;
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
