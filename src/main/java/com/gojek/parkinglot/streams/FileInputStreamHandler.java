package com.gojek.parkinglot.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileInputStreamHandler extends AbstractInputStreamHandler {

	private BufferedReader reader;

	public FileInputStreamHandler(InputStream inputStream) {
		reader = new BufferedReader(new InputStreamReader(inputStream));
	}

	@Override
	public String readLine() throws IOException {
		return reader.readLine();
	}

}
