package problems.algotoolbox.week2;

import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSum(long n) {
        if (n < 1)
            return 0;
        
        int[] p = new int[100];
        
        int a = 1, b = 0;
        int temp;
        int sum = a + b;
        
        p[0] = 0;
        p[1] = 1;
        
        int count = 1;
        
        for (int i = 2; i <= n; i++) {
            temp = (a + b) % 10;
            b = a;
            a = temp;
            sum = (sum + temp) % 10;
            
            p[i] = sum;
            if (p[i] == 1 && p[i - 1] == 0) break;
            count++;
        }

        return p[(int) (n % 60)];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSum(n);
        System.out.println(s);

//        System.out.println(getFibonacciSum(0));
//        System.out.println(getFibonacciSum(1));
//        System.out.println(getFibonacciSum(2));
//        System.out.println(getFibonacciSum(3));
//        System.out.println(getFibonacciSum(100));
        
//        for (int i = 0; i < 200; i++)
//            System.out.println(i + ": " + getFibonacciSum(i));
    }
}

