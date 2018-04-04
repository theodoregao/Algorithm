package problems.algotoolbox.week2;

import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHuge(long n, int m) {
        if (n <= 1)
            return 1;
        
        long[] p = new long[m * 6];
        p[0] = 0;
        p[1] = 1;
        int count = 1;
        for (int i = 2; i < m * 6; i++) {
            p[i] = (p[i - 2] + p[i - 1]) % m;
            if (p[i] == 1 && p[i - 1] == 0) break;
            count++;
        }
        
        return p[(int)(n % count)];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(getFibonacciHuge(n, m));
//        System.out.println(getFibonacciHuge(239,  1000));
//        System.out.println(getFibonacciHuge(2816213588L,  30524));
    }
}

