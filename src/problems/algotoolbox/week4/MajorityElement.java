package problems.algotoolbox.week4;

import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a) {
        int left = 0, right = a.length, half = a.length / 2;
        int majority = getMajorityElementHelper(a, 0, a.length - 1);
        return count(a, left, right - 1, majority) > half ? majority : -1;
    }
    
    private static int getMajorityElementHelper(int[] elems, int lo, int hi) {
        if (lo == hi) return elems[lo];
        int mid = (lo + hi) >>> 1;
        int l = getMajorityElementHelper(elems, lo, mid);
        int r = getMajorityElementHelper(elems, mid + 1, hi);
        if (l == r) return l;
        return (count(elems, lo, mid, l) > count(elems, mid + 1, hi, r)) ? l : r;
    }
    
    private static int count(int[] elems, int lo, int hi, int x) {
        int count = 0;
        for (int i = lo; i <= hi; i++)
            if (elems[i] == x)
                count++;
        return count;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
        
//        System.out.println(getMajorityElement(new int[] {2, 3, 3, 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 3, 9, 2, 2}));
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
}

