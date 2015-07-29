
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private static final int DEFAULT_ARRAY_SIZE = 16;

    private Item[] items;
    
    private int size;

    public RandomizedQueue() // construct an empty randomized queue
    {
        size = 0;
        resize();
    }
    
    private void resize() {
        if (items == null) {
            resize(DEFAULT_ARRAY_SIZE);
        }
        
        else if (items.length == size) {
            resize(2 * items.length);
        }
        
        else resize(items.length / 2);
    }

    private void resize(int sz) {
        Item[] newItems = (Item[]) new Object[Math.max(DEFAULT_ARRAY_SIZE, sz)];
        if (items != null) {
            for (int i = 0; i < items.length && i < newItems.length; i++)
                newItems[i] = items[i];
            for (int i = 0; i < items.length; i++)
                items[i] = null;
        }
        items = newItems;
    }

    public boolean isEmpty() // is the queue empty?
    {
        return size() == 0;
    }

    public int size() // return the number of items on the queue
    {
        return size;
    }

    public void enqueue(Item item) // add the item
    {
        if (item == null) throw new NullPointerException();
        if (size >= items.length) resize();
        items[size++] = item;
        exchange(items, size - 1, StdRandom.uniform(size));
    }
    
    private void exchange(Item[] theItems, int i, int j) {
        Item item = theItems[i];
        theItems[i] = theItems[j];
        theItems[j] = item;
    }

    public Item dequeue() // remove and return a random item
    {
        if (size == 0) throw new NoSuchElementException();
        int dequeuePosition = StdRandom.uniform(size);
        Item item = items[dequeuePosition];
        items[dequeuePosition] = items[--size];
        items[size] = null;
        if (size < items.length / 2 && items.length > DEFAULT_ARRAY_SIZE) resize();
        return item;
    }

    public Item sample() // return (but do not remove) a random item
    {
        if (size == 0) throw new NoSuchElementException();
        return items[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() // return an independent iterator over
                                     // items in random order
    {
        return new ArrayIterator(items, size);
    }
        
    private class ArrayIterator implements Iterator<Item> {
        
        private Item[] items;
        private int cursor;
        
        public ArrayIterator(Item[] items, int size) {
            this.items = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
                this.items[i] = items[i];
            for (int i = 0; i < size; i++) {
                exchange(items, i, StdRandom.uniform(i + 1));
            }
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < items.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = items[cursor];
            items[cursor++] = null;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
