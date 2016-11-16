package com.codingkapoor.merchantsguidetogalaxy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.codingkapoor.merchantsguidetogalaxy.util.PropertyReader;
import com.codingkapoor.merchantsguidetogalaxy.util.RomanNumberValidator;
import com.codingkapoor.merchantsguidetogalaxy.util.UserInputFileReader;

public class Translator {

	private List<String> userInputAsListOfStrings;

	private PropertyReader inputProperties;
	private PropertyReader outputProperties;

	private Map<String, String> translationsMap;
	private Map<String, Float> creditsMap;

	private static final String FLOATING_POINT_NUMBER_REGEX = "[-+]?(\\d*[.])?\\d+";

	// Initializes map that maintains alien to roman numerals mapping.
	private void prepareTranslationsMap() {
		translationsMap = new HashMap<String, String>();
		for (String[] strArray : parseUserInput(inputProperties.getProperty("alien.to.roman.numerals.mapping.format"))) {
			StringTokenizer lhs = new StringTokenizer(strArray[0]);
			StringTokenizer rhs = new StringTokenizer(strArray[1]);
			if (rhs.countTokens() == 1) {
				if (RomanNumberValidator.isValidRomanNumeral(strArray[1])) {
					if (lhs.countTokens() == 1) {
						translationsMap.put(strArray[0], strArray[1]);
						userInputAsListOfStrings.remove(strArray[2]);
					} else {
						throw new RuntimeException(MessageFormat.format(outputProperties.getProperty("invalid.translations"), strArray[2]));
					}
				} else {
					throw new RuntimeException(MessageFormat.format(outputProperties.getProperty("invalid.translations"), strArray[2]));
				}
			}
		}
	}

	// Initializes map that maintains alien multiplier numerals to decimal numbers mapping.
	private void prepareCreditsMap() {
		creditsMap = new HashMap<String, Float>();
		for (String[] strArray : parseUserInput(inputProperties.getProperty("alien.numerals.to.credits.mapping.format"))) {
			String alienNumber = "";

			StringTokenizer lhs = new StringTokenizer(strArray[0]);
			StringTokenizer rhs = new StringTokenizer(strArray[1]);

			if (rhs.countTokens() == 1 && strArray[1].matches(FLOATING_POINT_NUMBER_REGEX)) {
				while (lhs.hasMoreTokens()) {
					String nextToken = lhs.nextToken();
					if (translationsMap.containsKey(nextToken)) {
						alienNumber += nextToken + " ";
					} else if (lhs.hasMoreTokens()) {
						throw new RuntimeException(MessageFormat.format(outputProperties.getProperty("invalid.translations"), strArray[2]));
					} else {
						String roman = translateAlienToRomanNumber(alienNumber);
						if (RomanNumberValidator.validate(roman)) {
							creditsMap.put(nextToken, (Float.parseFloat(strArray[1]) / RomanToDecimalConvertor.convert(roman)));
							userInputAsListOfStrings.remove(strArray[2]);
						} else 
							throw new RuntimeException(MessageFormat.format(outputProperties.getProperty("syntactically.incorrect"), strArray[2], alienNumber));
					}
				}
			} else {
				throw new RuntimeException(MessageFormat.format(outputProperties.getProperty("invalid.translations"), strArray[2]));
			}
		}
	}

	// Attempts to answer all the questions that are not related to credits.
	private void processNonCreditQuestions() {
		for (String[] strArray : parseUserInput(inputProperties.getProperty("non.credits.question.format"))) {
			userInputAsListOfStrings.remove(strArray[1]);
			String alienNumber = "";
			StringTokenizer alienTokens = new StringTokenizer(strArray[0]);
			while (alienTokens.hasMoreTokens()) {
				String nextToken = alienTokens.nextToken();
				if (translationsMap.containsKey(nextToken)) {
					alienNumber += nextToken + " ";
				} else {
					System.out.println(MessageFormat.format(outputProperties.getProperty("no.idea"), strArray[1]));
					break;
				}
			}
			if (!alienNumber.isEmpty()) {
				String roman = translateAlienToRomanNumber(alienNumber);
				if (RomanNumberValidator.validate(roman)) {
					System.out.println(strArray[1] + " " + RomanToDecimalConvertor.convert(roman));
				} else {
					System.out.println(MessageFormat.format(outputProperties.getProperty("syntactically.incorrect"), strArray[1], alienNumber));
				}
			}
		}
	}

