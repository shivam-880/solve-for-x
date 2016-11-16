package com.codingkapoor.merchantsguidetogalaxy.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	private Properties prop = new Properties();

	public PropertyReader(String fileName) throws IOException {

		try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {

			if (is == null)
				throw new FileNotFoundException(fileName);

			prop.load(is);
		} 
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}
