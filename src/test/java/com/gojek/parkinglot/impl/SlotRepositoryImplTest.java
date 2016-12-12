package com.gojek.parkinglot.impl;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gojek.parkinglot.Car;
import com.gojek.parkinglot.SlotRepository;
import com.gojek.parkinglot.exceptions.NoCarFoundException;
import com.gojek.parkinglot.exceptions.ParkingLotOverFlowException;

public class SlotRepositoryImplTest {
	private SlotRepository instance;
	private int capacity = 3;

	@Before
	public void setup() {
		instance = SlotRepositoryImpl.getInstance(capacity);

	}

	@After
	public void tearDown() {
		for (int i = 0; i < capacity; i++) {
			try {
				instance.deAllotSlot(i + 1);
			} catch (NoCarFoundException e) {

			}

		}

	}

	@Test
	public void testAllotSlotShouldAllotFirstCar() {
		Car car = createCar("White", "KA­01­HH­3141");
		try {
			instance.allotSlot(car);
			assertEquals("testAllotSlotShouldAllotFirstCar", 1, car.getAllotedSlot());
		} catch (ParkingLotOverFlowException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testShouldNotFailAllotSlotsEqualCapacity() {
		Car car1 = createCar("White", "KA­01­HH­3141");
		Car car2 = createCar("White", "KA­01­HH­3142");
		Car car3 = createCar("White", "KA­01­HH­3143");
		try {
			instance.allotSlot(car1);
			instance.allotSlot(car2);
			instance.allotSlot(car3);
			assertEquals("testShouldNotFailAllotSlotsEqualCapacity", 3, car3.getAllotedSlot());
		} catch (ParkingLotOverFlowException e) {
			fail("testFailAllotSlotsMoreThanCapacity");
		}
	}

	@Test
	public void testFailAllotSlotsMoreThanCapacity() {
		Car car1 = createCar("White", "KA­01­HH­3141");
		Car car2 = createCar("White", "KA­01­HH­3142");
		Car car3 = createCar("White", "KA­01­HH­3143");
		Car car4 = createCar("White", "KA­01­HH­3144");
		try {
			instance.allotSlot(car1);
			instance.allotSlot(car2);
			instance.allotSlot(car3);
			instance.allotSlot(car4);
			fail("testFailAllotSlotsMoreThanCapacity");
		} catch (ParkingLotOverFlowException e) {
			assertEquals("testAllotSlotShouldAllotFirstCar", "Sorry, parking lot is full.", e.getMessage());
		}
	}

	@Test
	public void testShouldThrowExceptionWhenDeallotWhenSlotsAreEmpty() {
		try {
			instance.deAllotSlot(1);
			fail("testShouldThrowExceptionWhenDeallotWhenSlotsAreEmpty");
		} catch (NoCarFoundException e) {
			assertEquals("testShouldThrowExceptionWhenDeallotWhenSlotsAreEmpty", "No car parked at the given slot.",
					e.getMessage());
		}
	}

	@Test
	public void testShouldAllotAndDeallotTheSlot() {
		try {
			Car car1 = createCar("White", "KA­01­HH­3141");
			instance.allotSlot(car1);
			instance.deAllotSlot(car1.getAllotedSlot());
			assertEquals("testShouldAllotAndDeallotTheSlot", 0, car1.getAllotedSlot());
		} catch (NoCarFoundException e) {
			fail("testShouldAllotAndDeallotTheSlot");
		} catch (ParkingLotOverFlowException e) {
			fail("testShouldAllotAndDeallotTheSlot");
		}
	}

	@Test
	public void testShouldThrowExceptionWhenDeAllotSlotIsLessThanOne() {
		try {
			instance.deAllotSlot(0);
			fail("testShouldThrowExceptionWhenDeAllotSlotIsLessThanOne");
		} catch (NoCarFoundException e) {
			assertEquals("testShouldThrowExceptionWhenDeAllotSlotIsLessThanOne", "Given slot is invalid.",
					e.getMessage());
		}
	}

	@Test
	public void testShouldThrowExceptionWhenDeAllotSlotIsMoreThanCapacity() {
		try {
			instance.deAllotSlot(capacity + 1);
			fail("testShouldThrowExceptionWhenDeAllotSlotIsMoreThanCapacity");
		} catch (NoCarFoundException e) {
			assertEquals("testShouldThrowExceptionWhenDeAllotSlotIsMoreThanCapacity", "Given slot is invalid.",
					e.getMessage());
		}
	}

	@Test
	public void testShouldReturnAllParkedCarsAtSnapShpt() {
		try {
			Car car1 = createCar("White", "KA­01­HH­3141");
			Car car2 = createCar("White", "KA­01­HH­3142");
			Car car3 = createCar("White", "KA­01­HH­3143");
			instance.allotSlot(car1);
			instance.allotSlot(car2);
			instance.allotSlot(car3);
			instance.deAllotSlot(car1.getAllotedSlot());
			Collection<Car> parkedCars = instance.getParkedCars();
			assertEquals("testShouldReturnAllParkedCarsAtSnapShpt", 2, parkedCars.size());
		} catch (NoCarFoundException e) {
			fail("testShouldReturnAllParkedCarsAtSnapShpt");
		} catch (ParkingLotOverFlowException e) {
			fail("testShouldReturnAllParkedCarsAtSnapShpt");
		}
	}

	private Car createCar(String color, String regNum) {
		Car car = new CarImpl();
		car.setColor(color);
		car.setRegNum(regNum);
		return car;
	}

}
