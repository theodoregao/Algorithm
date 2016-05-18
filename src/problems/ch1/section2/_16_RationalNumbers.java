package problems.ch1.section2;

import edu.princeton.cs.algs4.StdRandom;

public class _16_RationalNumbers {
    
    public static void main(String[] args) {
        Rational rational = new Rational(StdRandom.uniform(1000), StdRandom.uniform(1000));
        System.out.println(rational);
        
        // The harmonic series
        rational = new Rational(1, 1);
        for (int i = 2; i <= 10; i++) rational = rational.plus(new Rational(1, i));
        System.out.println(rational);
        
        rational = new Rational(-1, 1);
        for (int i = 2; i <= 10; i++) rational = rational.minus(new Rational(1, i));
        System.out.println(rational);
        
        rational = new Rational(1, 1);
        final int n = 10;
        final long b = -2;
        final long a = -3;
        final Rational timer = new Rational(b, a);
        for (int i = 0; i < n; i++) {
            System.out.println(rational);
            rational = rational.times(timer);
        }
        
        rational = new Rational(1, 1);
        final Rational divider = new Rational(a, b);
        for (int i = 0; i < n; i++) {
            System.out.println(rational);
            rational = rational.divides(divider);
        }
    }
    
    static long pow(int x, int y) {
        long result = 1;
        for (int i = 0; i < y; i++) result *= x;
        return result;
    }
    static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) result *= i;
        return result;
    }
    
    static class Rational {
        long numerator, denominator;
        
        Rational(long numerator, long denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            simplify();
        }
        
        Rational plus(Rational b) {
            return new Rational(numerator * b.denominator + denominator * b.numerator, denominator * b.denominator);
        }
        
        Rational minus(Rational b) {
            return new Rational(numerator * b.denominator - denominator * b.numerator, denominator * b.denominator);
        }
        
        Rational times(Rational b) {
            return new Rational(numerator * b.numerator, denominator * b.denominator);
        }
        
        Rational divides(Rational b) {
            return new Rational(numerator * b.denominator, denominator * b.numerator);
        }
        
        boolean equals(Rational that) {
            if (that == null) return false;
            return numerator == that.numerator && denominator == that.denominator;
        }
        
        public String toString() {
            return numerator + " / " + denominator + " = " + 1.0 * numerator / denominator;
        }
        
        private int gcd(long p, long q) {
            p = Math.abs(p);
            q = Math.abs(q);
            if (p < q) {
                long temp = p;
                p = q;
                q = temp;
            }
            if (q == 0) return (int) p;
            long r = p % q;
            return gcd(q, r);
        }
        
        private void simplify() {
            if (numerator < 0 && denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            int gcd = gcd(numerator, denominator);
            numerator = numerator / gcd;
            denominator = denominator / gcd;
        }
    }

}
