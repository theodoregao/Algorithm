package collections.impl.general;

import java.util.Iterator;
import java.util.NoSuchElementException;

import collections.Deque;
import collections.impl.DoubleLinkedNode;
import collections.impl.DoubleLinkedNodeIterator;

public class LinkedDeque<Item> implements Deque<Item> {
    
    private DoubleLinkedNode<Item> first;
    private DoubleLinkedNode<Item> last;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new DoubleLinkedNodeIterator<Item>(first);
    }

    @Override
    public void pushLeft(Item item) {
        if (size == 0) first = last = new DoubleLinkedNode<>(item);
        else {
            DoubleLinkedNode<Item> newFirst = new DoubleLinkedNode<>(item, null, first);
            first.previous = newFirst;
            first = newFirst;
        }
        size++;
    }

    @Override
    public void pushRight(Item item) {
        if (size == 0) first = last = new DoubleLinkedNode<>(item);
        else {
            DoubleLinkedNode<Item> newLast = new DoubleLinkedNode<>(item, last, null);
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    @Override
    public Item popLeft() {
        if (size == 0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        size--;
        if (size == 0) last = null;
        return item;
    }

    @Override
    public Item popRight() {
        if (size == 0) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        size--;
        if (size == 0) first = null;
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
        Deque<Integer> deque = new LinkedDeque<>();
        
        for (int i = 0; i < 5; i++) deque.pushLeft(i);
        for (int i = 5; i < 10; i++) deque.pushRight(i);
        
        for (int i: deque) System.out.println(i);
        System.out.println();
        
        while (deque.size() > 0) {
            if (deque.size() % 2 == 0) System.out.println(deque.popLeft());
            else System.out.println(deque.popRight());
        }
        
    }

}
