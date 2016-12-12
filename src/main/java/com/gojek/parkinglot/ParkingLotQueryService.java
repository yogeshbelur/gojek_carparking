package com.gojek.parkinglot;

import java.util.Collection;
import java.util.List;

import com.gojek.parkinglot.exceptions.NoCarFoundException;

public interface ParkingLotQueryService {

	public List<String> getRegistrationNums(String color);

	public int getSlotNumForReg(String regNum) throws NoCarFoundException;

	public List<Integer> getSlotNumForColor(String color);

	public void printStatus();

	public Collection<Car> getParkedCars();
}
