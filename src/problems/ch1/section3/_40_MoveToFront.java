package problems.ch1.section3;

import collections.GeneralizedQueue;
import edu.princeton.cs.algs4.StdIn;

public class _40_MoveToFront {
    
    public static void main(String[] args) {
        GeneralizedQueue<Character> queue = new GeneralizedQueue<>();
        
        String str = StdIn.readLine();
        
        for (Character ch: str.toCharArray()) {
            if (queue.find(ch) != -1) queue.delete(queue.find(ch));
            queue.insert(ch);
            
            System.out.println("size: " + queue.size());
            for (Character c: queue) System.out.print(c);
            System.out.println();
        }
    }

}
