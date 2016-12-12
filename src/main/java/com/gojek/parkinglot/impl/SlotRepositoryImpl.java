package com.gojek.parkinglot.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.gojek.parkinglot.Car;
import com.gojek.parkinglot.SlotRepository;
import com.gojek.parkinglot.exceptions.NoCarFoundException;
import com.gojek.parkinglot.exceptions.ParkingLotOverFlowException;

public class SlotRepositoryImpl implements SlotRepository {

	private static Map<Integer, Car> slotVsCar;

	private static int[] slots;

	private static int capacity;

	private static SlotRepositoryImpl INSTANCE;

	private SlotRepositoryImpl() {

	}

	public static SlotRepository getInstance(int cap) {
		if (null == INSTANCE) {
			synchronized (SlotRepositoryImpl.class) {
				if (null == INSTANCE) {
					INSTANCE = new SlotRepositoryImpl();
					capacity = cap;
					slotVsCar = new HashMap<Integer, Car>(capacity);
					slots = new int[capacity];
				}
			}
		}

		return INSTANCE;
	}


	@Override
	public void allotSlot(Car car) throws ParkingLotOverFlowException {
		int availableSlot = 0;
		// check the nearest slot available
		while (availableSlot < capacity) {
			// 1 represents occupied and 0 represents empty
			if (slots[availableSlot] == 0) {
				break;
			}
			availableSlot++;
		}
		if (availableSlot >= capacity) {
			throw new ParkingLotOverFlowException("Sorry, parking lot is full.");
		}
		// occupy the availableSlot
		slots[availableSlot] = 1;
		availableSlot++;
		car.setAllotedSlot(availableSlot);
		slotVsCar.put(availableSlot, car);
	}

	@Override
	public Car deAllotSlot(int slot) throws NoCarFoundException {
		// validate whether the slot asked is between 1 and capacity
		if (slot < 1 || slot > capacity) {
			throw new NoCarFoundException("Given slot is invalid.");
		}
		Car car = slotVsCar.remove(slot);
		if (null == car) {
			throw new NoCarFoundException("No car parked at the given slot.");
		}
		// empty the slot.
		slots[slot - 1] = 0;
		car.setAllotedSlot(0);
		return car;
	}

	@Override
	public Collection<Car> getParkedCars() {
		return slotVsCar.values();
	}

}
