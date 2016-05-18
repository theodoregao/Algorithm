package problems.ch1.section1;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;

/**
 * 1.1.29 Equal keys. Add to BinarySearch a static method rank() that takes a key and
a sorted array of int values (some of which may be equal) as arguments and returns the
number of elements that are smaller than the key and a similar method count() that
returns the number of elements equal to the key. Note : If i and j are the values returned
by rank(key, a) and count(key, a) respectively, then a[i..i+j-1] are the values in
the array that are equal to key.
 * @author Theodore
 *
 */
public class _29_EqualKeys {
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    
    private static int left(int[] a, int key, int left, int right) {
        int lo = left;
        int hi = right;
        int mid = lo + (hi - lo) / 2;
        while (lo <= hi) {
            if      (key <= a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            mid = lo + (hi - lo) / 2;
        }
        return mid;
    }

    public static int rank(int[] a, int key) {
        return left(a, key, 0, a.length - 1);
    }
    
    public static int count(int[] a, int key) {
        return left(a, key + 1, 0, a.length - 1) - left(a, key, 0, a.length - 1);
    }

    public static void main(String[] args) {
//        In in = new In("data/tinyW.txt");
        In in = new In("data/largeW.txt");
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        for (int i = 0; i < whitelist[whitelist.length - 1]; i++)
            if (count(whitelist, i) > 1)
                System.out.println(i + ": " + count(whitelist, i));

    }
}
