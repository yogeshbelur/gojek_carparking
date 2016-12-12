package com.gojek.parkinglot;

import java.util.Collection;

import com.gojek.parkinglot.exceptions.NoCarFoundException;
import com.gojek.parkinglot.exceptions.ParkingLotOverFlowException;

public interface SlotRepository {

	public void allotSlot(Car car) throws ParkingLotOverFlowException;

	public Car deAllotSlot(int slot) throws NoCarFoundException;

	public Collection<Car> getParkedCars();
}
