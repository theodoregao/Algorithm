package collections.impl.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import collections.Queue;

public class ResizingArrayQueue<Item> implements Queue<Item> {

    private static final int DEFAULT_SIZE = 16;

    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int first = 0;
    private int size = 0;
    
    public ResizingArrayQueue() {
        
    }
    
    public ResizingArrayQueue(Queue<Item> queue) {
        for (Item item: queue) enqueue(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    @Override
    public Item peek() {
        if (size == 0) throw new NoSuchElementException();
        return items[first];
    }

    @Override
    public void enqueue(Item item) {
        if (first + size == items.length) resize();
        items[first + size++] = item;
    }

    @Override
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        Item item = items[first];
        items[first] = null;
        first++;
        size--;
        if (size <= items.length / 4 && items.length > DEFAULT_SIZE) resize();
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
    
    private void resize() {
        if (size > items.length / 2) resize(items.length * 2);
        else if (size <= items.length / 4 && items.length > DEFAULT_SIZE) resize(items.length / 2);
        else resize(items.length);
    }

    private void resize(int max) {
        Item[] newItems = items;
        if (max != items.length) newItems = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[first + i];
            items[first + i] = null;
        }
        first = 0;
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
            return items[first + index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ResizingArrayQueue<Integer>();
        for (int i = 0; i < 1000; i++) queue.enqueue(i);
        for (int i: new ResizingArrayQueue<>(queue)) System.out.println(i);
        
        System.out.println();
        for (int i = 0; i < 1000; i++) {
            if (queue.dequeue() != i) System.out.println("error");
        }
    }

}
