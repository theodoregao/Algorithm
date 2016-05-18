package problems.ch1.section1;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.36 Empirical shuffle check. Run computational experiments to check that our
shuffling code on page 32 works as advertised. Write a program ShuffleTest that takes
command-line arguments M and N, does N shuffles of an array of size M that is initial-
ized with a[i] = i before each shuffle, and prints an M-by-M table such that row i
gives the number of times i wound up in position j for all j . All entries in the array
should be close to N/M.
 * @author shun
 *
 */
public class _36_EmpiricalShuffleCheck {
    public static void main(String[] args) {
        final int n = 1000000;
        final int SIZE = 10;
        int[] data = new int[SIZE];
        
        int[][] counts = new int[SIZE][SIZE];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < SIZE; j++) data[j] = j;
            StdRandom.shuffle(data);
            for (int k = 0; k < SIZE; k++)
                counts[k][data[k]]++;
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
}
