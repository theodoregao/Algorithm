package collections.impl.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

import collections.Stack;

public class ResizingArrayStack<Item> implements Stack<Item> {

    private static final int DEFAULT_SIZE = 16;

    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int size = 0;
    
    public ResizingArrayStack() {
        
    }
    
    public ResizingArrayStack(Stack<Item> stack) {
        Stack<Item> tempStack = new ResizingArrayStack<>();
        for (Item item: stack) tempStack.push(item);
        while (!tempStack.isEmpty()) push(tempStack.pop());
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    @Override
    public void push(Item item) {
        if (size == items.length) resize(size * 2);
        items[size++] = item;
    }

    @Override
    public Item pop() {
        if (size == 0) throw new NoSuchElementException();
        Item item = items[--size];
        items[size] = null;
        if (size <= items.length / 4 && size > DEFAULT_SIZE) resize(size / 2);
        return item;
    }

    @Override
    public Item peek() {
        if (size == 0) throw new NoSuchElementException();
        return items[size - 1];
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

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new ResizingArrayStack<Integer>();
        for (int i = 0; i < 10; i++) stack.push(i);
        
        for (int i : new ResizingArrayStack<>(stack)) System.out.println(i);
        System.out.println();
        
        while (!stack.isEmpty()) System.out.println(stack.pop());
    }
}
