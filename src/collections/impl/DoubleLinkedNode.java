package collections.impl;

public class DoubleLinkedNode<Item> {
    public Item item;
    public DoubleLinkedNode<Item> previous;
    public DoubleLinkedNode<Item> next;
    
    public DoubleLinkedNode() {
        this(null);
    }
    
    public DoubleLinkedNode(Item item) {
        this(item, null, null);
        previous = next = this;
    }
    
    public DoubleLinkedNode(Item item, DoubleLinkedNode<Item> previous, DoubleLinkedNode<Item> next) {
        this.item = item;
        this.previous = previous;
        this.next = next;
    }
}
