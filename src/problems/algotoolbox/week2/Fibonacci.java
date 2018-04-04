package problems.algotoolbox.week2;

import java.util.Scanner;

public class Fibonacci {
    private static long calc_fib(int n) {
        if (n == 0) return 0;
        int a = 0, b = 1;
        int c;
        for (int i = 2; i <= n; i++) {
            a += b;
            c = b;
            b = a;
            a = c;
        }
        return b;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calc_fib(n));
        
//        for (int i = 0; i < 10; i++) System.out.println(i + ": " + calc_fib(i));
    }
}