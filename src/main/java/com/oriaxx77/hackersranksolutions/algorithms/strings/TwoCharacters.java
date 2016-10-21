package com.oriaxx77.hackersranksolutions.algorithms.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;



/**
 * Challenge Url: https://www.hackerrank.com/challenges/two-characters
 * 
 * Idea:
 * 
 * 1. Create a Map that contains all characters with their positions in the string. e.g. in bananba a -> 1,3,6, b -> 0,5, n-> 2,4
 * 2. Calculate the alteration string length for all char pairs. 
 * 3. Get the max from the alteration string length
 * 
 * Sample:
 * 
 * input:
 * //141
		//cwomzxmuelmangtosqkgfdqvkzdnxerhravxndvomhbokqmvsfcaddgxgwtpgpqrmeoxvkkjunkbjeyteccpugbkvhljxsshpoymkryydtmfhaogepvbwmypeiqumcibjskmsrpllgbvc
		
		// Output: 8
 */
public class TwoCharacters {
	
	public static long twoCharacters(String s) {
		
        if ( basecase( s ) ){
        	return 0;
        }                
        
        Map<Character,List<Integer>> charPositionsMap = createCharPositionsMap( s );        
        
        return findBestCaractersForAlterations( charPositionsMap.entrySet() );
    }

	private static boolean basecase( String s ){
		return s == null || s.length() == 0;
	}
	
	private static int findBestCaractersForAlterations( Set<Entry<Character,List<Integer>>> orderedCharPositionsList){
		List<Integer> alterationCounts = new ArrayList<Integer>();
		for ( Entry<Character,List<Integer>> charPos1 : orderedCharPositionsList ) {
        	for ( Entry<Character,List<Integer>> charPos2: orderedCharPositionsList ){
        		alterationCounts.add( calcAlterations( charPos1, charPos2 ) );
        	}
        }
		
		Collections.sort( alterationCounts );
		return alterationCounts.isEmpty() ? 0: alterationCounts.get( alterationCounts.size()-1 ); 
		
	}
	
	
	private static int calcAlterations( Entry<Character,List<Integer>> charPos1, Entry<Character,List<Integer>> charPos2 ){
		
		
		if ( !charPos1.getKey().equals( charPos2.getKey())){ // Ignore same keys
			if ( Math.abs( charPos1.getValue().size()-charPos2.getValue().size() ) <= 1)  { // Size checking. Must be 0,1 difference
				
				Entry<Character,List<Integer>> longer;
				Entry<Character,List<Integer>> shorter;
				if ( charPos1.getValue().size() > charPos2.getValue().size() ){
					longer = charPos1;
					shorter = charPos2;
				} else if ( charPos1.getValue().size() < charPos2.getValue().size() ){
					longer = charPos2;
					shorter = charPos1;
				} else { // ==
					if ( charPos1.getValue().get(0) < charPos2.getValue().get(0) ) {
						longer = charPos1;
						shorter = charPos2;
					} else {
						longer = charPos2;
						shorter = charPos1;
					}
				}
				  
				
				int i = 0;
				int longerIdx = 0;
				int shorterIdx = 0;
				int prevPos = -1;
				
				while ( i < longer.getValue().size() + shorter.getValue().size() ) {
					int nextPos;
					if ( i % 2 == 0 ) { // Pull from the longer
						nextPos = longer.getValue().get( longerIdx++ );
					} else { // Pull from the shorter
						nextPos = shorter.getValue().get( shorterIdx++ );
					}
					
					if ( prevPos > nextPos ) {
						return -1;
					}
					prevPos = nextPos;
					i++;
				}
				
				return longer.getValue().size() + shorter.getValue().size() ;
			}
		}
		
		return 0;
		
	}
	
	
	private static Map<Character,List<Integer>> createCharPositionsMap( String s ) {
		Map<Character,List<Integer>> charPositionsMap = new HashMap<>();
        for ( int i=0; i < s.length(); i++ ) {
        	Character c = s.charAt( i );
        	if ( charPositionsMap.containsKey( c )) {
        		charPositionsMap.get( c ).add( i );
        	} else {
        		charPositionsMap.put( c, new ArrayList<Integer>( Arrays.asList( i ) ) );
        	}
        }
        return charPositionsMap;
	}
	
	
	
	public static void main(String[] args) {
		
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
        String string = scan.nextLine();
		System.out.println( twoCharacters( string ) );	
	}

}
