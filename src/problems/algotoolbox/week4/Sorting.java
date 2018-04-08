package problems.algotoolbox.week4;

import java.io.*;
import java.util.*;

public class Sorting {
    public static void sort(int[] items) {
        SortUtil.shuffle(items);
        sortInternal(items, 0, items.length - 1);
    }
    
    private static void sortInternal(int[] items, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(items, lo, hi);
        sortInternal(items, lo, j - 1);
        sortInternal(items, j + 1, hi);
    }
    
    private static int partition(int[] items, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (items[++i] < items[lo]) if (i >= hi) break;
            while (items[--j] > items[lo]) ;
            if (i >= j) break;
            SortUtil.swap(items, i, j);
        }
        SortUtil.swap(items, lo, j);
        return j;
    }
    
    public static void threeWaySort(int[] items) {
        SortUtil.shuffle(items);
        threeWaySortInternal(items, 0, items.length - 1);
    }
    
    private static void threeWaySortInternal(int[] items, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        int v = items[lo];
        while (i <= gt) {
            if (items[i] < v) SortUtil.swap(items, i++, lt++);
            else if (items[i] > v) SortUtil.swap(items, i, gt--);
            else i++;
        }
        threeWaySortInternal(items, lo, lt - 1);
        threeWaySortInternal(items, gt + 1, hi);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        threeWaySort(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
    
    static class SortUtil {
        
        public static int[] randomInts(int size) {
            int[] items = new int[size];
            Random random = new Random();
            for (int i = 0; i < size; i++) items[i] = random.nextInt();
            return items;
        }
        
        /**
         * @param length if length less than 0, the length will be random value between (0, -length).
         */
        public static String[] randomStrings(int size, int length) {
            String[] strings = new String[size];
            for (int i = 0; i < size; i++) strings[i] = randomString(length);
            return strings;
        }
        
        private static String randomString(int length) {
            Random random = new Random();
            length = length > 0 ? length : (1 + random.nextInt(-length));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) sb.append((char)('a' + random.nextInt(26)));
            return sb.toString();
        }
        
        public static void shuffle(int[] items) {
            Random random = new Random();
            for (int i = 0; i < items.length; i++)
                SortUtil.swap(items, i, i + random.nextInt(items.length - i));
        }
        
        public static void shuffle(String[] strings) {
            Random random = new Random();
            for (int i = 0; i < strings.length; i++)
                SortUtil.swap(strings, i, i + random.nextInt(strings.length - i));
        }
        
        public static void swap(int[] items, int i, int j) {
            int temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }
        
        public static void swap(String[] strings, int i, int j) {
            String temp = strings[i];
            strings[i] = strings[j];
            strings[j] = temp;
        }
        
        public static boolean isSorted(int[] items) {
            for (int i = 1; i < items.length; i++)
                if (items[i - 1] > items[i]) return false;
            return true;
        }
        
        public static boolean isSorted(String[] strings) {
            for (int i = 1; i < strings.length; i++)
                if (strings[i - 1].compareTo(strings[i]) > 0) return false;
            return true;
        }

    }
}

