package problems.algotoolbox.week5;
import java.util.*;

public class LCS2 {

	private static int lcs2(int[] a, int[] b) {
		int[][] dp = new int[a.length + 1][b.length + 1];
		
		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length; j++) {
				dp[i][j] = Math.max(
						Math.max(dp[i - 1][j], dp[i][j - 1]),
						dp[i - 1][j - 1] + (a[i - 1] == b[j - 1] ? 1 : 0));
			}
		}
		
		return dp[a.length][b.length];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		int m = scanner.nextInt();
		int[] b = new int[m];
		for (int i = 0; i < m; i++) {
			b[i] = scanner.nextInt();
		}

		System.out.println(lcs2(a, b));

//		System.out.println(lcs2(new int[]{2, 7, 5}, new int[]{2, 5}));
//		System.out.println(lcs2(new int[]{7}, new int[]{1, 2, 3, 4}));
//		System.out.println(lcs2(new int[]{2, 7, 8, 3}, new int[]{5, 2, 8, 7}));
	}
}
