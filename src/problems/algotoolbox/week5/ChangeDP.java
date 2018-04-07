package problems.algotoolbox.week5;
import java.util.Scanner;

public class ChangeDP {
	private static int getChange(int m) {
		int[] coins = new int[m + 1];
		coins[0] = 0;

		for (int i = 1; i <= m; i++) {
			int c1 = coins[i - 1] + 1;
			int c3 = i >= 3 ? coins[i - 3] + 1 : Integer.MAX_VALUE;
			int c4 = i >= 4 ? coins[i - 4] + 1 : Integer.MAX_VALUE;
			coins[i] = Math.min(c1, Math.min(c3, c4));
		}

		return coins[m];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		System.out.println(getChange(m));
		// System.out.println(getChange(2));
		// System.out.println(getChange(34));
	}
}
