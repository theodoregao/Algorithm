package collections;

class Node<Item> {
    Item item;
    Node<Item> next;
    
    Node() {
        this(null);
    }
    
    Node(Item item) {
        this(item, null);
    }
    
    Node(Item item, Node<Item> next) {
        this.item = item;
        this.next = next;
    }
}
