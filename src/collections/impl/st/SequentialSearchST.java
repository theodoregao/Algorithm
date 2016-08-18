package collections.impl.st;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> implements Iterable<Key> {

    private Node first;
    private int size;
    
    private class Node {
        Key key;
        Value value;
        Node next;
        
        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    
    public int size() {
        return size;
    }
    
    public Value get(Key key) {
        for (Node node = first; node != null; node = node.next) if (key.equals(node.key)) {
            return node.value;
        }
        return null;
    }
    
    public void put(Key key, Value value) {
        for (Node node = first; node != null; node = node.next)
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        first = new Node(key, value, first);
        size++;
    }
    
    public void delete(Key key) {
        Node preNode = null;
        for (Node node = first; node != null; preNode = node, node = node.next) if (key.equals(node.key)) {
            if (preNode == null) first = node.next;
            else preNode.next = node.next;
            size--;
        }
    }

    @Override
    public Iterator<Key> iterator() {
        return new SequenticalSearchIterator();
    }
    
    private class SequenticalSearchIterator implements Iterator<Key> {
        
        private Node node;
        
        public SequenticalSearchIterator() {
            node = first;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Key next() {
            Key key = node.key;
            node = node.next;
            return key;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
}
