package com.gojek.parkinglot;

import java.util.List;

import com.gojek.parkinglot.exceptions.NoCarFoundException;

public interface ParkingLotQueryService {

	public List<String> getRegistrationNums(String color);

	public int getSlotNum(String regNum) throws NoCarFoundException;
	
	public void printStatus();
}
