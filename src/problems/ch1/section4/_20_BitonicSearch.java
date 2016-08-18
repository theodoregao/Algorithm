package problems.ch1.section4;

import java.util.Arrays;

import algorithms.BinarySearch;
import collections.impl.StdRandom;

public class _20_BitonicSearch {
    
    static int count;
    
    static int bitonicSearch(int[] a, int key) {
        return bitonicSearch(a, 0, a.length - 1, key);
    }
    
    static int bitonicSearch(int[] a, int lo, int hi, int key) {
        count++;
        if (lo > hi) return -1;
        int mid = (lo + hi) >>> 1;
        
//        System.out.println(lo + ", " + hi + ", " + mid);
        
        if (a[mid] == key) return mid;
        
        else if (a[mid] < key) {
            if (mid > 0 && a[mid] > a[mid - 1]) return bitonicSearch(a, mid + 1, hi, key);
            else if (mid < a.length - 1 && a[mid] > a[mid + 1]) return bitonicSearch(a, lo, mid - 1, key);
        }
        else {
            if (mid > 0 && a[mid] > a[mid - 1]) {
                int index = BinarySearch.search(a, key, lo, mid - 1);
                if (index >= 0) return index;
                else return bitonicSearch(a, mid + 1, hi, key);
            }
            if (mid < a.length - 1 && a[mid] > a[mid + 1]) {
                int index = BinarySearch.search(a, key, mid + 1, hi, true);
                if (index >= 0) return index;
                else return bitonicSearch(a, lo, mid - 1, key);
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int size = 2000000;
        int[] temp = StdRandom.sample(-size, size, size);
        Arrays.sort(temp);
        int[] a = new int[temp.length];
        int index = StdRandom.uniform(temp.length);
        for (int i = index; i < a.length; i++) a[i - index] = temp[i];
        for (int i = index - 1; i >= 0; i--) a[a.length - i - 1] = temp[i];
        
        int key = a[StdRandom.uniform(a.length)];
//        utils.Arrays.visualizeWithY(a, key);
        index = bitonicSearch(a, key);
//        utils.Arrays.visualize(a, index);
        
        System.out.println(index);
        
        System.out.println(a[index] == key);
        
        System.out.println("count : " + count);
    }

}
