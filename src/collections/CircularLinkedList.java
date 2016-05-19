package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item> {
    
    private DoubleLinkedNode<Item> current;
    private int size = 0;
    
    public Item currentItem() {
        return next(0);
    }
    
    public void removeCurrent() {
        if (size == 0) throw new NoSuchElementException();
        if (size == 1) current = null;
        else {
            DoubleLinkedNode<Item> oldCurrent = current;
            current = current.next;
            oldCurrent.previous.next = current;
            current.previous = oldCurrent.previous;
            oldCurrent.previous = null;
            oldCurrent.next = null;
        }
        size--;
    }
    
    public Item next() {
        return next(1);
    }
    
    public Item previous() {
        return next(-1);
    }
    
    public Item next(int position) {
        if (size == 0) throw new NoSuchElementException();
        if (position > 0) while (position-- > 0) current = current.next;
        else if (position < 0) while (position++ < 0) current = current.previous;
        return current.item;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DoubleLinkedNodeIterator<>(current, size);
    }

}
