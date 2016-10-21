package com.oriaxx77.hackersranksolutions.algorithms.strings;

import java.util.Scanner;



/**
 * 
 * https://www.hackerrank.com/challenges/beautiful-binary-string
 * 
Alice has a binary string, , of length . She thinks a binary string is beautiful if and only if it doesn't contain the substring .

In one step, Alice can change a  to a  (or vice-versa). Count and print the minimum number of steps needed to make Alice see the string as beautiful.

Input Format

The first line contains an integer,  (the length of binary string ). 
The second line contains a single binary string, , of length .

Constraints

Each character in .
Output Format

Print the minimum number of steps needed to make the string beautiful.

Sample Input 0

7
0101010
Sample Output 0

2
Sample Input 1

5
01100
Sample Output 1

0
Sample Input 2

10
0100101010
Sample Output 2

3
Explanation

Sample Case 0:

In this sample, 

The figure below shows a way to get rid of each instance of :

binary.png

Because we were able to make the string beautiful by changing  characters ( and ), we print .

Sample Case 1:

In this sample 

The substring  does not occur in , so the string is already beautiful and we print . *
 */
public class BeautifulBinaryString {

	// The idea is to change 010 to 011
	public static int noOfChangesToBeBeautiful(String s) {
		
		if ( s.length() <= 2 )
			return 0;
		
		int changesCount = 0;		
		char[] chars = s.toCharArray();
		char[] beautifulChars = new char[s.length()];
		System.arraycopy( chars, 0, beautifulChars, 0, 2);
		for ( int i = 2; i < chars.length; i++ ) {
			if ( beautifulChars[i-2] == '0' &&  beautifulChars[i-1] == '1' &&  chars[i] == '0' ){
				beautifulChars[i] = '1';
				changesCount++;
			} else {
				beautifulChars[i] = chars[i];
			}
		}
		System.out.println( chars );
		System.out.println( beautifulChars );
		return changesCount;
    }
		
	
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		scan.nextLine(); // len, not used
        String string = scan.nextLine();
		
		System.out.println(  noOfChangesToBeBeautiful( string ) );	
	}

}
