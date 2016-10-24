package com.oriaxx77.hackersranksolutions.algorithms.strings;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



/**
 * Challenge Url: https://www.hackerrank.com/challenges/two-characters
 * 
 * Idea:
 * 
 * 1. Create a Map that contains all characters with their positions in the string. e.g. in bananba a -> 1,3,6, b -> 0,5, n-> 2,4
 * 2. Calculate the alternating string length for all char pairs. 
 * 3. Get the max from the alternating string lengths
 */
public class TwoCharacters {
	
	public long longestAlternatingCharacterPairs(String s) {
		
        if ( baseCase( s ) ){
        	return 0;
        }                
        
        Map<Character,List<Integer>> characterPositionsMap = createCharacterPositionsMap( s );        
        List<Integer> alternatingCharacterPairsLengths = getCharacterPairAlternatingsLengths( characterPositionsMap.entrySet() );
        return getMax( alternatingCharacterPairsLengths );
    }

	
	/**
	 * Empty string or null is base case. Return 0.
	 */
	private static boolean baseCase( String s ){
		return s == null || s.length() == 0;
	}
	
	
	/**
	 * Create a Map that contains all characters with their positions in the string. e.g. in bananba a -> 1,3,6, b -> 0,5, n-> 2,4 
	 */
	private static Map<Character,List<Integer>> createCharacterPositionsMap( String s ) {
		return IntStream.range(0, s.length())
				 .mapToObj( i -> new AbstractMap.SimpleEntry<Character, Integer>( s.charAt(i), i) )
				 .collect( Collectors.groupingBy( Entry::getKey, Collectors.mapping( Entry::getValue, Collectors.toList()) ) );		
	}
	
	
	/** 
	 * Calculate the alteration string length for all char pairs. 
	 */
	private static List<Integer> getCharacterPairAlternatingsLengths( Set<Entry<Character,List<Integer>>> orderedCharPositionsList){
		List<Integer> altnationCounts = new ArrayList<Integer>();
		for ( Entry<Character,List<Integer>> charPos1 : orderedCharPositionsList ) {
        	for ( Entry<Character,List<Integer>> charPos2: orderedCharPositionsList ){
        		altnationCounts.add( getCharacterPairAlternatingLength( charPos1, charPos2 ) );
        	}
        }
		return altnationCounts;
	}
	
	/**
	 * Get the alteration counts list and returns with the max element.
	 */
	private static int getMax( List<Integer> alternatingStringsLengths ){
		return alternatingStringsLengths.stream().max( Integer::compareTo ).orElse(0);
	}
	
	/**
	 * Calculate the alternating string length for a character pair.
	 */
	private static int getCharacterPairAlternatingLength( Entry<Character,List<Integer>> characterPositions1, Entry<Character,List<Integer>> characterPositions2){		
		
		if ( impossibleAlternatings(characterPositions1, characterPositions2) ) {
			return 0;
		}
		
		List<Entry<Character,List<Integer>>> pullOrder = definePullOrder( characterPositions1, characterPositions2 );		
		Entry<Character,List<Integer>> firstEntry = pullOrder.get(0);
		Entry<Character,List<Integer>> secondEntry = pullOrder.get(1);
				
				  
				
		int i = 0;
		int longerIdx = 0;
		int shorterIdx = 0;
		int prevPos = -1;
		
		while ( i < firstEntry.getValue().size() + secondEntry.getValue().size() ) {
			int nextPos;
			if ( i % 2 == 0 ) { // Pull from the longer
				nextPos = firstEntry.getValue().get( longerIdx++ );
			} else { // Pull from the shorter
				nextPos = secondEntry.getValue().get( shorterIdx++ );
			}
			
			if ( prevPos > nextPos ) {
				return -1;
			}
			prevPos = nextPos;
			i++;
		}
		
		return firstEntry.getValue().size() + secondEntry.getValue().size() ;
	}
	
	/**
	 * Returns true if the alternatings impossible:
	 * - The characters are  the same
	 * - The differences of occurences of the two characters are higher than 1. 
	 */
	private static boolean impossibleAlternatings( Entry<Character,List<Integer>> charPositions1, Entry<Character,List<Integer>> charPositions2 ) {
		return ( charPositions1.getKey().equals( charPositions2.getKey()) ) ||
				( Math.abs( charPositions1.getValue().size()-charPositions2.getValue().size() ) > 1 );  
	}
	
	/**
	 * Defines the pull order of the two character-positions.
	 * The one with more occurrences should be the first.
	 */
	private static List<Entry<Character,List<Integer>>> definePullOrder( Entry<Character,List<Integer>> characterPositions1, Entry<Character,List<Integer>> characterPositions2 ) {
		Entry<Character,List<Integer>> firstEntry;
		Entry<Character,List<Integer>> secondEntry;
		if ( characterPositions1.getValue().size() > characterPositions2.getValue().size() ){
			firstEntry = characterPositions1;
			secondEntry = characterPositions2;
		} else if ( characterPositions1.getValue().size() < characterPositions2.getValue().size() ){
			firstEntry = characterPositions2;
			secondEntry = characterPositions1;
		} else { // ==
			if ( characterPositions1.getValue().get(0) < characterPositions2.getValue().get(0) ) {
				firstEntry = characterPositions1;
				secondEntry = characterPositions2;
			} else {
				firstEntry = characterPositions2;
				secondEntry = characterPositions1;
			}
		}
		return Arrays.asList( firstEntry, secondEntry );
	}
			
	  		
	
	
	
	/**
	 * Static entry point of the app. Get the data from the command line.
	 * See https://www.hackerrank.com/challenges/two-characters 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
        String string = scan.nextLine();
		System.out.println( new TwoCharacters().longestAlternatingCharacterPairs( string ) );	
	}

}
