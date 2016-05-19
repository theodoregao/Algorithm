package collections;

import java.util.Iterator;

public class ResizingArrayBag<Item> implements Bag<Item> {
    
    private static final int DEFAULT_SIZE = 16;
    
    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
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
    
    private class ReverseArrayIterator implements Iterator<Item> {
        
        private int index = size;

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Item next() {
            return items[--index];
        }
        
    }

}
