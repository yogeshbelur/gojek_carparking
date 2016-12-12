package com.gojek.parkinglot.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.gojek.parkinglot.Car;
import com.gojek.parkinglot.ParkingLotQueryProcessor;
import com.gojek.parkinglot.ParkingLotQueryService;
import com.gojek.parkinglot.exceptions.NoCarFoundException;

public class ParkingLotQueryProcessorImpl implements ParkingLotQueryProcessor {

	private ParkingLotQueryService parkingLotQueryService;

	public ParkingLotQueryProcessorImpl(ParkingLotQueryService parkingLotQueryService) {
		this.parkingLotQueryService = parkingLotQueryService;
	}

	@Override
	public void process(String[] queryParam) {
		switch (queryParam[0]) {
		case QueryConstants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
			List<String> registrationNums = parkingLotQueryService.getRegistrationNums(queryParam[1]);
			System.out.println(registrationNums);
			break;

		case QueryConstants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
			List<Integer> slotNum = parkingLotQueryService.getSlotNumForColor(queryParam[1]);
			System.out.println(slotNum);
			break;

		case QueryConstants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
			try {
				int slotNumForReg = parkingLotQueryService.getSlotNumForReg(queryParam[1]);
				System.out.println(slotNumForReg);
			} catch (NoCarFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case QueryConstants.STATUS:
			Collection<Car> parkedCars = parkingLotQueryService.getParkedCars();
			System.out.println("Slot No\t" + "Registration No\t" + "Colour	");
			Iterator<Car> iterator = parkedCars.iterator();
			while (iterator.hasNext()) {
				Car car = (Car) iterator.next();
				System.out.println(car.getAllotedSlot() + "\t" + car.getRegNum() + "\t" + car.getColor());
			}
			break;
		default:
			System.out.println("No such query command exists");
			break;
		}
	}

}
