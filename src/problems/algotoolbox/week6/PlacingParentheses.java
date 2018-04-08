package problems.algotoolbox.week6;
import java.util.Scanner;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {
        int n = exp.length() / 2 + 1;
        long[] value = new long[n];
        char[] operator = new char[n - 1];

        for (int i = 0; i < exp.length(); i++) {
            if (i % 2 != 0)
                operator[i / 2] = exp.charAt(i);
            else
                value[i / 2] = exp.charAt(i) - '0';
        }

        long[][] min = new long[n][n];
        long[][] max = new long[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                min[i][j] = Long.MAX_VALUE;
                max[i][j] = Long.MIN_VALUE;
            }
        }
        
        for (int i = 0; i < n; i++) {
            min[i][i] = max[i][i] = value[i];
        }
        
        for (int s = 0; s < n; s++) {
            for (int i = 0, j = i + s; i < n - s; i++, j++) {
                for (int k = i; k <= j - 1; k++) {
                    long a = eval(max[i][k], max[k + 1][j], operator[k]);
                    long b = eval(max[i][k], min[k + 1][j], operator[k]);
                    long c = eval(min[i][k], max[k + 1][j], operator[k]);
                    long d = eval(min[i][k], min[k + 1][j], operator[k]);
                    min[i][j] = min(a, b, c, d, min[i][j]);
                    max[i][j] = max(a, b, c, d, max[i][j]);
                }
            }
        }

        return max[0][n - 1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }
    
    private static long min(long a, long b, long c, long d, long x) {
        long min = a;
        if (min > b) min = b;
        if (min > c) min = c;
        if (min > d) min = d;
        if (min > x) min = x;
        return min;
    }
    
    private static long max(long a, long b, long c, long d, long x) {
        long max = a;
        if (max < b) max = b;
        if (max < c) max = c;
        if (max < d) max = d;
        if (max < x) max = x;
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
        
//        System.out.println(getMaximValue("5-8+7*4-8+9"));
    }
}
