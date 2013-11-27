package com.felix.math.master;

import java.util.Random;

public class RandomGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(randInt(1, -1));
	}

	/**
	 * Returns a psuedo-random number between min and max, inclusive. The difference between min and max can be at most <code>Integer.MAX_VALUE - 1</code>.
	 * Swaps if first number is greater than second number
	 * 
	 * @param min Minimim value
	 * @param max Maximim value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static Random random = new Random();

	public static int randInt(int min, int max) {
		if (max < min) {
			// swapping
			min = min + max;
			max = min - max;
			min = min - max;
		}
		// nextInt is normally exclusive of the top value, so add 1 to make it inclusive
		int randomNum = random.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
