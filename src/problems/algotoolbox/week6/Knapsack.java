package problems.algotoolbox.week6;
import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int[][] dp = new int[w.length + 1][W + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= W; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= w[i - 1]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i - 1]] + w[i - 1]);
            }
        }
        return dp[w.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
        
//        System.out.println(optimalWeight(10, new int[] {1, 4, 8}));
    }
}
