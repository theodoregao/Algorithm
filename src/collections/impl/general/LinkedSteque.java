package collections.impl.general;

import java.util.Iterator;
import java.util.NoSuchElementException;

import collections.Steque;
import collections.impl.LinkedNodeIterator;
import collections.impl.Node;

public class LinkedSteque<Item> implements Steque<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new LinkedNodeIterator<>(first);
    }

    @Override
    public void push(Item item) {
        first = new Node<Item>(item, first);
        if (last == null) last = first;
        size++;
    }

    @Override
    public void enqueue(Item item) {
        if (last == null) push(item);
        else {
            last = last.next = new Node<Item>(item, null);
            size++;
        }
    }

    @Override
    public Item pop() {
        if (size == 0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first == null) last = null;
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

    public static void main(String[] args) {
        Steque<Integer> steque = new LinkedSteque<Integer>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) steque.enqueue(i);
            else steque.push(i);
        }
        for (int i : steque) System.out.println(i);
        System.out.println();
        while (!steque.isEmpty()) System.out.println(steque.pop());
    }

}
