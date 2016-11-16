package com.codingkapoor.merchantsguidetogalaxy;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class DecimalToRomanConvertor {

	private static final Map<Integer, String> DECIMAL_TO_ROMAN_NUMERALS_MAPPING = new TreeMap<>();

	static {
		DECIMAL_TO_ROMAN_NUMERALS_MAPPING.put(1, "I");
		DECIMAL_TO_ROMAN_NUMERALS_MAPPING.put(5, "V");
		DECIMAL_TO_ROMAN_NUMERALS_MAPPING.put(10, "X");
		DECIMAL_TO_ROMAN_NUMERALS_MAPPING.put(50, "L");
		DECIMAL_TO_ROMAN_NUMERALS_MAPPING.put(100, "C");
		DECIMAL_TO_ROMAN_NUMERALS_MAPPING.put(500, "D");
		DECIMAL_TO_ROMAN_NUMERALS_MAPPING.put(1000, "M");
	}

	public static String convert(int num) {
		int decimalPlace = 0, decimalNumber = 0;
		String roman = "";
		while (num > 0) {
			decimalNumber = (num % 10) * (int) Math.pow(10, decimalPlace);
			if (decimalNumber != 0) {
				roman = getRomanEquivalentOf(decimalNumber) + roman;
			}
			num /= 10;
			decimalPlace++;
		}
		return roman;
	}

	private static String getRomanEquivalentOf(int decimalNumber) {

		Integer[] listOfDecimals = (Integer[]) DECIMAL_TO_ROMAN_NUMERALS_MAPPING.keySet().toArray(new Integer[0]);
		int retVal = Arrays.binarySearch(listOfDecimals, decimalNumber);

		if (retVal > -1) {
			return DECIMAL_TO_ROMAN_NUMERALS_MAPPING.get(decimalNumber);
		}

		int diff = (int) Math.pow(10, String.valueOf(decimalNumber).length() - 1);

		if (((retVal * -1) - 1) > listOfDecimals.length - 1) {
			String romanEquivalent = "";
			for (int i = 0; i < decimalNumber / 1000; i++) {
				romanEquivalent += "M";
			}
			return romanEquivalent;
		}

		else {
			if (listOfDecimals[((retVal * -1) - 1)] - decimalNumber == diff) {
				return DECIMAL_TO_ROMAN_NUMERALS_MAPPING.get(diff)
						+ DECIMAL_TO_ROMAN_NUMERALS_MAPPING.get(listOfDecimals[(retVal * -1) - 1]);
			} else {
				if (((retVal * -1) - 2) > -1) {
					return DECIMAL_TO_ROMAN_NUMERALS_MAPPING.get(listOfDecimals[(retVal * -1) - 2])
							+ getRomanEquivalentOf(decimalNumber - listOfDecimals[(retVal * -1) - 2]);
				}
				return "";
			}
		}
	}
	
}
