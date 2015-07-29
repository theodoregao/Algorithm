public class Percolation {
    private final int[][] DIRECTIONS = new int[][] { { 0, -1 }, { 0, 1 },
            { -1, 0 }, { 1, 0 } };
    private int N;
    private WeightedQuickUnionUF fullWeightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[] sites;

    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException();
        this.N = N;
        final int NN = N * N;
        sites = new boolean[NN + 2];
        sites[0] = true;
        sites[NN + 1] = true;
        weightedQuickUnionUF = new WeightedQuickUnionUF(NN + 2);
        fullWeightedQuickUnionUF = new WeightedQuickUnionUF(NN + 1);
    }

    public void open(int i, int j) {
        checkIndex(i, j);
        sites[index(i, j)] = true;
        if (i == 1) {
            weightedQuickUnionUF.union(0, index(i, j));
            fullWeightedQuickUnionUF.union(0, index(i, j));
        }
        if (i == N) {
            weightedQuickUnionUF.union(N * N + 1, index(i, j));
        }
        for (int k = 0; k < DIRECTIONS.length; k++) {
            int x = i + DIRECTIONS[k][0];
            int y = j + DIRECTIONS[k][1];
            if (isAvailableIndex(x, y) && isOpen(x, y)) {
                weightedQuickUnionUF.union(index(i, j), index(x, y));
                fullWeightedQuickUnionUF.union(index(i, j), index(x, y));
            }
        }
    }

    public boolean isOpen(int i, int j) {
        checkIndex(i, j);
        return sites[index(i, j)];
    }

    public boolean isFull(int i, int j) {
        checkIndex(i, j);
        return fullWeightedQuickUnionUF.connected(0, index(i, j));
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, N * N + 1);
    }

    private boolean isAvailableIndex(int i, int j) {
        return i > 0 && i <= N && j > 0 && j <= N;
    }

    private int index(int i, int j) {
        return (i - 1) * N + j;
    }

    private void checkIndex(int i, int j) {
        if (i <= 0 || i > N)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > N)
            throw new IndexOutOfBoundsException("row index j out of bounds");
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);

        while (!StdIn.isEmpty()) {
            percolation.open(StdIn.readInt(), StdIn.readInt());
        }
        System.out.println("percolation=" + percolation.percolates());
    }
}