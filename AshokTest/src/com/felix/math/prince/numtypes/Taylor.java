package com.felix.math.prince.numtypes;
/*************************************************************************
 *  Compilation:  javac Taylor.java
 *  Execution:    java Taylor N
 *  Dependencies: RatPoly.java
 *
 *  Taylor series of
 *
 *     e^x     = 1 + x + x^2/2! + x^3/3! + x^4/4! + ...
 *   sin x     = x - x^3/3! + x^5/5! - x^7/7! + ...
 *   e^x sin x = x + x^2 + x^3/3 -x^5/30 -x^6/90 + ...
 *
 *  % java Taylor 5
 *  e^x = 1/24 x^4 + 1/6 x^3 + 1/2 x^2 + 1 x + 1
 *  sin(x) = -1/6 x^3 + 1 x
 *  e^x sin(x) = -1/24 x^5 + 1/3 x^3 + 1 x^2 + 1 x
 *
 *************************************************************************/

public class Taylor {

    // return n!
    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n-1);
    }


    public static void main(String[] args) { 

        // number of terms
        int N = Integer.parseInt(args[0]);

        // Taylor series for e^x
        RatPoly e = RatPoly.ZERO;
        for (int i = 0; i < N; i++) {
            BigRational coef = new BigRational(1, factorial(i));
            RatPoly term = new RatPoly(coef, i);
            e = e.plus(term);
        }
        System.out.println("e^x = " + e);

        // Taylor series for sin(x)
        RatPoly sin = RatPoly.ZERO;
        for (int i = 0; i < N; i++) {
            BigRational coef;
            if      (i % 4 == 0) coef = BigRational.ZERO;
            else if (i % 4 == 1) coef = new BigRational(1, factorial(i));
            else if (i % 4 == 2) coef = BigRational.ZERO;
            else                 coef = new BigRational(-1, factorial(i));
            RatPoly term = new RatPoly(coef, i);
            sin = sin.plus(term);
        }
        System.out.println("sin(x) = " + sin);

        System.out.println("e^x sin(x) = " + e.times(sin).truncate(N));

   }

}