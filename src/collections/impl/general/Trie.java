package collections.impl.general;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    
    private Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        root.insert(word.toCharArray(), 0);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Boolean r = root.search(word.toCharArray(), 0);
        return r != null && r;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return root.search(prefix.toCharArray(), 0) != null;
    }
    
//    private static class Node {
//        Node[] next = new Node[256];
//        boolean terminate;
//        public void insert(char[] str, int index) {
//            if (str.length == index) {
//                terminate = true;
//                return;
//            }
//            if (next[str[index]] == null) next[str[index]] = new Node();
//            next[str[index]].insert(str, index + 1);
//        }
//        public Boolean search(char[] str, int index) {
//            if (str.length == index) return terminate;
//            if (next[str[index]] == null) return null;
//            return next[str[index]].search(str, index + 1);
//        }
//    }
    
    private static class Node {
        Map<Character, Node> next = new HashMap<>();
        boolean terminate;
        public void insert(char[] str, int index) {
            if (str.length == index) { terminate = true; return; }
            if (!next.containsKey(str[index])) next.put(str[index], new Node());
            next.get(str[index]).insert(str, index + 1);
        }
        public Boolean search(char[] str, int index) {
            if (str.length == index) return terminate;
            if (!next.containsKey(str[index])) return null;
            return next.get(str[index]).search(str, index + 1);
        }
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("shun");
        trie.insert("gao");
        trie.insert("is");
        trie.insert("Chinese");
        System.out.println(trie.search("shun"));
        System.out.println(trie.search("shu"));
        trie.insert("shu");
        System.out.println(trie.search("shu"));
        System.out.println(trie.search("shung"));
        System.out.println(trie.startsWith("gao"));
        System.out.println(trie.startsWith("Chi"));
        System.out.println(trie.startsWith("C"));
        System.out.println(trie.startsWith("i"));
        System.out.println(trie.startsWith("is"));
        System.out.println(trie.startsWith("is "));
    }
}