package com.gojek.parkinglot.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gojek.parkinglot.AbstractTestCase;
import com.gojek.parkinglot.Car;
import com.gojek.parkinglot.ParkingLotQueryService;
import com.gojek.parkinglot.exceptions.NoCarFoundException;
import com.gojek.parkinglot.exceptions.ParkingLotOverFlowException;

public class ParkingLotQueryServiceTest extends AbstractTestCase {

	private ParkingLotQueryService parkingLotQueryService;

	@Before
	public void setUp() {
		super.setUp();
		parkingLotQueryService = new ParkingLotQueryServiceImpl(slotRepository);
	}

	@Test
	public void testShouldReturnRegNumOfCarInParticularRegNum() {
		Car car1 = createCar("White", "KA­01­HH­3141");
		Car car2 = createCar("White", "KA­01­HH­3142");
		Car car3 = createCar("White", "KA­01­HH­3143");
		try {
			slotRepository.allotSlot(car1);
			slotRepository.allotSlot(car2);
			slotRepository.allotSlot(car3);
		} catch (ParkingLotOverFlowException e1) {
			fail("testShouldReturnRegNumOfCarInParticularRegNum");
		}
		try {
			int slotNumForReg = parkingLotQueryService.getSlotNumForReg(car2.getRegNum());
			assertEquals("testShouldReturnRegNumOfCarInParticularRegNum", car2.getAllotedSlot(), slotNumForReg);
		} catch (NoCarFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testShouldThrowNotFoundWhenNocarWithGivenRegNumNotFound() {
		Car car1 = createCar("White", "KA­01­HH­3141");
		try {
			slotRepository.allotSlot(car1);
		} catch (ParkingLotOverFlowException e1) {
			fail("testShouldReturnRegNumOfCarInParticularRegNum");
		}
		try {
			parkingLotQueryService.getSlotNumForReg("KA­01­HH­3142");
			fail("testShouldThrowNotFoundWhenNocarWithGivenRegNumNotFound");
		} catch (NoCarFoundException e) {
			assertEquals("testShouldThrowNotFoundWhenNocarWithGivenRegNumNotFound", "Not Found", e.getMessage());
		}
	}

	@Test
	public void testShouldReturnAllTheCarsSlotsWithParticularColor() {
		Car car1 = createCar("White", "KA­01­HH­3141");
		Car car2 = createCar("RED", "KA­01­HH­3141");
		Car car3 = createCar("White", "KA­01­HH­3141");
		try {
			slotRepository.allotSlot(car1);
			slotRepository.allotSlot(car2);
			slotRepository.allotSlot(car3);
		} catch (ParkingLotOverFlowException e1) {
			fail("testShouldReturnAllTheCarsSlotsWithParticularColor");
		}
		List<Integer> slotNumForColor = parkingLotQueryService.getSlotNumForColor("White");
		assertEquals("testShouldReturnAllTheCarsSlotsWithParticularColor", 2, slotNumForColor.size());
	}

	@Test
	public void testShouldReturnAllTheCarsRegNumWithParticularColor() {
		Car car1 = createCar("White", "KA­01­HH­3141");
		Car car2 = createCar("RED", "KA­01­HH­3141");
		Car car3 = createCar("White", "KA­01­HH­3141");
		try {
			slotRepository.allotSlot(car1);
			slotRepository.allotSlot(car2);
			slotRepository.allotSlot(car3);
		} catch (ParkingLotOverFlowException e1) {
			fail("testShouldReturnAllTheCarsRegNumWithParticularColor");
		}
		List<String> slotNumForColor = parkingLotQueryService.getRegistrationNums("White");
		assertEquals("testShouldReturnAllTheCarsRegNumWithParticularColor", 2, slotNumForColor.size());
	}

	private Car createCar(String color, String regNum) {
		Car car = new CarImpl();
		car.setColor(color);
		car.setRegNum(regNum);
		return car;
	}

}
