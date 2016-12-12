package com.gojek.parkinglot;

/**
 * Represents Car
 * 
 * @author Yogesh
 *
 */
public interface Car {

	/**
	 * @return regNum
	 */
	public String getRegNum();

	/**
	 * sets regNum
	 * 
	 * @param regNum
	 */
	public void setRegNum(String regNum);

	/**
	 * @return allocated slot
	 */
	public int getAllotedSlot();

	/**
	 * sets slot
	 * 
	 * @param allotedSlot
	 */
	public void setAllotedSlot(int allotedSlot);

	/**
	 * @return color of car
	 */
	public String getColor();

	/**
	 * sets color of car
	 * 
	 * @param color
	 */
	public void setColor(String color);
}
