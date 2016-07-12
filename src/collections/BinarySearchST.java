package collections;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    
    private static final int DEFAULT_SIZE = 16;
    
    private Key[] keys;
    private Value[] values;
    private int size;
    
    public BinarySearchST() {
        size = 0;
        keys = (Key[]) new Comparable[DEFAULT_SIZE];
        values = (Value[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = size; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        size++;
        if (size == keys.length) resize();
    }
    
    private void resize() {
        Key[] newKeys = (Key[]) new Comparable[keys.length * 2];
        Value[] newValues = (Value[]) new Object[values.length * 2];
        for (int i = 0; i < size; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
            keys[i] = null;
            values[i] = null;
        }
        keys = newKeys;
        values = newValues;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) return values[i];
        else return null;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[size - 1];
    }

    @Override
    public Key floor(Key key) {
        int i = rank(key, 0, size - 1);
        if (i >= 0) return key;
        if (i < -1) return keys[-i - 2];
        else return null;
    }

    @Override
    public Key ceiling(Key key) {
        return keys[rank(key)];
    }

    @Override
    public int rank(Key key) {
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
    
    private int rank(Key key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return -lo - 1;
    }

    @Override
    public Key select(int k) {
        return keys[k];
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public void delete(Key key) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            for (++i; i < size; i++) {
                keys[i - 1] = keys[i];
                values[i - 1] = values[i];
            }
            size--;
            keys[size] = null;
            values[size] = null;
        }
    }

    @Override
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedQueue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }
    
//    public static void main(String[] args) {
//        int minLength = 8;
//        SymbolTable<String, Integer> st = new BinarySearchST<String, Integer>();
//        In in = new In("data/tale.txt");
//        
//        while (!in.isEmpty()) {
//            String word = in.readString();
//            if (word.length() < minLength) continue;
//            if (!st.contains(word)) st.put(word, 1);
//            else st.put(word, st.get(word) + 1);
//        }
//        
//        String max = "";
//        st.put(max, 0);
//        for (String word: st.keys())
//            if (st.get(word) > st.get(max))
//                max = word;
//        System.out.println(max + ": " + st.get(max));
//    }
    
    public static void main(String[] args) {
        SymbolTable<Integer, Integer> st = new BinarySearchST<Integer, Integer>();
        st.put(1, 0);
        st.put(10, 1);
        st.put(20, 2);
        
        for (Integer key: st.keys())
            System.out.println(key + ": " + st.get(key));

        System.out.println(st.floor(0));
        System.out.println(st.floor(1));
        System.out.println(st.floor(9));
        System.out.println(st.floor(10));
        System.out.println(st.floor(19));
        System.out.println(st.floor(20));
    }

}
