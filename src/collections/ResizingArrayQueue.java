package collections;

import java.util.Iterator;

public class ResizingArrayQueue<Item> implements Queue<Item> {

    private static final int DEFAULT_SIZE = 16;

    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int first = 0;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    @Override
    public void enqueue(Item item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Item dequeue() {
        // TODO Auto-generated method stub
        return null;
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
        for (int i = 0; i < size; i++)
            newItems[i] = items[i];
        items = newItems;
    }
    
    private void shrink() {
        if (size <= items.length / 4 && items.length > DEFAULT_SIZE)
            resize(size / 2);
    }

    private void expand() {
        if (size == items.length)
            resize(size * 2);
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

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
    }

}
