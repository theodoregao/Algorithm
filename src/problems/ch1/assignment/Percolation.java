package problems.ch1.assignment;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private static final int[][] DIRECTIONS = new int[][] {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    private int n;
    private boolean[] mSites;
    private boolean[] mBottom;
    private WeightedQuickUnionUF mUnionFind;
    private int mTop;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        n = N;
        mSites = new boolean[N * N + 1];
        mBottom = new boolean[N * N + 1];
        mTop = N * N;
        mUnionFind = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 1; i <= N; i++) {
            mUnionFind.union(mTop, getSite(1, i));
            mBottom[getSite(N, i)] = true;
        }
    }
    
    private int getSite(int i, int j) {
        return (i - 1) * n + j - 1;
    }

    public void open(int i, int j) {
        if (!isAvailableSite(i, j)) throw new IndexOutOfBoundsException();
        if (isOpen(i, j)) return;
        
        mSites[getSite(i, j)] = true;
        int site = getSite(i, j);
        
        if (i == 1) mSites[mTop] = true;

        boolean isConnectToBottom = mBottom[site] | mBottom[mUnionFind.find(site)];
        for (int k = 0; k < DIRECTIONS.length; k++) {
            int row = i + DIRECTIONS[k][0];
            int col = j + DIRECTIONS[k][1];
            if (isAvailableSite(row, col) && isOpen(row, col)) {
                isConnectToBottom |= mBottom[mUnionFind.find(getSite(row, col))];
                mUnionFind.union(getSite(row, col), site);
            }
        }
        mBottom[mUnionFind.find(site)] = isConnectToBottom;
    }
    
    private boolean isAvailableSite(int row, int col) {
        return row > 0 && row <= n
                && col > 0 && col <= n;
    }

    public boolean isOpen(int i, int j) {
        if (!isAvailableSite(i, j)) throw new IndexOutOfBoundsException();
        return mSites[getSite(i, j)];
    }
    
    private boolean isOpen(int p) {
        return mSites[p];
    }

    public boolean isFull(int i, int j) {
        if (!isAvailableSite(i, j)) throw new IndexOutOfBoundsException();
        return isOpen(i, j) && mUnionFind.connected(mTop, getSite(i, j));
    }

    public boolean percolates() {
        return mBottom[mUnionFind.find(mTop)];
    }

    public static void main(String[] args) {
        // int n = 1000;
        // Percolation percolation = new Percolation(n);
        // int count = 0;
        // int[] inputs = StdRandom.sample(0, n * n, n * n);
        // while (!percolation.percolates()) {
        // int row = inputs[count] / n + 1;
        // int col = inputs[count] % n + 1;
        // percolation.open(row, col);
        // count++;
        // }
        // System.out.println(count);
    }
}