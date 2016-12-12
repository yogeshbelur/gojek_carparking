package com.gojek.parkinglot;

import java.util.Collection;

import com.gojek.parkinglot.exceptions.NoCarFoundException;
import com.gojek.parkinglot.exceptions.ParkingLotOverFlowException;

/**
 * @author Yogesh
 * 
 *         This interface can be used to represent the main data structure for
 *         tracking park and leave data for the cars.
 */
public interface SlotRepository {

	/**
	 * Allots a nearby available slot to the car
	 * 
	 * @param car
	 * @throws ParkingLotOverFlowException
	 *             - if all slots are full
	 */
	public void allotSlot(Car car) throws ParkingLotOverFlowException;

	/**
	 * De allots the car from the given slot
	 * 
	 * @param slot
	 * @return
	 * @throws NoCarFoundException
	 *             - if no car found in given slot
	 */
	public Car deAllotSlot(int slot) throws NoCarFoundException;

	/**
	 * @return returns all cars in the parking slot
	 */
	public Collection<Car> getParkedCars();
}
