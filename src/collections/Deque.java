package collections;

public interface Deque<Item> extends Iterable<Item> {
    void pushLeft(Item item);
    void pushRight(Item item);
    Item popLeft();
    Item popRight();
    boolean isEmpty();
    int size();
}
