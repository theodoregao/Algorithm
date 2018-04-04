package problems.algotoolbox.week2;

import java.util.*;

public class FibonacciPartialSum {
    
    private static long getFibonacciSum(long n) {
        if (n < 1)
            return 0;
        
        int[] p = new int[100];
        
        int a = 1, b = 0;
        int temp;
        int sum = a + b;
        
        p[0] = 0;
        p[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            temp = (a + b) % 10;
            b = a;
            a = temp;
            sum = (sum + temp) % 10;
            
            p[i] = sum;
            if (p[i] == 1 && p[i - 1] == 0) break;
        }

        return p[(int) (n % 60)];
    }
    
    private static long getFibonacciPartialSum(long from, long to) {
        return (10 + getFibonacciSum(to) - getFibonacciSum(from - 1)) % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));

//        System.out.println(getFibonacciPartialSum(3, 7));
//        System.out.println(getFibonacciPartialSum(10, 10));
//        System.out.println(getFibonacciPartialSum(10, 200));
    }
}

