package com.felix.math.master;

public class CheckNumberType {
	
	public static void main(String[] args) {
		
		System.out.println(isComplex("309+432i"));
	    String[] attempts = new String[]{"300+400i", "4i+2", "5000-324i", "555", "2i"};
	    String s[] = "300-400i".split("[\\Q+-\\Ei]");

	    System.out.println(s[0]+" "+s[1]); //prints 300 400
//		System.out.println(isComposite(7));
//		System.out.println(isPrime(0));
//		System.out.println(isNatural(1));
	}
	
	public static boolean isInteger(long n){
		return true;
	}
	

	//TODO Complete
	public static boolean isComplex(String str) {
		try{
			Long.parseLong(str);
			return false;
		}catch (Exception e) {

			System.out.println(str);
			System.out.println(str.substring(0, str.indexOf('+')));
			System.out.println(str.substring(str.indexOf('+')+1,str.indexOf("i")));
			//String s[] = "300-400i".split("[\\Q+-\\Ei]");
			//System.out.println(s[0]+" "+s[1]); //prints 300 400
			return true;
		}
	}
	public static boolean isPrime(long n) {
	    if(n < 2) return false;
	    if(n == 2 || n == 3) return true;
	    if(n%2 == 0 || n%3 == 0) return false;
	    long sqrtN = (long)Math.sqrt(n)+1;
	    for(long i = 6L; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) return false;
	    }
	    return true;
	}
	
	public static boolean isComposite(long n){
		return !isPrime(n);
	}
	
	public static boolean isNatural(long n){
		if(n>0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isWhole(long n){
		if(n>=0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isWhole(String s){		
	    try { 
	        long n = Long.parseLong(s); 
	        if(n>=0){
				return true;
			}
			else{
				return false;
			}
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
		
		
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	//Java 1.4+. No imports needed.
	/*public static boolean isPrime(int n) {
	    return !new String(new char[n]).matches(".?|(..+?)\\1+");
	}*/
}
