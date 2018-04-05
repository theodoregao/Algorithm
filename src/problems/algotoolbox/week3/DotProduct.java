package problems.algotoolbox.week3;
import java.util.*;

public class DotProduct {
    private static long maxDotProduct(long[] a, long[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long sum = 0;
        for (int i = 0; i < a.length; i++)
        	sum += a[i] * b[i];
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextLong();
        }
        System.out.println(maxDotProduct(a, b));

//    	System.out.println(maxDotProduct(new int[] {23}, new int[] {39}));
//    	System.out.println(maxDotProduct(new int[] {1, 3, -5}, new int[] {-2, 4, 1}));
    }
}

