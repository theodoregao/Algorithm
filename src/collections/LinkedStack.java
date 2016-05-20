package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Stack<Item> {

    private Node<Item> first;
    private int size = 0;
    
    public LinkedStack() {
        
    }
    
    public LinkedStack(Stack<Item> stack) {
        Stack<Item> tempStack = new LinkedStack<>();
        for (Item item: stack) tempStack.push(item);
        while (!tempStack.isEmpty()) push(tempStack.pop());
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedNodeIterator<Item>(first);
    }

    @Override
    public void push(Item item) {
        first = new Node<Item>(item, first);
        size++;
    }

    @Override
    public Item pop() {
        if (size == 0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
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
        Stack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) stack.push(i);
        for (int i : new LinkedStack<>(stack)) System.out.println(i);
        System.out.println();
        while (!stack.isEmpty()) System.out.println(stack.pop());
    }

}
