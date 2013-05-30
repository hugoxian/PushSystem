package com.test.floats;

public class Test {
	public static void main(String[] args) {
		double d = 8;  
        long l = Double.doubleToLongBits(d);  
        System.out.println(Long.toBinaryString(l));  
        float f = 8;  
        int i = Float.floatToIntBits(f);  
        System.out.println(Integer.toBinaryString(i));  
        
        long s = (long) 1.91;
        
        System.out.println(s);
        
        System.out.println(Integer.toBinaryString(8));
	}
}
