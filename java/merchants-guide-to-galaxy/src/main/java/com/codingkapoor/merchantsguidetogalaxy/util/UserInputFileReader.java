package com.codingkapoor.merchantsguidetogalaxy.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserInputFileReader {

	public List<String> read(String fileName) throws IOException {
		
		List<String> strList = new ArrayList<>();
		
		InputStream is = (fileName != null) ? new FileInputStream(fileName) : getClass().getResourceAsStream("/default-user-input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line = null;
		while ((line = br.readLine()) != null) {
			strList.add(line);
		}
		
		return strList;
	}
}
