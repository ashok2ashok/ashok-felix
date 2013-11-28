package com.felix.math.prince.numtypes;
/*************************************************************************
 *  Compilation:  javac Hermite.java
 *  Execution:    java Hermite N
 *  Dependencies: Polynomial.java
 *
 *  Print out the first N Hermite polynomials.
 *
 *  H(0) = 1
 *  H(1) = 2x
 *  H(n) = 2x * H(n-1)  - 2(n-1) * H(n-2)
 *
 *
 *  % java Hermite 1
 *  1x^0
 *
 *  % java Hermite 2
 *  1x^0
 *  2x^1
 *
 *  % java Hermite 10
 *  1x^0
 *  2x^1
 *  4x^2 - 2x^0
 *  8x^3 - 12x^1
 *  16x^4 - 48x^2 + 12x^0
 *  32x^5 - 160x^3 + 120x^1
 *  64x^6 - 480x^4 + 720x^2 - 120x^0
 *  128x^7 - 1344x^5 + 3360x^3 - 1680x^1
 *  256x^8 - 3584x^6 + 13440x^4 - 13440x^2 + 1680x^0
 *  512x^9 - 9216x^7 + 48384x^5 - 80640x^3 + 30240x^1
 *
 *************************************************************************/

public class Hermite {

    public static void main(String[] args) { 
        int N = Integer.parseInt(args[0]);
        Polynomial[] H = new Polynomial[Math.max(2, N)];
        H[0] = new Polynomial(1, 0);   //  1
        H[1] = new Polynomial(2, 1);   //  2x

        // compute Hermite polynomials
        for (int n = 2; n < N; n++) {
            Polynomial temp1 = H[1].times(H[n-1]);
            Polynomial temp2 = new Polynomial(2 * (n-1), 0);   // 2(n-1)
            Polynomial temp3 = temp2.times(H[n-2]);
            H[n] = temp1.minus(temp3);
        }

        // print results
        for (int n = 0; n < N; n++)
            System.out.println(H[n]);
    }
}