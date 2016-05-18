package problems.ch1.section1;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.28 Remove duplicates. Modify the test client in BinarySearch to remove any duplicate keys in the whitelist after the sort.
 * @author Theodore
 *
 */
public class _28_RemoveDuplicates {
    private _28_RemoveDuplicates() {
    }

    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static int rank(int key, int[] a) {
        return indexOf(a, key);
    }
    
    private static int[] removeDuplicates(int[] whitelist) {
        int size = 0;
        if (whitelist.length > 1) {
            size = 1;
            for (int i = 1; i < whitelist.length; i++)
                if (whitelist[size - 1] != whitelist[i])
                    whitelist[size++] = whitelist[i];
        }
        return Arrays.copyOf(whitelist, size);
    }

    public static void main(String[] args) {

        // read the integers from a file
        In in = new In("data/tinyW.txt");
//        In in = new In("data/largeW.txt");
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);
        
        whitelist = removeDuplicates(whitelist);
        
        System.out.println(whitelist.length);

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (_28_RemoveDuplicates.indexOf(whitelist, key) == -1)
                StdOut.println(key);
        }
    }

}
