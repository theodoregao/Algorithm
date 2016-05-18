package collections;

public interface Bag<Item> {
    void add(Item item);
    boolean isEmpty();
    int size();
}
