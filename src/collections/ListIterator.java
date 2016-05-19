package collections;

import java.util.Iterator;

class ListIterator<Item> implements Iterator<Item> {
    
    private Node<Item> current;
    
    public ListIterator(Node<Item> node) {
        current = node;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Item next() {
        Item item = current.item;
        current = current.next;
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
