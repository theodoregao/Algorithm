package collections.impl.general;

import java.util.Iterator;
import java.util.NoSuchElementException;

import collections.impl.DoubleLinkedNode;
import collections.impl.DoubleLinkedNodeIterator;

public class CircularLinkedList<Item> implements Iterable<Item> {
    
    private DoubleLinkedNode<Item> current;
    private int size = 0;
    
    public void add(Item item) {
        if (size == 0) current = new DoubleLinkedNode<>(item);
        else {
            DoubleLinkedNode<Item> newNode = new DoubleLinkedNode<>(item, current.previous, current);
            current.previous.next = newNode;
            current.previous = newNode;
        }
        size++;
    }
    
    public Item currentItem() {
        return next(0);
    }
    
    public Item removeCurrent() {
        if (size == 0) throw new NoSuchElementException();
        Item item = current.item;
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
        return item;
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
    
    public static void main(String[] args) {
        CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<Integer>();
        for (int i = 0; i < 10; i++) circularLinkedList.add(i);
        while(!circularLinkedList.isEmpty()) {
            circularLinkedList.next(3);
            System.out.println(circularLinkedList.removeCurrent());
        }
    }

}
