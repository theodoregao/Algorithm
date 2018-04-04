package problems.algotoolbox.week2;

import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitNaive(int n) {
        if (n == 0) return 0;
        int a = 0, b = 1;
        int c;
        for (int i = 2; i <= n; i++) {
            a += b;
            c = b % 10;
            b = a % 10;
            a = c % 10;
        }
        return b;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitNaive(n);
        System.out.println(c);

//        System.out.println(getFibonacciLastDigitNaive(3));
//        System.out.println(getFibonacciLastDigitNaive(331));
//        System.out.println(getFibonacciLastDigitNaive(327305));
    }
}

