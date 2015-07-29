import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private LinkedNode head;
    private LinkedNode tail;
    private int size;

    public Deque() // construct an empty deque
    {
        head = new LinkedNode(null);
        tail = new LinkedNode(null);
        size = 0;
        
        head.next = tail;
        tail.prev = head;
    }

    public boolean isEmpty() // is the deque empty?
    {
        return size == 0;
    }

    public int size() // return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item) // add the item to the front
    {
        if (item == null) throw new NullPointerException();
        LinkedNode node = new LinkedNode(item);
        LinkedNode front = head.next;
        front.prev = node;
        node.prev = head;
        head.next = node;
        node.next = front;
        size++;
    }

    public void addLast(Item item) // add the item to the end
    {
        if (item == null) throw new NullPointerException();
        LinkedNode node = new LinkedNode(item);
        LinkedNode last = tail.prev;
        last.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = last;
        size++;
    }

    public Item removeFirst() // remove and return the item from the front
    {
        if (isEmpty()) throw new NoSuchElementException();
        LinkedNode front = head.next;
        head.next = front.next;
        head.next.prev = head;
        size--;
        front.next = null;
        front.prev = null;
        return front.item;
    }

    public Item removeLast() // remove and return the item from the end
    {
        if (isEmpty()) throw new NoSuchElementException();
        LinkedNode last = tail.prev;
        tail.prev = last.prev;
        tail.prev.next = tail;
        size--;
        last.prev = null;
        last.next = null;
        return last.item;
    }

    public Iterator<Item> iterator() // return an iterator over items in order
                                     // from front to end
    {
        return new LinkedNodeIterator(head);
    }
        
    private class LinkedNodeIterator implements Iterator<Item> {
        private LinkedNode current;
        
        public LinkedNodeIterator(LinkedNode first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current.next != tail;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            current = current.next;
            return current.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class LinkedNode {
        private Item item;
        private LinkedNode prev;
        private LinkedNode next;

        public LinkedNode(Item item) {
            this.item = item;
            prev = null;
            next = null;
        }
    }
}