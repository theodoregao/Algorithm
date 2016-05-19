package collections;

public interface Steque<Item> extends Iterable<Item> {
    void push(Item item);
    void enqueue(Item item);
    Item pop();
    Item peek();
    boolean isEmpty();
    int size();
}
