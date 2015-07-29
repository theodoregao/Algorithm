import java.util.NoSuchElementException;

public class Subset {
   public static void main(String[] args) {
       RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
       int k = Integer.parseInt(args[0]);
       if (k <= 0) return;
       
       String string = StdIn.readString();

       while (!string.equals("")) {
           randomizedQueue.enqueue(string);
           try {
               string = StdIn.readString();
           }
           catch (NoSuchElementException ex) {
               string = "";
               break;
           }
       }
       while (randomizedQueue.size() > k)
           randomizedQueue.dequeue();
       while (!randomizedQueue.isEmpty())
           System.out.println(randomizedQueue.dequeue());
   }
}