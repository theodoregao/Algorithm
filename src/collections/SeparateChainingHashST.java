package collections;

import java.util.Iterator;

public class SeparateChainingHashST<Key, Value> implements Map<Key, Value> {
    
    private static final int DEFAULT_CAPACITY = 64;
    private static final int SIZE_PER_UNIT = 4;
   
    private int size;
    private SequentialSearchST<Key, Value>[] st;
    
    public SeparateChainingHashST() {
        resize(DEFAULT_CAPACITY);
    }

    @Override
    public void put(Key key, Value value) {
        resize();
        if (get(key) == null) size++;
        put(st, key, value);
    }

    private void put(SequentialSearchST<Key, Value>[] st, Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    @Override
    public void delete(Key key) {
        if (get(key) != null) size--;
        st[hash(key)].delete(key);
        resize();
    }

    @Override
    public boolean contains(Key key) {
        return st[hash(key)].get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    @Override
    public Iterable<Key> keys() {
        Bag<Key> keySet = new LinkedBag<>();
        for (SequentialSearchST<Key, Value> keys: st)
            for (Key key: keys) keySet.add(key);
        return keySet;
    }
    
    private int hash(Key key) {
        return (~(key.hashCode() * 65521) + 1) & 0x7fffffff % st.length;
//        return (key.hashCode() & 0x7fffffff) % st.length;
    }
    
    private void resize() {
        if (size < st.length && st.length > DEFAULT_CAPACITY) resize(st.length / 2);
        else if (size >= st.length * SIZE_PER_UNIT) resize(st.length * 2);
    }
    
    private void resize(int capacity) {
//        System.out.println("resize() " + capacity);
        SequentialSearchST<Key, Value>[] oldST = st;
        st = new SequentialSearchST[capacity];
        for (int i = 0; i < st.length; i++) st[i] = new SequentialSearchST<>();
        if (oldST != null) for (SequentialSearchST<Key, Value> st: oldST) {
            Iterator<Key> it = st.iterator();
            while (it.hasNext()) {
                Key key = it.next();
                put(this.st, key, st.get(key));
            }
        }
    }

    public static void main(String[] args) {
        SeparateChainingHashST<Integer, Integer> map = new SeparateChainingHashST<>();
        for (int i = 0; i < 10000000; i++) map.put(i, i);
        for (int i = 0; i < 10000000; i++) if (map.get(i) != i) System.out.println("error: " + i + ", " + map.get(i));
        System.out.println(map.size);
        for (int i = 0; i < 1000000; i++) map.delete(i);
        System.out.println(map.size);
//        for (int i = 0; i < 10; i++) System.out.println(map.get(i));
        for (int i = 999998; i < 1000005; i++) System.out.println(map.get(i));
    }
}
