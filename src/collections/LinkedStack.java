package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Stack<Item> {
    
    private Node<Item> first;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    @Override
    public void push(Item item) {
        first = new Node<Item>(item, first);
        size++;
    }

    @Override
    public Item pop() {
        if (size == 0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    @Override
    public Item peek() {
        if (size == 0) throw new NoSuchElementException();
        return first.item;
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
