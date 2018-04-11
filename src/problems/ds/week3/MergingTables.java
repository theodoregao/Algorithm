package problems.ds.week3;
import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables {
    private final InputReader reader;
    private final OutputWriter writer;

    public MergingTables(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables(reader, writer).run();
        writer.writer.flush();
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        
        int[] sizes = new int[n];
        
        
        for (int i = 0; i < n; i++) {
            sizes[i] = reader.nextInt();
        }
        UnionFind unionFind = new UnionFind(sizes);
        for (int i = 0; i < m; i++) {
            unionFind.union(reader.nextInt(), reader.nextInt());
            writer.printf("%d\n", unionFind.max());
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
    
    private static class UnionFind {
        private int n;
        private long max;
        private int[] parents;
        private long[] sizes;
        
        public UnionFind(int[] sizes) {
            n = sizes.length;
            parents = new int[n + 1];
            this.sizes = new long[n + 1];
            max = 0;
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
                max = Math.max(max, this.sizes[i] = sizes[i - 1]);
            }
        }
        
        public int find(int p) {
            if (p == parents[p]) return p;
            return parents[p] = find(parents[p]);
        }
        
        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) return;
            if (sizes[i] > sizes[j]) {
                parents[j] = i;
                max = Math.max(max, sizes[i] += sizes[j]);
            }
            else {
                parents[i] = j;
                max = Math.max(max, sizes[j] += sizes[i]);
            }
            n--;
        }
        
        public long max() {
            return max;
        }
    }
}
