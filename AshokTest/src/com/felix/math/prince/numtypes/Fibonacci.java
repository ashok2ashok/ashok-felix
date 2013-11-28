package com.felix.math.prince.numtypes;
/*************************************************************************
 *  Compilation:  javac Fibonacci.java
 *  Execution:    java Fibonacci N
 *
 *  Compute Fibonacci number using Dijkstra's recurrence:
 *    F(2N-1)  = F(N-1)^2 + F(N)^2
 *    F(2N)    = (2 F(N-1) + F(N)) F(N)
 *
 *  Reference: http://www.cs.utexas.edu/users/EWD/ewd06xx/EWD654.PDF
 *  Reference: http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibFormula.html
 *
 *************************************************************************/

import java.util.HashMap;
import java.math.BigInteger;

public class Fibonacci {
    private static HashMap<BigInteger, BigInteger> cache = new HashMap<BigInteger, BigInteger>();
    private static BigInteger TWO  = new BigInteger("2");
    private static BigInteger ONE  = BigInteger.ONE;
    private static BigInteger ZERO = BigInteger.ZERO;

    public static BigInteger fibonacci(BigInteger n) {
        if (n.equals(ZERO)) return ZERO;
        if (n.equals(ONE))  return ONE;
        if (cache.containsKey(n)) return cache.get(n);

        // odd
        if (n.testBit(0)) {
            BigInteger n2 = n.shiftRight(1);
            BigInteger n3 = n2.add(ONE);
            BigInteger result = fibonacci(n2).multiply(fibonacci(n2)).add(fibonacci(n3).multiply(fibonacci(n3)));
            cache.put(n, result);
            return result;
        }

        // even
        else {
            BigInteger n2 = n.shiftRight(1);
            BigInteger n3 = n2.subtract(ONE);
            BigInteger result = fibonacci(n2).multiply(fibonacci(n2).add(fibonacci(n3).add(fibonacci(n3))));
            cache.put(n, result);
            return result;
        }
    }

    public static void main(String[] args) { 
        BigInteger N = new BigInteger(args[0]);
        System.out.println(fibonacci(N));
    }
}