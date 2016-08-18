package collections.impl.bag;

import java.util.Iterator;

import collections.Bag;

public class ResizingArrayBag<Item> implements Bag<Item> {

    private static final int DEFAULT_SIZE = 16;

    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    @Override
    public void add(Item item) {
        if (size == items.length) resize(items.length * 2);
        items[size++] = item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int max) {
        Item[] newItems = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) newItems[i] = items[i];
        items = newItems;
    }

    private class ArrayIterator implements Iterator<Item> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            return items[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Bag<Integer> bag = new ResizingArrayBag<Integer>();
        for (int i = 0; i < 10; i++) bag.add(i);
        for (int i : bag) System.out.println(i);
    }

}
