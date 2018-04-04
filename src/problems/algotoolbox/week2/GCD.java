package problems.algotoolbox.week2;

import java.util.*;

public class GCD {
    private static int gcd(int a, int b) {
        int temp;
        while (a % b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(gcd(a, b));

//        System.out.println(gcd(18, 35));
//        System.out.println(gcd(28851538, 1183019));
    }
}
