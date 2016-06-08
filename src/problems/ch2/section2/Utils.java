package problems.ch2.section2;

public class Utils<Item> {
    public void print(Node<Item> items) {
        while (items != null) {
            System.out.println(items.item);
            items = items.next;
        }
    }
}
