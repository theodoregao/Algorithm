package collections;

public class LinearProbingHashST<Key, Value> implements Map<Key, Value> {
    
    private static final int DEFAULT_CAPACITY = 64;
    
//    private static final int[] PRIMES = new int[] {61, 127, 251, 509, 1021, 2039, 4093, 8191,
//            16381, 32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301, 8388593,
//            16777213, 33554393, 67108859, 134217689, 268435399, 536870909, 1073741789, 2147483647};
   
    private int capacity;
    private int size;
    private Key[] keys;
    private Value[] values;
    
    public LinearProbingHashST() {
        resize(DEFAULT_CAPACITY);
    }

    @Override
    public void put(Key key, Value value) {
        resize();
        if (get(key) == null) size++;
        put(keys, values, key, value);
    }
    
    private void put(Key[] keys, Value[] values, Key key, Value value) {
        int index = hash(key);
        while (keys[index] != null && !key.equals(keys[index])) index = ++index % keys.length;
        keys[index] = key;
        values[index] = value;
    }

    @Override
    public void delete(Key key) {
        if (get(key) == null) return;
        size--;
        int index = hash(key);
        while (!key.equals(keys[index])) index = ++index % keys.length;
        keys[index] = null;
        values[index] = null;
        
        index = ++index % keys.length;
        while (keys[index] != null) {
            Key thisKey = keys[index];
            Value thisValue = values[index];
            keys[index] = null;
            values[index] = null;
            put(keys, values, thisKey, thisValue);
            index = ++index % keys.length;
        }
    }

    @Override
    public Iterable<Key> keys() {
        Bag<Key> keySet = new LinkedBag<>();
        for (Key key: keys) if (key != null) keySet.add(key);
        return keySet;
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
    public boolean contains(Key key) {
        int index = hash(key);
        while (keys[index] != null && !key.equals(keys[index])) index = ++index % keys.length;
        return values[index] != null;
    }

    @Override
    public Value get(Key key) {
        int index = hash(key);
        while (keys[index] != null && !key.equals(keys[index])) index = ++index % keys.length;
        return values[index];
    }
    
    private int hash(Key key) {
        return (~(key.hashCode() * 65521) + 1) & 0x7fffffff % keys.length;
//        return (~key.hashCode() + 1) & 0x7fffffff % keys.length;
    }
    
    private void resize() {
        if (size < capacity / 8 && capacity > DEFAULT_CAPACITY) resize(capacity / 2);
        else if (size >= capacity / 2) resize(capacity * 2);
    }
    
    private void resize(int capacity) {
        this.capacity = capacity;
//        System.out.println("resize() " + capacity);
        Key[] oldKeys = keys;
        Value[] oldValues = values;
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
        if (oldKeys != null) for (int i = 0; i < oldKeys.length; i++) if (oldKeys[i] != null) put(keys, values, oldKeys[i], oldValues[i]);
    }

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        LinearProbingHashST<Integer, Integer> map = new LinearProbingHashST<>();
        for (int i = 1; i < 10000000; i++) map.put(i, i);
//        System.out.println("size = " + map.size);
        for (int i = 0; i < 10000000; i++) if (map.get(i) == null || map.get(i) != i) System.out.println(i + ", " + map.get(i));
        for (int i = 0; i < 1000000; i++) map.delete(i);
//        System.out.println("size = " + map.size);
        for (int i = 999998; i < 1000005; i++) System.out.println(map.get(i));
        System.out.println(map.get(0));
//        for (int i = 0; i < 10000000; i++) if (map.get(i) == null || map.get(i) != i) System.out.println(i + ", " + map.get(i));
        System.out.println(System.currentTimeMillis() - timestamp);
    }
}
