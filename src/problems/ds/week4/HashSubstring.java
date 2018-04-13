package problems.ds.week4;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        long p = 1000000007;
        int x = 263;
        long xx = 1;
        long expectedHash = 0;
        long currentHash = 0;
        List<Integer> occurrences = new ArrayList<Integer>();
        
        if (s.length() > t.length()) return occurrences;
        
        for (int i = 0; i < m; i++) {
        	expectedHash = (expectedHash * x + s.charAt(i)) % p;
        }
        for (int i = 0; i < m - 1; i++) {
        	currentHash = (currentHash * x + t.charAt(i)) % p;
        	xx = (xx * x) % p;
        }

//        System.out.println("expected: " + expectedHash);
//        System.out.println("xx: " + xx);
//        System.out.println("currentHash: " + currentHash);
        
        for (int i = 0; i <= n - m; i++) {
        	currentHash = (currentHash * x + t.charAt(i + m - 1)) % p;
//        	System.out.println("current: " + currentHash);
        	if (currentHash == expectedHash && t.substring(i, i + m).equals(s))
        		occurrences.add(i);
        	currentHash = (p + currentHash - (xx * t.charAt(i)) % p) % p;
        }
        
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

