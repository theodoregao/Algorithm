package collections.impl;

public class Node<Item> {
    public Item item;
    public Node<Item> next;
    
    public Node() {
        this(null);
    }
    
    public Node(Item item) {
        this(item, null);
    }
    
    public Node(Item item, Node<Item> next) {
        this.item = item;
        this.next = next;
    }
}
