package com.oriaxx77.hackersranksolutions.algorithms.strings;

import java.util.Scanner;



/**
 * 
 * https://www.hackerrank.com/challenges/pangrams
Roy wanted to increase his typing speed for programming contests. So, his friend advised him to type the sentence "The quick brown fox jumps over the lazy dog" repeatedly, because it is a pangram. (Pangrams are sentences constructed by using every letter of the alphabet at least once.)

After typing the sentence several times, Roy became bored with it. So he started to look for other pangrams.

Given a sentence , tell Roy if it is a pangram or not.

Input Format

Input consists of a string .

Constraints 
Length of  can be at most   and it may contain spaces, lower case and upper case letters. Lower-case and upper-case instances of a letter are considered the same.

Output Format

Output a line containing pangram if  is a pangram, otherwise output not pangram.

Sample Input

Input #1

We promptly judged antique ivory buckles for the next prize    
Input #2

We promptly judged antique ivory buckles for the prize    
Sample Output

Output #1

pangram
Output #2

not pangram
Explanation

In the first test case, the answer is pangram because the sentence contains all the letters of the English alphabet.
 */
public class Pangrams {

	public static boolean isPangram(String s) {
		boolean[] charPresents = new boolean[26];
		char[] chars = s.toLowerCase().replaceAll( "\\s+", "" ).toCharArray();
		for ( char c : chars ){
			charPresents[c-97]=true;
		}
		
		for ( boolean charPresent : charPresents ){
			if ( !charPresent ){
				return false;
			}
		}
		return true;
    }
		
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
		if ( isPangram( string ) ) {
			System.out.println( "pangram" );
		} else {
			System.out.println( "not pangram");
		}	
	}

}
