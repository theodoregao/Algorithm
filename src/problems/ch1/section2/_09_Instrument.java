package problems.ch1.section2;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.9 Instrument BinarySearch (page 47) to use a Counter to count the total number
of keys examined during all searches and then print the total after all searches are complete.
 Hint : Create a Counter in main() and pass it as an argument to rank().
 * @author Theodore
 *
 */
public class _09_Instrument {
    
    private static long count;

    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
                count++;
            }
            else if (key > a[mid]) {
                lo = mid + 1;
                count += 2;
            }
            else {
                count += 2;
                return mid;
            }
        }
        return -1;
    }

    public static int rank(int key, int[] a) {
        return indexOf(a, key);
    }

    public static void main(String[] args) {
        
        count = 0;

        // read the integers from a file
//        In in = new In("data/tinyW.txt");
        In in = new In("data/largeW.txt");
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read integer key from standard input; print if not in whitelist
        int max = 0;
        for (int value: whitelist) if (max < value) max = value;
        for (int i = 0; i <= max; i++) indexOf(whitelist, i);
        
        System.out.println("whitelist size: " + whitelist.length + ", search time: " + (max + 1) + ", compare time: " + count + ", average: " + count / max);
    }
}
