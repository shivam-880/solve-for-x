package com.codingkapoor.merchantsguidetogalaxy;

import java.util.HashMap;
import java.util.Map;

public class RomanToDecimalConvertor {

	private static final Map<Character, Integer> ROMAN_TO_DECIMAL_NUMERALS_MAPPING = new HashMap<>();

	static {
		ROMAN_TO_DECIMAL_NUMERALS_MAPPING.put('I', 1);
		ROMAN_TO_DECIMAL_NUMERALS_MAPPING.put('V', 5);
		ROMAN_TO_DECIMAL_NUMERALS_MAPPING.put('X', 10);
		ROMAN_TO_DECIMAL_NUMERALS_MAPPING.put('L', 50);
		ROMAN_TO_DECIMAL_NUMERALS_MAPPING.put('C', 100);
		ROMAN_TO_DECIMAL_NUMERALS_MAPPING.put('D', 500);
		ROMAN_TO_DECIMAL_NUMERALS_MAPPING.put('M', 1000);
	}

	public static int convert(String roman) {
		char[] romanCharArr = roman.toCharArray();

		int nextDecimalEq, nextNextDecimalEq, decimalEq = 0, i;
		for (i = 0; i < romanCharArr.length - 1; i++) {
			nextDecimalEq = ROMAN_TO_DECIMAL_NUMERALS_MAPPING.get(romanCharArr[i]);
			nextNextDecimalEq = ROMAN_TO_DECIMAL_NUMERALS_MAPPING.get(romanCharArr[i + 1]);
			if (nextDecimalEq < nextNextDecimalEq) {
				decimalEq += (nextNextDecimalEq - nextDecimalEq);
				i += 1;
			} else {
				decimalEq += nextDecimalEq;
			}
		}
		if (i <= romanCharArr.length - 1) {
			decimalEq += ROMAN_TO_DECIMAL_NUMERALS_MAPPING.get(romanCharArr[romanCharArr.length - 1]);
		}

		return decimalEq;
	}
	
}
