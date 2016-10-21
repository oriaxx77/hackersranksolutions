package com.oriaxx77.hackersranksolutions.algorithms.strings;

import java.util.Scanner;



/**
 * Idea: Treat the string chars array as a stack where you push or pop chars.
 *
 */
public class ReducedString {

	public static String removeAdjacentCharPairs(String s) {
        char[] chars = s.toCharArray(); 
        int stackPointer = 0;  

        for ( int i = 0; i < chars.length; i++) {
            if ( stackPointer == 0 || chars[i] != chars[stackPointer - 1]) {
                chars[stackPointer++] = chars[i]; 
            } else {
            	stackPointer--;                     
            }
        }
        return new String(chars, 0, stackPointer);
    }
		
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
		String result = removeAdjacentCharPairs( string );
		String resultToPrint = "".equals( result ) ? "Empty String" : result;
		System.out.println( resultToPrint );
	}

}
