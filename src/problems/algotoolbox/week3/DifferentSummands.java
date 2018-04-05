package problems.algotoolbox.week3;
import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        int l = 1;
        while (n > 0) {
//        	System.out.println(n);
        	if (n > 2 * l) {
        		summands.add(l);
        		n -= l;
        		l++;
        	}
        	else {
        		summands.add(n);
        		n = 0;
        	}
        }
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
//    	System.out.println(optimalSummands(1));
//    	System.out.println(optimalSummands(2));
//    	System.out.println(optimalSummands(6));
//    	System.out.println(optimalSummands(8));
//    	System.out.println(optimalSummands(9));
//    	System.out.println(optimalSummands(10));
//    	System.out.println(optimalSummands(Integer.MAX_VALUE).size());
    }
}