	// Attempts to answer all the questions related to credits.
	private void processCreditQuestions() {
		for (String[] strArray : parseUserInput(inputProperties.getProperty("credits.question.format"))) {
			userInputAsListOfStrings.remove(strArray[1]);
			String alienNumber = "";
			StringTokenizer alienTokens = new StringTokenizer(strArray[0]);
			while (alienTokens.hasMoreTokens()) {
				String nextToken = alienTokens.nextToken();
				if (!creditsMap.containsKey(nextToken)) {
					if (translationsMap.containsKey(nextToken)) {
						alienNumber += nextToken + " ";
					} else {
						System.out.println(MessageFormat.format(outputProperties.getProperty("no.idea"), strArray[1]));
						break;
					}
				} else if (alienTokens.hasMoreTokens()) {
					System.out.println(MessageFormat.format(outputProperties.getProperty("no.idea"), strArray[1]));
					break;
				} else {
					String roman = translateAlienToRomanNumber(alienNumber);
					if (RomanNumberValidator.validate(roman)) {
						System.out.println(MessageFormat.format(outputProperties.getProperty("credits.questionaire"),
								strArray[1], RomanToDecimalConvertor.convert(roman) * creditsMap.get(nextToken)));
					} else {
						System.out.println(MessageFormat.format(outputProperties.getProperty("syntactically.incorrect"),
								strArray[1], alienNumber));
					}
				}
			}
		}
	}

	// Attempts to answer all the invalid questions.
	private void processInvalidQuestions() {
		for (String invalidQuestions : userInputAsListOfStrings) {
			System.out.println(MessageFormat.format(outputProperties.getProperty("no.idea"), invalidQuestions));
		}
	}

	private String translateAlienToRomanNumber(String alienNumber) {
		StringTokenizer stringTokenizer = new StringTokenizer(alienNumber);

		String romanNumber = "";
		while (stringTokenizer.hasMoreElements()) {
			romanNumber += translationsMap.get(stringTokenizer.nextElement());
		}

		return romanNumber;
	}

	private List<String[]> parseUserInput(String format) {
		List<String[]> parsedInputList = new ArrayList<>();
		MessageFormat messageFormat = new MessageFormat(format);

		String currentLine = "";
		Iterator<String> iterator = userInputAsListOfStrings.iterator();

		while (iterator.hasNext()) {
			currentLine = iterator.next();
			Object[] parsedObjects;
			try {
				parsedObjects = messageFormat.parse(currentLine.trim().replaceAll("\\s+", " "));
				String[] parsedStrings = Arrays.copyOf(parsedObjects, parsedObjects.length + 1, String[].class);
				parsedStrings[parsedObjects.length] = currentLine;
				parsedInputList.add(parsedStrings);
			} catch (ParseException ignored) {
			}
		}
		
		return parsedInputList;
	}

	public void translate() {
		prepareTranslationsMap();
		prepareCreditsMap();
		
		processNonCreditQuestions();
		processCreditQuestions();
		processInvalidQuestions();
	}

	public Translator(String filePath) throws IOException, URISyntaxException {

		UserInputFileReader reader = new UserInputFileReader();
		userInputAsListOfStrings = reader.read(filePath);

		inputProperties = new PropertyReader("input.properties");
		outputProperties = new PropertyReader("output.properties");


	}

	public static void main(String[] args) throws IOException, URISyntaxException {

		Translator translator = new Translator((args.length > 0) ? args[0] : null);
		translator.translate();
	}

}
