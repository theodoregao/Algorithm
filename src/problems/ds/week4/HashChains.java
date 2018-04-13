package problems.ds.week4;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    private CustomMap map;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
//    	System.out.println(query.type + " " + query.s);
        switch (query.type) {
            case "add":
                map.put(query.s);
                break;
            case "del":
                map.del(query.s);
                break;
            case "find":
                writeSearchResult(map.find(query.s));
                break;
            case "check":
                for (String cur : map.check(query.ind))
                    out.print(cur + " ");
                out.println();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        map = new CustomMap(in.nextInt());
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
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
    
    private static class CustomMap {
    	private int capacity;
    	private Object[] buckets;
    	private int x = 263;
    	private int p = 1000000007;
    	
    	public CustomMap(int capacity) {
    		this.capacity = capacity;
    		this.buckets = new Object[capacity];
    		for (int i = 0; i < buckets.length; i++) buckets[i] = new ArrayList<>();
    	}
    	
    	public List<String> check(int index) {
    		return (List<String>) buckets[index];
    	}
    	
    	public void put(String str) {
    		ArrayList<String> list = ((ArrayList<String>) buckets[hash(str)]);
    		if (!list.contains(str)) list.add(0, str);
    	}
    	
    	public void del(String str) {
    		ArrayList<String> list = ((ArrayList<String>) buckets[hash(str)]);
    		list.remove(str);
    	}
    	
    	public boolean find(String str) {
    		return ((List<String>) buckets[hash(str)]).contains(str);
    	}
    	
    	private int hash(String str) {
    		if (str == null) return -1;
    		long hash = 0;
    		for (int i = str.length() - 1; i >= 0; i--) {
    			hash = (((hash * x) + (int)str.charAt(i))) % p;
    		}
//    		System.out.println("hash: " + hash);
    		return (int) (hash % capacity);
    	}
    }
}
