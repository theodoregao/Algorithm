package collections.impl.heap;

import java.util.Comparator;
import java.util.Iterator;

import collections.Map;
import collections.impl.st.LinearProbingHashST;

public class IndexHeap<Item> implements Iterable<Item> {
    private static final int DEFAULT_SIZE = 16;

    private Comparator<Item> comparator;
    private Map<Item, Integer> index;
    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int size = 0;
    
    public IndexHeap(Comparator<Item> comparator) {
        this.comparator = comparator;
        index = new LinearProbingHashST<>();
    }
    
    public IndexHeap(Item[] items) {
        this((Comparator<Item>)null);
        for (int i = 0; i < items.length; i++) {
            insert(items[i]);
        }
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void insert(Item item) {
        if (size == items.length - 1) resize(size * 2);
        items[++size] = item;
        index.put(item, size);
        swim(size);
    }
    
    public Item top() {
        return items[1];
    }
    
    public void delete(Item item) {
        int pos = index.get(item);
        swap(pos, size);
        items[size--] = null;
        index.delete(item);
        sink(pos);
        if (size <= items.length / 4 && items.length > DEFAULT_SIZE) resize(items.length / 2);
    }
    
    public Item deleteTop() {
        Item item = items[1];
        swap(1, size);
        items[size--] = null;
        index.delete(item);
        sink(1);
        if (size <= items.length / 4 && items.length > DEFAULT_SIZE) resize(items.length / 2);
        
        return item;
    }
    
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k / 2, k);
            k /= 2;
        }
    }
    
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }
    
    private boolean less(int i, int j) {
        return comparator.compare(items[i], items[j]) < 0;
    }
    
    private void swap(int i, int j) {
        index.put(items[i], j);
        index.put(items[j], i);
        Item item = items[i];
        items[i] = items[j];
        items[j] = item;
    }
    
    private void resize(int max) {
        Item[] newItems = (Item[]) new Object[max];
        for (int i = 1; i <= size; i++) newItems[i] = items[i];
        items = newItems;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            return items[1 + index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
    
    public static void main(String[] args) {
        IndexHeap<Integer> heap = new IndexHeap<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < 5; i++) heap.insert(i);
        heap.delete(3);
        heap.delete(0);
        heap.delete(2);
        while (!heap.isEmpty()) System.out.println(heap.deleteTop());
    }
}
