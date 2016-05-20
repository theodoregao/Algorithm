package collections;

import java.util.Iterator;

public class LinkedBag<Item> implements Bag<Item> {

    private Node<Item> first;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new LinkedNodeIterator<Item>(first);
    }

    @Override
    public void add(Item item) {
        first = new Node<Item>(item, first);
        size++;
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
        Bag<Integer> bag = new LinkedBag<Integer>();
        for (int i = 0; i < 10; i++) bag.add(i);
        for (int i : bag) System.out.println(i);
    }

}
