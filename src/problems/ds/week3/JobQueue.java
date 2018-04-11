package problems.ds.week3;
import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        Heap<Processor> processors = new Heap<>(Processor.comparator);
        for (int i = 0; i < numWorkers; i++) processors.insert(new Processor(0, i));
        for (int i = 0; i < jobs.length; i++) {
            Processor processor = processors.deleteTop();
            assignedWorker[i] = processor.index;
            startTime[i] = processor.completeTime;
            processor.completeTime += jobs[i];
            processors.insert(processor);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
    
    private static class Processor implements Comparable<Processor> {
        long completeTime;
        int index;
        
        public Processor(int completeTime, int index) {
            this.completeTime = completeTime;
            this.index = index;
        }
        
        @Override
        public int compareTo(Processor p) {
            return completeTime != p.completeTime ? Long.signum(p.completeTime - completeTime) : p.index - index;
        }
        
        public static Comparator<Processor> comparator = new Comparator<JobQueue.Processor>() {
            
            @Override
            public int compare(Processor p1, Processor p2) {
                return p1.compareTo(p2);
            }
        };
        
        @Override
        public String toString() {
            return "Processor(" + completeTime + ", " + index + ")";
        }
    }
    
    private static class Heap<Item> {
        private static final int DEFAULT_SIZE = 16;
        private Comparator comparator;
        private Item[] items;
        private int size;
        
        public Heap(Comparator comparator) {
            this.comparator = comparator;
            items = (Item[]) new Object[DEFAULT_SIZE];
        }
        
        public void insert(Item item) {
            if (size == items.length - 1) resize(items.length * 2);
            items[++size] = item;
            swim(size);
        }
        
        public Item deleteTop() {
            if (size == 0) throw new IllegalArgumentException();
            Item item = (Item) items[1];
            swap(1, size);
            items[size--] = null;
            sink(1);
            if (size <= items.length / 4 && size > DEFAULT_SIZE) resize(items.length / 2);
            return item;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        private void resize(int sz) {
            Item[] newItems = (Item[]) new Object[sz];
            for (int i = 1; i <= size; i++) newItems[i] = items[i];
            items = newItems;
        }
        
        private void swim(int k) {
            while (k > 1 && less(k / 2, k)) {
                swap(k / 2, k);
                k /= 2;
            }
        }
        
        private void sink(int k) {
            while (k * 2 <= size) {
                int j = k * 2;
                if (j < size && less(j, j + 1)) j++;
                if (!less(k, j)) break;
                swap(k, j);
                k = j;
            }
        }
        
        private boolean less(int i, int j) {
            return comparator.compare(items[i], items[j]) < 0;
        }
        
        private void swap(int i, int j) {
            Item item = items[i];
            items[i] = items[j];
            items[j] = item;
        }
    }
}
