package collections;

public interface SymbolTable<Key extends Comparable<Key>, Value> {
    
    void put(Key key, Value value);
    Value get(Key key);
    boolean contains(Key key);
    boolean isEmpty();
    int size();

    Key min();
    Key max();
    Key floor(Key key);
    Key ceiling(Key key);
    int rank(Key key);
    Key select(int k);
    
    void deleteMin();
    void deleteMax();
    void delete(Key key);
    int size(Key lo, Key hi);
    Iterable<Key> keys();
    Iterable<Key> keys(Key lo, Key hi);
}
