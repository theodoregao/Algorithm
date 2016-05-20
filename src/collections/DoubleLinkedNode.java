package collections;

class DoubleLinkedNode<Item> {
    Item item;
    DoubleLinkedNode<Item> previous;
    DoubleLinkedNode<Item> next;
    
    DoubleLinkedNode() {
        this(null);
    }
    
    DoubleLinkedNode(Item item) {
        this(item, null, null);
        previous = next = this;
    }
    
    DoubleLinkedNode(Item item, DoubleLinkedNode<Item> previous, DoubleLinkedNode<Item> next) {
        this.item = item;
        this.previous = previous;
        this.next = next;
    }
}
