package problems.ch2.section5;

import java.util.HashSet;

import collections.impl.StdRandom;

public class _02_TwoWordCompound {
    
//    public static int twoWordCompound(String[] words) {
//        int count = 0;
//        quickSort(words); // nlogn
//        
////        HashSet<String> set = new HashSet<String>();
//        for (int i = 0; i < words.length; ) {
//            for (int j = 0; j < words.length; ) { // n^2
//                if (i != j && binarySearch(words, words[i] + words[j])) {// logn
////                    System.out.println(words[i] + words[j]);
////                    set.add(words[i] + words[j]);
//                    count++;
//                }
//                while (++j < words.length && words[j - 1] == words[j]) ;
//            }
//            while (++i < words.length && words[i - 1] == words[i]) ;
//        }
//        return count;//set.size();
//    }
    
    public static int twoWordCompound2(String[] words) {
        int count = 0;
        String[] words2 = new String[(words.length - 1) * words.length];
        
        long timestamp = System.currentTimeMillis();
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) { // n^2
                if (i == j) continue;
                words2[index++] = words[i] + words[j];
            }
        }
        
        System.out.println(System.currentTimeMillis() - timestamp);
        
        quickSort(words2); // n^2 2logn
        
        System.out.println(System.currentTimeMillis() - timestamp);
        for (int i = 0; i < words.length; i++) { // n
//            System.out.println(words[i]);
            if (binarySearch(words2, words[i])) {// 2logn
//                System.out.println(words[i]);
                count++;
            }
        }
        
        return count;
    }
    
    public static int twoWordCompound3(String[] words) {
        int count = 0;

        long timestamp = System.currentTimeMillis();
        
        HashSet<String> keys = new HashSet<String>();
        for (int i = 0; i < words.length; i++) keys.add(words[i]);
        
        HashSet<String> set = new HashSet<String>();

        System.out.println(System.currentTimeMillis() - timestamp);
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) { // n^2
                if (i == j) continue;
                set.add(words[i] + words[j]);
            }
        }

        System.out.println(System.currentTimeMillis() - timestamp);
        
        for (String word: keys) if (set.contains(word)) count++;
        
        return count;
    }
    
    public static void main(String[] args) {
        String[] words = /*new String[] {"a", "aa", "aa", "a", "a"};/*/randomString(5000);
//        for (int i = 0; i < words.length; i++) System.out.println(i + ": " + words[i]);
        System.out.println("start");
        long timestamp = System.currentTimeMillis();
        System.out.println("count: " + twoWordCompound3(words));
        System.out.println(System.currentTimeMillis() - timestamp);
    }
    
    static String[] randomString(int size) {
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = intToString(i);
        }
        return strings;
    }
    
    static String intToString(int i) {
        final int A_TO_Z = 26;
        String s = "";
        while (i >= 0) {
            s = (char)(i % A_TO_Z + 'a') + s;
            i = i / A_TO_Z - 1;
        }
        return s;
    }

    static void quickSort(String[] words) {
        StdRandom.shuffle(words);
        quickSort(words, 0, words.length - 1);
    }
    
    static boolean binarySearch(String[] words, String word) {
        int lo = 0, hi = words.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int compare = word.compareTo(words[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else return true;
        }
        return false;
    }
    
    static void quickSort(String[] words, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(words, lo, hi);
        quickSort(words, lo, j - 1);
        quickSort(words, j + 1, hi);
    }
    
    static int partition(String[] words, int lo, int hi) {
        int i = lo, j = hi + 1;
        String v = words[lo];
        while (true) {
            while (words[++i].compareTo(v) < 0) if (i == hi) break;
            while (v.compareTo(words[--j]) < 0) ;
            if (i >= j) break;
            swap(words, i, j);
        }
        swap(words, lo, j);
        return j;
    }
    
    static void swap(String[] words, int i, int j) {
        String word = words[i];
        words[i] = words[j];
        words[j] = word;
    }
}
