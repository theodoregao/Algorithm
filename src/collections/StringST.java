package collections;

public interface StringST<Value> {
    
    void put(String key, Value value);
    Value get(String key);
    void delete(String key);
    boolean contains(String key);
    boolean isEmpty();
    String longestPrefixOf(String string);
    Iterable<String> keysWithPrefix(String prefix);
    Iterable<String> keysThatMatch(String pattern);
    int size();
    Iterable<String> keys();

}
