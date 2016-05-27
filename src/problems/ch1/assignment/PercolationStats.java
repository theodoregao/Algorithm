package problems.ch1.assignment;


import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int N, int T) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        int count = 0;
        double[] means = new double[T];
        for (int i = 0; i < T; i++) {
            count = 0;
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int col = StdRandom.uniform(N) + 1;
                if (percolation.isOpen(row, col)) continue;
                percolation.open(row, col);
                count++;
            }
            means[i] = 1.0 * count / (N * N);
            mean += means[i];
        }
        mean /= T;
        for (int i = 0; i < T; i++) {
            stddev += Math.pow(Math.abs(mean - means[i]), 2);
        }
        stddev /= (T - 1);
        stddev = Math.sqrt(stddev);
        double delta = 1.96 * stddev / Math.sqrt(T);
        confidenceLo = mean - delta;
        confidenceHi = mean + delta;
    }

    public double mean() {
        // sample mean of percolation threshold
        return mean;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo() {
        // low endpoint of 95% confidence interval
        return confidenceLo;
    }

    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return confidenceHi;
    }

    public static void main(String[] args) {
        // test client (described below)
        
        PercolationStats percolationStats = new PercolationStats(1000, 10);
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println(percolationStats.confidenceLo());
        System.out.println(percolationStats.confidenceHi());
    }
}