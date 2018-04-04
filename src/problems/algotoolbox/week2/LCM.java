package problems.algotoolbox.week2;

import java.util.*;

public class LCM {
    private static long gcd(int a, int b) {
        int temp;
        while (a % b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }

    private static long lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(lcm(a, b));

//        System.out.println(lcm(6, 8));
//        System.out.println(lcm(28851538, 1183019));
//        System.out.println(lcm(14159572, 63967072));
    }
}
