package collections;

import java.util.Iterator;

public class SeparateChainingHashMap<Key, Value> {
    
    private static final int[] PRIMES = new int[] {61, 127, 251, 509, 1021, 2039, 4093, 8191,
            16381, 32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301, 8388593,
            16777213, 33554393, 67108859, 134217689, 268435399, 536870909, 1073741789, 2147483647};
   
    private int capacityIndex;
    private int size;
    private SequentialSearchST<Key, Value>[] st;
    
    public SeparateChainingHashMap() {
        resize(0);
    }
    
    public void put(Key key, Value value) {
        put(st, key, value);
        size++;
        resize();
    }
    
    private void put(SequentialSearchST<Key, Value>[] st, Key key, Value value) {
        st[hash(key)].put(key, value);
    }
    
    public Value get(Key key) {
        return st[hash(key)].get(key);
    }
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % getCapacity();
    }
    
    private int getCapacity() {
        if (capacityIndex < 0 || capacityIndex >= PRIMES.length) return 0;
        return PRIMES[capacityIndex];
    }
    
    private int getCapacity(int capacityIndex) {
        if (capacityIndex < 0 || capacityIndex >= PRIMES.length) return 0;
        return PRIMES[capacityIndex];
    }
    
    private void resize() {
        if (size < getCapacity() / 4 && capacityIndex > 0) resize(capacityIndex - 1);
        else if (size >= getCapacity() && capacityIndex < PRIMES.length - 1) resize(capacityIndex + 1);
    }
    
    private void resize(int capacityIndex) {
        this.capacityIndex = capacityIndex;
        System.out.println("resize() " + getCapacity());
        SequentialSearchST<Key, Value>[] oldST = st;
        st = new SequentialSearchST[getCapacity()];
        for (int i = 0; i < getCapacity(); i++) st[i] = new SequentialSearchST<>();
        if (oldST != null) for (SequentialSearchST<Key, Value> st: oldST) {
            Iterator<Key> it = st.iterator();
            while (it.hasNext()) {
                Key key = it.next();
                put(this.st, key, st.get(key));
            }
        }
    }

    public static void main(String[] args) {
        SeparateChainingHashMap<Integer, Integer> map = new SeparateChainingHashMap<>();
        for (int i = 0; i < 10000000; i++) map.put(i, i);
        for (int i = 0; i < 10000000; i++) if (map.get(i) != i) System.out.println("error: " + i + ", " + map.get(i));
        System.out.println(map.size);
    }
}
