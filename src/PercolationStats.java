public class PercolationStats {
    private double[] xs;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        xs = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            int count = 0;
            while (true) {
                count++;
                int x, y;
                do {
                    x = 1 + StdRandom.uniform(N);
                    y = 1 + StdRandom.uniform(N);
                } while (percolation.isOpen(x, y));

                percolation.open(x, y);
                if (percolation.percolates()) {
                    break;
                }
            }
            xs[i] = 1.0 * count / (N * N);
        }
    }

    public double mean() {
        double mean = 0;
        for (int i = 0; i < xs.length; i++) {
            mean += xs[i];
        }
        return mean / xs.length;
    }

    public double stddev() {
        double stddev = 0;
        double mean = mean();
        for (int i = 0; i < xs.length; i++) {
            stddev += (mean - xs[i]) * (mean - xs[i]);
        }
        return Math.sqrt(stddev / (xs.length - 1));
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(xs.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(xs.length);
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(2, 1000000);
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println(percolationStats.confidenceLo());
        System.out.println(percolationStats.confidenceHi());
    }
}
