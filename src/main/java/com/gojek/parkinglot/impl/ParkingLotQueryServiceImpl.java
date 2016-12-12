package com.gojek.parkinglot.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.gojek.parkinglot.Car;
import com.gojek.parkinglot.ParkingLotQueryService;
import com.gojek.parkinglot.SlotRepository;
import com.gojek.parkinglot.exceptions.NoCarFoundException;

public class ParkingLotQueryServiceImpl implements ParkingLotQueryService {

	private SlotRepository slotRepository;

	public ParkingLotQueryServiceImpl(SlotRepository slotRepository) {
		this.slotRepository = slotRepository;
	}

	@Override
	public List<String> getRegistrationNums(String color) {
		Collection<Car> parkedCars = slotRepository.getParkedCars();
		List<String> regNums = new ArrayList<String>(6);
		Iterator<Car> iterator = parkedCars.iterator();
		while (iterator.hasNext()) {
			Car car = iterator.next();
			if (color.equalsIgnoreCase(car.getColor())) {
				regNums.add(car.getRegNum());
			}
		}
		return regNums;
	}

	@Override
	public int getSlotNumForReg(String regNum) throws NoCarFoundException {
		Collection<Car> parkedCars = slotRepository.getParkedCars();
		Iterator<Car> iterator = parkedCars.iterator();
		while (iterator.hasNext()) {
			Car car = iterator.next();
			if (regNum.equalsIgnoreCase(car.getRegNum())) {
				return car.getAllotedSlot();
			}
		}
		throw new NoCarFoundException("Not Found");
	}

	@Override
	public void printStatus() {
		Collection<Car> parkedCars = slotRepository.getParkedCars();
		System.out.println("Slot No\t" + "Registration No\t" + "Colour	");
		Iterator<Car> iterator = parkedCars.iterator();
		while (iterator.hasNext()) {
			Car car = (Car) iterator.next();
			System.out.print(car.getAllotedSlot() + "\t" + car.getRegNum() + "\t" + car.getColor());
		}

	}

	@Override
	public List<Integer> getSlotNumForColor(String color) {
		Collection<Car> parkedCars = slotRepository.getParkedCars();
		List<Integer> arrayList = new ArrayList<Integer>();
		Iterator<Car> iterator = parkedCars.iterator();
		while (iterator.hasNext()) {
			Car car = iterator.next();
			if (color.equalsIgnoreCase(car.getColor())) {
				arrayList.add(car.getAllotedSlot());
			}
		}
		return arrayList;
	}

	@Override
	public Collection<Car> getParkedCars() {
		return slotRepository.getParkedCars();
	}
}
