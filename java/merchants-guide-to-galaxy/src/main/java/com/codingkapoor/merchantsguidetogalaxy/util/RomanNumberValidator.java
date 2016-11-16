package com.codingkapoor.merchantsguidetogalaxy.util;

import com.codingkapoor.merchantsguidetogalaxy.DecimalToRomanConvertor;
import com.codingkapoor.merchantsguidetogalaxy.RomanToDecimalConvertor;

public class RomanNumberValidator {

	public static boolean isValidRomanNumeral(final String roman) {
		return roman.matches("I|V|X|L|C|D|M");
	}

	// Contains letters only from I, V, X, L, C, D, M.
	private static boolean containsOnlyValidRomanNumerals(final String roman) {
		return !roman.matches(".*[^IVXLCDM].*");
	}

	// "D", "L", and "V" can never be repeated.
	private static boolean isNonRepetitiveNumeralsRepeated(final String roman) {
		return roman.matches(".*D{2,}.*|.*L{2,}.*|.*V{2,}.*");
	}

	// The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more.
	private static boolean isRepetitiveNumeralsRepeatedMoreThanThrice(final String roman) {
		return roman.matches(".*I{4,}.*|.*X{4,}.*|.*C{4,}.*|.*M{4,}.*");
	}

	private static boolean isIncorrectSubstractiveNotation(final String roman) {
		return roman.matches(".*IL.*|.*IC.*|.*ID.*|.*IM.*|.*XD.*|.*XM.*");
	}

	public static boolean validate(final String roman) {
		
		String derivedRoman = DecimalToRomanConvertor.convert(RomanToDecimalConvertor.convert(roman));
		
		return (containsOnlyValidRomanNumerals(roman) && derivedRoman.equals(roman) &&
				!isNonRepetitiveNumeralsRepeated(roman) && 
				!isRepetitiveNumeralsRepeatedMoreThanThrice(roman) && 
				!isIncorrectSubstractiveNotation(roman));
	}

}
