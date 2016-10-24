package com.oriaxx77.hackersranksolutions.algorithms.strings;

import org.junit.Assert;
import org.junit.Test;


public class TwoCharactersTests {
	
	
	@Test
	public void testLongestAlternatingCharacterPairs(){
		String[] inputs = { "cwomzxmuelmangtosqkgfdqvkzdnxerhravxndvomhbokqmvsfcaddgxgwtpgpqrmeoxvkkjunkbjeyteccpugbkvhljxsshpoymkryydtmfhaogepvbwmypeiqumcibjskmsrpllgbvc" };
		Long[] expecteds = {8L};
		
		for (int i = 0; i < inputs.length; i++ ) {
			Long actual = new TwoCharacters().longestAlternatingCharacterPairs( inputs[i] );
			Assert.assertEquals( expecteds[i], actual);
		}
	}
}
