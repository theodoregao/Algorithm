package problems.ch1.section1;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.37 Bad shuffling. Suppose that you choose a random integer between 0 and N-1
in our shuffling code instead of one between i and N-1 . Show that the resulting order is
not equally likely to be one of the N! possibilities. Run the test of the previous exercise
for this version.
 * @author shun
 *
 */
public class _37_BadShuffling {
    public static void main(String[] args) {
        final int n = 1000000;
        final int SIZE = 10;
        int[] data = new int[SIZE];
        
        int[][] counts = new int[SIZE][SIZE];
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < SIZE; j++) data[j] = j;
//            StdRandom.shuffle(data);
            badShuffle(data);
            for (int k = 0; k < SIZE; k++)
                counts[data[k]][k]++;
        }
        
        print(counts);
    }
    
    private static void print(int[][] counts) {
        int count = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts.length; j++) {
                count += counts[i][j];
                System.out.print(String.format("%6d ", counts[i][j]));
            }
            System.out.println();
        }
        System.out.println();
        double delta = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts.length; j++) {
                double posibility = 1.0 * counts[i][j] / count;
                System.out.print(String.format("%.4f ", posibility));
                if (delta < posibility) delta = posibility;
            }
            System.out.println();
        }
        System.out.println(delta);
    }
    
    private static void badShuffle(int[] a) {
        if (a == null) throw new NullPointerException("argument array is null");
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(1, n);     // between i and n-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
}
