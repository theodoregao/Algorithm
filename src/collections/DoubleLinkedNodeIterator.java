package collections;

import java.util.Iterator;

class DoubleLinkedNodeIterator<Item> implements Iterator<Item> {
    
    private DoubleLinkedNode<Item> current;
    private int size;
    
    public DoubleLinkedNodeIterator(DoubleLinkedNode<Item> node) {
        this(node, Integer.MAX_VALUE);
    }
    
    public DoubleLinkedNodeIterator(DoubleLinkedNode<Item> node, int size) {
        current = node;
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
