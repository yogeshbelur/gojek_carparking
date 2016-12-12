package com.gojek.parkinglot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.gojek.parkinglot.exceptions.NoCarFoundException;
import com.gojek.parkinglot.exceptions.ParkingLotOverFlowException;
import com.gojek.parkinglot.streams.CommandLineInputStreamHandler;
import com.gojek.parkinglot.streams.FileInputStreamHandler;

public class CarParkingApplication {

	public static void main(String[] args) throws ParkingLotOverFlowException, NoCarFoundException {
		InputStreamHandler inputStreamHandler = null;

		if (args.length == 0) {
			inputStreamHandler = new CommandLineInputStreamHandler(System.in);

		} else {
			try {
				FileInputStream inputStream = new FileInputStream(args[0]);
				inputStreamHandler = new FileInputStreamHandler(inputStream);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
		inputStreamHandler.handle();
	}
}
