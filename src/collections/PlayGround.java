package collections;

public class PlayGround {
    
    public static void main(String[] args) {
//        Stack<Integer> stack = new ResizingArrayStack<Integer>();
        Stack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) stack.push(i);
        for (int i: stack) System.out.println(i);
        while (!stack.isEmpty()) System.out.println(stack.pop());
        
        Queue<Integer> queue = new LinkedQueue<Integer>();
        for (int i = 0; i < 10; i++) queue.enqueue(i);
        for (int i: queue) System.out.println(i);
        while (!queue.isEmpty()) System.out.println(queue.dequeue());

        Bag<Integer> bag = new ResizingArrayBag<Integer>();
//        Bag<Integer> bag = new LinkedBag<Integer>();
        for (int i = 0; i < 10; i++) bag.add(i);
        for (int i: bag) System.out.println(i);
    }

}
