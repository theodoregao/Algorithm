package collections.impl.st;

import collections.Queue;
import collections.StringST;
import collections.impl.queue.LinkedQueue;

public class TrieST<Value> implements StringST<Value> {
    
    private static final int R = 256;
    private Node root = new Node();

    @Override
    public Value get(String key) {
        Node node = get(root, key, 0);
        return node == null ? null : (Value) node.value;
    }
    
    private Node get(Node node, String key, int d) {
        if (node == null) return null;
        if (d == key.length()) return node;
        char c = key.charAt(d);
        return get(node.next[c], key, d + 1);
    }

    @Override
    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }
    
    private Node put(Node node, String key, Value value, int d) {
        if (node == null) node = new Node();
        if (d == key.length()) {
            node.value = value;
            return node;
        }
        char c = key.charAt(d);
        node.next[c] = put(node.next[c], key, value, d + 1);
        return node;
    }

    @Override
    public void delete(String key) {
        root = delete(root, key, 0);
    }
    
    private Node delete(Node node, String key, int d) {
        if (node == null) return null;
        if (d == key.length()) node.value = null;
        else {
            char c = key.charAt(d);
            node.next[c] = delete(node.next[c], key, d + 1);
        }
        if (node.value != null) return node;
        for (char c = 0; c < R; c++) if (node.next[c] != null) return node;
        return null;
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() != 0;
    }

    @Override
    public String longestPrefixOf(String string) {
        return string.substring(0, search(root, string, 0, 0));
    }
    
    private int search(Node node, String string, int d, int length) {
        if (node == null) return length;
        if (node.value != null) length = d;
        if (d == string.length()) return length;
        char c = string.charAt(d);
        return search(node.next[c], string, d + 1, length);
    }

    @Override
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new LinkedQueue<String>();
        collect(get(root, prefix, 0), prefix, queue);
        return queue;
    }
    
    private void collect(Node node, String prefix, Queue<String> queue) {
        if (node == null) return;
        if (node.value != null) queue.enqueue(prefix);
        for (char c = 0; c < R; c++) collect(node.next[c], prefix + c, queue);
    }

    @Override
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new LinkedQueue<String>();
        collect(root, "", pattern, queue);
        return queue;
    }
    
    private void collect(Node node, String prefix, String pattern, Queue<String> queue) {
        int d = prefix.length();
        if (node == null) return;
        if (d == pattern.length() && node.value != null) queue.enqueue(prefix);
        if (d == pattern.length()) return;
        
        char next = pattern.charAt(d);
        for (char c = 0; c < R; c++)
            if (next == '.' || next == c)
                collect(node.next[c], prefix + c, pattern, queue);
    }

    @Override
    public int size() {
        return size(root);
    }
    
    private int size(Node node) {
        if (node == null) return 0;
        int cnt = 0;
        if (node.value != null) cnt++;
        for (char c = 0; c < R; c++) cnt += size(node.next[c]);
        return cnt;
    }

    @Override
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
    
    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

}
