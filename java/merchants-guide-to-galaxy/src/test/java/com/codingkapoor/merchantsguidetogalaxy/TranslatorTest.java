package com.codingkapoor.merchantsguidetogalaxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TranslatorTest {

	private Translator translator;

	private PrintWriter writer;

	private static final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private static final PrintStream printStream = new PrintStream(output);

	private File file = new File("test-user-input.txt");

	@BeforeClass
	public static void setUpStream() {
		System.setOut(printStream);
	}

	@AfterClass
	public static void cleanUpStream() throws IOException {
		System.setOut(null);
		
		output.close();
		printStream.close();
	}

	@Before
	public void setUpFile() throws IOException {
		output.reset();
		writer = new PrintWriter(file);
	}

	@After
	public void cleanUpFile() throws IOException {
		file.delete();
	}

	@Test
	public void testInvalidRomanNumerals() throws URISyntaxException, IOException {
		writer.println("glob is P");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("glob is P: Invalid translation. Please check input file.", 
					e.getMessage());
		}

	}

	@Test
	public void testInvalidAlienNumerals() throws IOException, URISyntaxException {
		writer.println("Only single word allowed on LHS is I");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("Only single word allowed on LHS is I: Invalid translation. Please check input file.",
					e.getMessage());
		}
	}

	@Test
	public void testInvalidDecimalNumberInCreditTranslations() throws IOException, URISyntaxException {
		writer.println("glob glob Silver is Only single token and decimals allowed on RHS Credits");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("glob glob Silver is Only single token and decimals allowed on RHS Credits: Invalid translation. Please check input file.",
					e.getMessage());
		}
	}

	@Test
	public void testSyntacticallyIncorrectAlienNumberInCreditTranslations() throws IOException, URISyntaxException {
		writer.println("glob is I");
		writer.println("tegj is L");
		writer.println("glob tegj Silver is 34 Credits");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("glob tegj Silver is 34 Credits Warning: glob tegj is syntactically incorrect. Please provide syntactically correct input.",
					e.getMessage());
		}

	}

	@Test
	public void testIncorrectMultiplierInCreditTranslations() throws IOException, URISyntaxException {
		writer.println("glob is I");
		writer.println("glob glob Silver NoMoreWordsAllowed is 34 Credits");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("glob glob Silver NoMoreWordsAllowed is 34 Credits: Invalid translation. Please check input file.", 
					e.getMessage());
		}

	}

	@Test
	public void testInvalidAlienNumberInNonCreditQuestions() throws IOException, URISyntaxException {
		writer.println("how much is pish tegj glob glob ?");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("how much is pish tegj glob glob ? I have no idea what you are talking about.", 
					e.getMessage());
		}
	}

	@Test
	public void testSyntacticallyIncorrectAlienNumberInNonCreditQuestions() throws IOException, URISyntaxException {
		writer.println("glob is I");
		writer.println("tegj is L");
		writer.println("how much is glob tegj ?");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("how much is glob tegj ? Warning: glob tegj is syntactically incorrect. Please provide syntactically correct input.", 
					e.getMessage());
		}

	}

	@Test
	public void testInvalidAlienNumberInCreditQuestions() throws IOException, URISyntaxException {
		writer.println("how many Credits is glob prok Silver ?");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("how many Credits is glob prok Silver ? I have no idea what you are talking about.", 
					e.getMessage());
		}
	}

	@Test
	public void testSyntacticallyIncorrectAlienNumberInCreditQuestions() throws IOException, URISyntaxException {
		writer.println("glob is I");
		writer.println("tegj is L");
		writer.println("glob glob Silver is 34 Credits");
		writer.println("how many Credits is glob tegj Silver ?");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("how many Credits is glob tegj Silver ? Warning: glob tegj is syntactically incorrect. Please provide syntactically correct input.",
					e.getMessage());
		}

	}

	@Test
	public void testIncorrectMultiplierInCreditQuestions() throws IOException, URISyntaxException {
		writer.println("glob is I");
		writer.println("glob glob Silver is 34 Credits");
		writer.println("how many Credits is glob glob Silver NoMoreWordsAllowed ?");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		try {
			translator.translate();
		} catch (Exception e) {
			Assert.assertEquals("how many Credits is glob glob Silver NoMoreWordsAllowed ? I have no idea what you are talking about.", 
					e.getMessage());
		}

	}

	@Test
	public void testUserProvidedInputFile() throws IOException, URISyntaxException {
		writer.println("glob is I");
		writer.println("prok is V");
		writer.println("pish is X");
		writer.println("tegj is L");
		writer.println("glob glob Silver is 34 Credits");
		writer.println("glob prok Gold is 57800 Credits");
		writer.println("pish pish Iron is 3910 Credits");
		writer.println("how much is pish tegj glob glob ?");
		writer.println("how many Credits is glob prok Silver ?");
		writer.println("how many Credits is glob prok Gold ?");
		writer.println("how many Credits is glob prok Iron ?");
		writer.println("how much wood could a woodchuck chuck if a woodchuck could chuck= wood ?");
		writer.close();

		translator = new Translator(file.getAbsolutePath());
		translator.translate();
		
		Assert.assertEquals("how much is pish tegj glob glob ? 42\r\n" 
						  + "how many Credits is glob prok Silver ? 68 Credits\r\n"
						  + "how many Credits is glob prok Gold ? 57,800 Credits\r\n" 
						  + "how many Credits is glob prok Iron ? 782 Credits\r\n"
						  + "how much wood could a woodchuck chuck if a woodchuck could chuck= wood ? I have no idea what you are talking about.\r\n", output.toString());
	}

}
