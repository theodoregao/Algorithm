package problems.algotoolbox.week5;
import java.util.*;

public class PrimitiveCalculator {
	private static List<Integer> optimal_sequence(int n) {
		int[] dp = new int[n + 1];
		int[] path = new int[n + 1];
		dp[0] = Integer.MAX_VALUE / 2;
		dp[1] = 0;
		path[1] = 1;

		for (int i = 2; i <= n; i++) {
			int multipleBy2 = i % 2 == 0 ? dp[i / 2] + 1 : Integer.MAX_VALUE;
			int multipleBy3 = i % 3 == 0 ? dp[i / 3] + 1 : Integer.MAX_VALUE;
			int plus1 = dp[i - 1] + 1;
			dp[i] = Math.min(Math.min(multipleBy2, multipleBy3), plus1);
			if (dp[i] == multipleBy2)
				path[i] = i / 2;
			else if (dp[i] == multipleBy3)
				path[i] = i / 3;
			else
				path[i] = i - 1;
		}

		List<Integer> sequence = new ArrayList<Integer>();
		sequence.add(0, n);
		while (n != 1) {
			sequence.add(0, path[n]);
			n = path[n];
		}
		return sequence;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<Integer> sequence = optimal_sequence(n);
		System.out.println(sequence.size() - 1);
		for (Integer x : sequence) {
			System.out.print(x + " ");
		}
		// System.out.println(optimal_sequence(1));
		// System.out.println(optimal_sequence(5));
		// System.out.println(optimal_sequence(96234));
	}
}
