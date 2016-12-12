package com.gojek.parkinglot.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.gojek.parkinglot.AbstractTestCase;
import com.gojek.parkinglot.Car;
import com.gojek.parkinglot.exceptions.NoCarFoundException;
import com.gojek.parkinglot.exceptions.ParkingLotOverFlowException;

public class SlotRepositoryImplTest extends AbstractTestCase {

	@Before
	public void setup() {
		super.setUp();

	}

	@Test
	public void testAllotSlotShouldAllotFirstCar() {
		Car car = createCar("White", "KA­01­HH­3141");
		try {
			slotRepository.allotSlot(car);
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
			slotRepository.allotSlot(car1);
			slotRepository.allotSlot(car2);
			slotRepository.allotSlot(car3);
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
			slotRepository.allotSlot(car1);
			slotRepository.allotSlot(car2);
			slotRepository.allotSlot(car3);
			slotRepository.allotSlot(car4);
			fail("testFailAllotSlotsMoreThanCapacity");
		} catch (ParkingLotOverFlowException e) {
			assertEquals("testAllotSlotShouldAllotFirstCar", "Sorry, parking lot is full.", e.getMessage());
		}
	}

	@Test
	public void testShouldThrowExceptionWhenDeallotWhenSlotsAreEmpty() {
		try {
			slotRepository.deAllotSlot(1);
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
			slotRepository.allotSlot(car1);
			slotRepository.deAllotSlot(car1.getAllotedSlot());
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
			slotRepository.deAllotSlot(0);
			fail("testShouldThrowExceptionWhenDeAllotSlotIsLessThanOne");
		} catch (NoCarFoundException e) {
			assertEquals("testShouldThrowExceptionWhenDeAllotSlotIsLessThanOne", "Given slot is invalid.",
					e.getMessage());
		}
	}

	@Test
	public void testShouldThrowExceptionWhenDeAllotSlotIsMoreThanCapacity() {
		try {
			slotRepository.deAllotSlot(capacity + 1);
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
			slotRepository.allotSlot(car1);
			slotRepository.allotSlot(car2);
			slotRepository.allotSlot(car3);
			slotRepository.deAllotSlot(car1.getAllotedSlot());
			Collection<Car> parkedCars = slotRepository.getParkedCars();
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
