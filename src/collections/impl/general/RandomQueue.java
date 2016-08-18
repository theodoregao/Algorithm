package collections.impl.general;

import java.util.Iterator;
import java.util.NoSuchElementException;

import collections.impl.StdRandom;

public class RandomQueue<Item> implements Iterable<Item> {

    private static final int DEFAULT_SIZE = 16;

    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int first = 0;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    public void enqueue(Item item) {
        if (first + size == items.length) resize();
        items[first + size++] = item;
    }

    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        Item item = sample();
        items[first] = null;
        first++;
        size--;
        if (size <= items.length / 4 && items.length > DEFAULT_SIZE) resize();
        return item;
    }
    
    private Item sample() {
        return sample(0);
    }
    
    public Item sample(int index) {
        if (size == 0) throw new NoSuchElementException();
        int position = StdRandom.uniform(size - index);
        Item item = items[first + index + position];
        items[first + index + position] = items[first + index];
        items[first + index] = item;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    
    private void resize() {
        if (size > items.length / 2) resize(items.length * 2);
        else if (size <= items.length / 4 && items.length > DEFAULT_SIZE) resize(items.length / 2);
        else resize(items.length);
    }

    private void resize(int max) {
        Item[] newItems = items;
        if (max != items.length) newItems = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) newItems[i] = items[first + i];
        first = 0;
        items = newItems;
    }

    private class RandomIterator implements Iterator<Item> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            return sample(index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        RandomQueue<Integer> randomQueue = new RandomQueue<Integer>();
        for (int i = 0; i < 10; i++) randomQueue.enqueue(i);
        
        System.out.println(randomQueue.size);
        System.out.println();
        for (int i: randomQueue) System.out.println(i);
        
        System.out.println();
        for (int i = 0; i < 10; i++) System.out.println(randomQueue.sample());
        
        System.out.println();
        while (!randomQueue.isEmpty()) System.out.println(randomQueue.dequeue());
        
        System.out.println();
        randomQueue = new RandomQueue<Integer>();
        for (int i = 0; i < 10; i++) randomQueue.enqueue(i);
        while (!randomQueue.isEmpty()) System.out.println(randomQueue.dequeue());
    }

}
