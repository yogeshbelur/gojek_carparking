package com.gojek.parkinglot.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Collection;

import org.junit.Test;

import com.gojek.parkinglot.AbstractTestCase;
import com.gojek.parkinglot.Car;

public class CommandsTest extends AbstractTestCase {

	private Commands commands;

	public void setUp() {
		super.setUp();
		commands = new Commands(slotRepository);
	}

	@Test
	public void testShouldExecutePark() {
		String[] parkCommand = "park KA­01­HH­1234 White".split(" ");
		Command command = commands.getCommand(parkCommand[0]);
		command.execute(parkCommand);
		Collection<Car> parkedCars = slotRepository.getParkedCars();
		assertNotEquals("testShouldExecutePark", null, parkedCars.iterator().next());
	}

	@Test
	public void testShouldExecuteParkAndLeave() {
		String[] parkCommand = "park KA­01­HH­1234 White".split(" ");
		String[] leaveCommand = "leave 1".split(" ");
		Command commandPark = commands.getCommand(parkCommand[0]);
		commandPark.execute(parkCommand);
		Collection<Car> parkedCars = slotRepository.getParkedCars();
		Car next = parkedCars.iterator().next();
		assertNotEquals("testShouldExecuteParkAndLeave", null, next);
		Command commandLeave = commands.getCommand(leaveCommand[0]);
		commandLeave.execute(leaveCommand);
		Collection<Car> leftCars = slotRepository.getParkedCars();
		assertEquals("testShouldExecuteParkAndLeave", 0, leftCars.size());
	}

}
