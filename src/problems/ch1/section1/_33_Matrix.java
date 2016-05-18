package problems.ch1.section1;

import java.security.InvalidParameterException;

/**
 * 1.1.33 Matrix library. Write a library Matrix that implements the following
 * API: public class Matrix static double dot(double[] x, double[] y) vector dot
 * product static double[][] mult(double[][] a, double[][] b) matrix-matrix
 * product static double[][] transpose(double[][] a) transpose static double[]
 * mult(double[][] a, double[] x) matrix-vector product static double[]
 * mult(double[] y, double[][] a) vector-matrix product
 * 
 * @author Theodore
 *
 */
public class _33_Matrix {

    static double dot(double[] x, double[] y) {
        if (x == null || y == null || x.length != y.length) throw new InvalidParameterException();
        double value = 0.0f;
        for (int i = 0; i < x.length; i++) value += x[i] * y[i];
        return value;
    }

    static double[][] mult(double[][] a, double[][] b) {
        if (a == null || b == null || a[0].length != b.length) throw new InvalidParameterException();
        double[][] value = new double[a.length][b[0].length];
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[0].length; j++) {
                value[i][j] = 0;
                for (int k = 0; k < a[0].length; k++) value[i][j] += a[i][k] * b[k][j];
            }
        }
        return value;
    }

    static double[][] transpose(double[][] a) {
        if (a == null) throw new InvalidParameterException();
        double[][] b = new double[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) b[j][i] = a[i][j];
        }
        return b;
    }

    static double[] mult(double[][] a, double[] x) {
        if (a == null || x == null) throw new InvalidParameterException();
        double[][] b = new double[x.length][1];
        for (int i = 0; i < x.length; i++) {
            b[i][0] = x[i];
        }
        return transpose(mult(a, b))[0];
    }

    static double[] mult(double[] y, double[][] a) {
        if (a == null || y == null) throw new InvalidParameterException();
        double[][] b = new double[1][y.length];
        for (int i = 0; i < y.length; i++) {
            b[0][i] = y[i];
        }
        return mult(b, a)[0];
    }
    
    private static void print(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                System.out.print(String.format("%.1f\t", a[i][j]));
            System.out.println();
        }
    }
    
    private static void print(double[] a) {
        for (int i = 0; i < a.length; i++) System.out.print(String.format("%.1f\t", a[i]));
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        double[] x = new double[] {1.0, 2.0, 3.0};
        double[] y = new double[] {3.0, 3.0, -3.0};
        
        double[][] a = new double[][] {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        
//        double[][] a = new double[][] {
//                {1.0, 2.0},
//                {4.0, 3.0},
//                {5.0, 6.0},
//        };
        
        print(a);
        System.out.println();
        
        print(transpose(a));
        System.out.println();
        
        System.out.println(dot(x, y));
        System.out.println();

        print(mult(a, transpose(a)));
        System.out.println();
        print(mult(transpose(a), a));
        System.out.println();
        
        print(mult(x, a));
        System.out.println();
        
        print(mult(transpose(a), x));
        System.out.println();
    }

}
