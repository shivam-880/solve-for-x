package com.codingkapoor.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.codingkapoor.model.Coordinate;
import com.codingkapoor.model.UserInput;

public class UserInputFileReader {

	public UserInput getUserInput(String filePath) throws Exception {
		return readUserInputFile(filePath);
	}

	private UserInput readUserInputFile(String filePath) throws Exception {

		UserInput userInput = new UserInput();
		
		ArrayList<Coordinate> coordinates = new ArrayList<>();
		
		// Variable "count" starts incrementing only after reaching first blank line in the input file.
		// The blank line marks the beginning of user input related to initial position of the frog in the next two lines. 
		int count = 0;
		
		int iMax = 0, jMax = 0;
		
		String line = null;
		
		
		InputStream is = (filePath != null) ? new FileInputStream(filePath) : getClass().getResourceAsStream("/input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		// Once a blank line is reached we only wish to read next two lines.
		while ((line = br.readLine()) != null && count <= 2) {

			if (line.length() != 0) {

				if (isValidLine(line)) {

					StringTokenizer tokenizer = new StringTokenizer(line, " ");
					Integer[] res = new Integer[tokenizer.countTokens()];

					int i = 0;
					while (tokenizer.hasMoreTokens()) {
						res[i] = Integer.parseInt(tokenizer.nextToken());
						i++;
					}

					Coordinate coordinate = new Coordinate(res[0], res[1]);

					if (count == 0) {
						coordinates.add(coordinate);
						
						if (coordinate.getX() > iMax)
							iMax = coordinate.getX();
						
						if (coordinate.getY() > jMax)
							jMax = coordinate.getY();
					}

					else if (count == 1) {
						count++;
						if (isValidPreviousCoordinate(coordinates, coordinate))
							userInput.setPrevious(coordinate);
						else
							throw new Exception("Incorrect initial previous position.");
					}

					else if (count == 2) {
						if (isValidCurrentCoordinate(coordinates, coordinate))
							userInput.setCurrent(coordinate);
						else
							throw new Exception("Incorrect initial current position.");						
					}
				} else throw new Exception("Incorrect input.");
			} else {
				count++;
			}

		}

		userInput.setAllCoordinates(coordinates);
		userInput.setiMax(iMax);
		userInput.setjMax(jMax);

		br.close();

		return userInput;
	}

	// A line the file is valid if it is composed of two numbers separated by a space.
	private boolean isValidLine(String line) {
		return line.matches("^(0|[1-9][0-9]*)(\\s+)(0|[1-9][0-9]*)$");
	}

	// Previous coordinate must not be amongst the list of coordinates where the frog could jump. 
	// The purpose of previous coordinate is only to specify the direction in which the frog is initially facing.
	private boolean isValidPreviousCoordinate(ArrayList<Coordinate> coordinates, Coordinate coordinate) {
		if (!coordinates.contains(coordinate))
			return true;
		return false;
	}

	// The frog must be positioned initially on one of the valid coordinate.
	private boolean isValidCurrentCoordinate(ArrayList<Coordinate> coordinates, Coordinate coordinate) {
		if (coordinates.contains(coordinate))
			return true;
		return false;
	}

}
