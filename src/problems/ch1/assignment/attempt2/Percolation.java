package problems.ch1.assignment.attempt2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private static final int[][] DIR = new int[][] {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    private static final boolean OPEN = true;

    private final int top;
    private final int width;
    private boolean[] sites;
    private boolean[] isConnectToBottom;
    private WeightedQuickUnionUF unionFind;
    private int numberOfOpenSites;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        width = n;
        top = width * width;
        sites = new boolean[width * width + 1];
        isConnectToBottom = new boolean[width * width + 1];
        unionFind = new WeightedQuickUnionUF(width * width + 1);
        numberOfOpenSites = 0;
        
        for (int i = 1; i <= width; i++) {
            unionFind.union(index(1, i), top);
            isConnectToBottom[width * width - i] = true;
        } 
    }

    public void open(int row, int col) {
        if (!valid(row, col)) throw new IllegalArgumentException();
        if (isOpen(row, col)) return;
        
        sites[index(row, col)] = OPEN;
        numberOfOpenSites++;
        int site = index(row, col);
        
        if (1 == row) sites[top] = OPEN;
        
        boolean isConnectedToBottom = isConnectToBottom[unionFind.find(site)];
        for (int i = 0; i < DIR.length; i++) {
            int r = row + DIR[i][0];
            int c = col + DIR[i][1];
            if (valid(r, c) && isOpen(r, c)) {
                isConnectedToBottom |= isConnectToBottom[unionFind.find(index(r, c))];
                unionFind.union(site, index(r, c));
            }
        }

        isConnectToBottom[unionFind.find(site)] = isConnectedToBottom;
    }

    public boolean isOpen(int row, int col) {
        if (!valid(row, col)) throw new IllegalArgumentException();
        return sites[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (!valid(row, col)) throw new IllegalArgumentException();
        return isOpen(row, col) && unionFind.connected(index(row, col), top);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return sites[top] && isConnectToBottom[unionFind.find(top)];
    }
    
    private int index(int r, int c) {
        return (r - 1) * width + c - 1;
    }
    
    private boolean valid(int r, int c) {
        return r > 0 && r <= width && c > 0 && c <= width;
    }

    public static void main(String[] args) {
        // unit test
    }
}