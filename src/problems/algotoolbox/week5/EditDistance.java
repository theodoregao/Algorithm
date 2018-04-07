package problems.algotoolbox.week5;
import java.util.*;

class EditDistance {

	public static int EditDistance(String s, String t) {
		int[][] dp = new int[s.length() + 1][t.length() + 1];
		
		for (int i = 1; i <= s.length(); i++) dp[i][0] = dp[i][0] = i;
		for (int i = 1; i <= t.length(); i++) dp[0][i] = i;
		
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= t.length(); j++) {
				int left = dp[i][j - 1] + 1;
				int top = dp[i - 1][j] + 1;
				int left_top = dp[i - 1][j - 1] + (s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1);
				dp[i][j] = Math.min(Math.min(left, top), left_top);
			}
		}
		
		return dp[s.length()][t.length()];
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		String s = scan.next();
		String t = scan.next();

		System.out.println(EditDistance(s, t));

//		System.out.println(EditDistance("ab", "ab"));
//		System.out.println(EditDistance("short", "ports"));
//		System.out.println(EditDistance("editing", "distance"));
	}

}
