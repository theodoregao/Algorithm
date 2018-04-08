package problems.algotoolbox.week6;
import java.util.*;
import java.io.*;

public class Partition3 {
	
	private static int partition(int[] values, boolean[] used, int sum) {
		int n = values.length;
		int[][] dp = new int[n + 1][sum + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i - 1][j];
				if (!used[i - 1] && j >= values[i - 1])
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - values[i - 1]] + values[i - 1]);
			}
		}
//		System.out.println("partition: " + sum + ": " + dp[n][sum]);
		return dp[n][sum];
	}
	
    private static int partition3(int[] A) {
    	int n = A.length;
    	int sum = 0;
    	for (int v: A) sum += v;
    	if (sum  % 3 != 0) return 0;
    	sum /= 3;
    	boolean[] used = new boolean[n];
        return backtrack(A, used, 0, sum, 0) ? 1 : 0;
    }
    
    private static boolean backtrack(int[] values, boolean[] used, int pos, int sum, int curSum) {
    	if (curSum == sum && partition(values, used, sum) == sum) return true;
    	if (pos >= values.length) return false;
    	if (backtrack(values, used, pos + 1, sum, curSum)) return true;
    	if (curSum < sum) {
	    	used[pos] = true;
	    	if (backtrack(values, used, pos + 1, sum, curSum + values[pos])) return true;
	    	used[pos] = false;
    	}
    	return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));

//    	System.out.println(partition3(new int[] {4, 3, 1, 1, 3}));
//    	System.out.println(partition3(new int[] {40}));
//    	System.out.println(partition3(new int[] {17, 59, 34, 57, 17, 23, 67, 1, 18, 2, 59}));
//    	System.out.println(partition3(new int[] {1, 2, 3, 4, 5, 5, 7, 7, 8, 10, 12, 19, 25}));
    }
}

