package problems.algotoolbox.week3;
import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
        String result = "";
        Arrays.sort(a, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				if (s1.equals(s2)) return 0;
				int n = s1.length() * s2.length();
				for (int i = 0; i <= n; i ++) {
					if (s1.charAt(i % s1.length()) != s2.charAt(i % s2.length()))
						return s2.charAt(i % s2.length()) - s1.charAt(i % s1.length());
				}
				return 0;
			}
		});
        
        
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));

//    	System.out.println(largestNumber(new String[] {"21", "2"}));
//    	System.out.println(largestNumber(new String[] {"9", "4", "6", "1", "9"}));
//    	System.out.println(largestNumber(new String[] {"23", "39", "92"}));
//    	System.out.println(largestNumber(new String[] {"23", "39", "92", "339", "333"}));
//    	System.out.println(largestNumber(new String[] {"3", "23"}));
    }
}

