package problems.ch1.assignment.attempt2;


import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int t) {
        // perform T independent experiments on an N-by-N grid
        if (n <= 0 || t <= 0) throw new IllegalArgumentException();
        int count = 0;
        double[] means = new double[t];
        double tempMean = 0;
        double tempStddev = 0;
        
        for (int i = 0; i < t; i++) {
            count = 0;
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (percolation.isOpen(row, col)) continue;
                percolation.open(row, col);
                count++;
            }
            means[i] = 1.0 * count / (n * n);
            tempMean += means[i];
        }
        tempMean /= t;
        this.mean = tempMean;
        for (int i = 0; i < t; i++) {
            tempStddev += Math.pow(Math.abs(tempMean - means[i]), 2);
        }
        tempStddev /= (t - 1);
        tempStddev = Math.sqrt(tempStddev);
        this.stddev = tempStddev;
        double delta = 1.96 * tempStddev / Math.sqrt(t);
        confidenceLo = tempMean - delta;
        confidenceHi = tempMean + delta;
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