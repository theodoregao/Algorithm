package problems.ch1.section1;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.39 Random matches. Write a BinarySearch client that takes an int value T as
command-line argument and runs T trials of the following experiment for N = 10 3 , 10 4 ,
10 5 , and 10 6 : generate two arrays of N randomly generated positive six-digit int values,
and find the number of values that appear in both arrays. Print a table giving the average
value of this quantity over the T trials for each value of N.
 * @author shun
 *
 */
public class _39_RandomMatches {

    public static void main(String[] args) {
        final int min = 100000;
        final int max = 1000000;
        final int n = 1000000;
        final int t = 10;
        
        int[] data1 = new int[n];
        int[] data2 = new int[n];

        int count = 0;
        for (int k = 0; k < t; k++) {
            for (int i = 0; i < n; i++) {
                data1[i] = (int) StdRandom.uniform(min, max);
                data2[i] = (int) StdRandom.uniform(min, max);
            }
            Arrays.sort(data1);
            Arrays.sort(data2);
            count += BinarySearch.indexOf(data2, data1[0]) >= 0 ? 1 : 0;
//            for (int i = 1; i < n; i++)
//                if (data1[i] != data1[i - 1] && BinarySearch.indexOf(data2, data1[i]) >= 0) count++;

            for (int i = min; i < max; i++)
                if (BinarySearch.indexOf(data1, i) != -1 && BinarySearch.indexOf(data2, i) != -1) count++;
        }
        System.out.println(1.0 * count / t);
    }
}
