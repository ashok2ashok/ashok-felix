package com.felix.math.prince.numtypes;

/*************************************************************************
 *  Compilation:  javac RationalApprox.java
 *  Execution:    java RationalApprox x
 *
 *  Compute the best rational approximation to x using Stern-Brocot
 *  tree.
 *
 *  % java RationalApprox 2.71828182845904523536028747135
 *  1 2 3 5/2 8/3 11/4 19/7 49/18 68/25 87/32 106/39 193/71 685/252 878/323 ...
 *
 *  % java RationalApprox 3.14159265358979323846264338328
 *  1 2 3 13/4 16/5 19/6 22/7 179/57 201/64 223/71 245/78 267/85 289/92 311/99 333/106

 *  % java RationalApprox 
 *  0.142857
 *  1/4 1/5 1/6 1/7 71429/500004 71430/500011 ...
 *
 *  Reference: Discrete Mathematics, 116-123.
 *
 *************************************************************************/

class RationalApprox {

   public static void main(String[] args) {
      double x = Double.parseDouble(args[0]);
      double epsilon = 1E-15;
      Rational left  = new Rational(0, 1);
      Rational right = new Rational(1, 0);
      Rational best = left;
      double bestError = Math.abs(x);
      System.out.println(best + " = " + best.toDouble() + ", error = " + bestError);

      // do Stern-Brocot binary search
      while(bestError > epsilon) {

         // compute next possible rational approximation
         Rational mediant = Rational.mediant(left, right);
         if (x < mediant.toDouble())
            right = mediant;              // go left
         else
            left = mediant;              // go right

         // check if better and update champion
         double error = Math.abs(mediant.toDouble() - x); 
         if (error < bestError) {
            best = mediant;
            bestError = error;
//            System.out.println(best + " = " + best.toDouble() + ", error = " + bestError);
            System.out.print(best + " ");
         }
      }
      System.out.println();

   }

}