package com.gojek.parkinglot.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandLineInputStreamHandler extends AbstractInputStreamHandler {

	private BufferedReader br;

	public CommandLineInputStreamHandler(InputStream inputStream) {
		br = new BufferedReader(new InputStreamReader(inputStream));
	}

	@Override
	public String readLine() throws IOException {
		return br.readLine();
	}

}
