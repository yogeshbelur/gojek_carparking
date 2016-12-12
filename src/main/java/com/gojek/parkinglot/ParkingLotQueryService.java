package com.gojek.parkinglot;

import java.util.Collection;
import java.util.List;

import com.gojek.parkinglot.exceptions.NoCarFoundException;

/**
 * @author Yogesh
 *
 *         <p>
 *         This interface can be used to query various data about the parking
 *         lot application.
 */
public interface ParkingLotQueryService {

	/**
	 * Returns the register numbers of all the car matching to given color
	 * 
	 * @param color
	 *            - color of the cars to be filtered
	 * @return
	 */
	public List<String> getRegistrationNums(String color);

	/**
	 * returns the slot numbers of the given car registration number
	 * 
	 * @param regNum
	 *            - regNum of parked car
	 * @return slotnum
	 * @throws NoCarFoundException
	 *             - when no car found with the given regnum
	 */
	public int getSlotNumForReg(String regNum) throws NoCarFoundException;

	/**
	 * returns the slot numbers of the given car color
	 * 
	 * @param color
	 *            - color of the car
	 * @return slotnum
	 */
	public List<Integer> getSlotNumForColor(String color);

	/**
	 * prints the snapshot of the parking lot
	 */
	public void printStatus();

	/**
	 * returns all parked car
	 * 
	 * @return all parked car
	 */
	public Collection<Car> getParkedCars();
}
