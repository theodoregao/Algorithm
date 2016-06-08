package problems.ch2.section2;

public class _21_Triplicates {
    
    public static void main(String[] args) {
        Node<String> list1 = buildStringList(new String[] {"a", "gao", "gif", "png", "shun", "jpeg", "ndk"});
        Node<String> list2 = buildStringList(new String[] {"aosp", "ga", "shun", "ppt", "xml", "ndk"});
        Node<String> list3 = buildStringList(new String[] {"shun", "shua", "b", "gao", "good game", "oh!", "junk", "no"});
        
        System.out.println(new _21_Triplicates().triplicates(list1, list2, list3));
    }
    
    private String triplicates(Node<String> list1, Node<String> list2, Node<String> list3) {
        
        _17_LinkedListSort<String> sorter = new _17_LinkedListSort<String>();
        list1 = sorter.sort(list1);
        list2 = sorter.sort(list2);
        list3 = sorter.sort(list3);

        new Utils<String>().print(list1);
        System.out.println();
        new Utils<String>().print(list2);
        System.out.println();
        new Utils<String>().print(list3);
        System.out.println();
        
        while (list1 != null && list2 != null && list3 != null) {
            if (list1.item.equals(list2.item) && list1.item.equals(list3.item)) return list1.item;
            else {
                Node<String> node = least(list1, list2, list3);
                if (node == list1) list1 = list1.next;
                else if (node == list2) list2 = list2.next;
                else list3 = list3.next;
            }
        }
        
        return "";
    }
    
    private Node<String> least(Node<String> list1, Node<String> list2, Node<String> list3) {
        if (list1.item.compareTo(list2.item) < 0) {
            if (list1.item.compareTo(list3.item) < 0) return list1;
            else return list3;
        }
        else {
            if (list2.item.compareTo(list3.item) < 0) return list2;
            else return list3;
        }
    }
    
    private static Node<String> buildStringList(String[] strings) {
        Node<String> head = new Node<String>(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            head = new Node<String>(strings[i], head);
        }
        return head;
    }

}

