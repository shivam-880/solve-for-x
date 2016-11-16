package com.codingkapoor.merchantsguidetogalaxy.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class UserInputFileReader {

	public List<String> read(String fileName) throws IOException, URISyntaxException {

		File file;

		if (fileName != null)
			file = new File(fileName);
		else {
			URL url = getClass().getClassLoader().getResource("default-user-input.txt");
			if (url == null)
				throw new FileNotFoundException();

			file = new File(url.toURI());
		}

		return Files.readAllLines(file.toPath(), Charset.defaultCharset());
	}
}
