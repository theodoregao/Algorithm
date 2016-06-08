package collections;

public interface Queue<Item> extends Iterable<Item> {
    Item peek();
    void enqueue(Item item);
    Item dequeue();
    boolean isEmpty();
    int size();
}
