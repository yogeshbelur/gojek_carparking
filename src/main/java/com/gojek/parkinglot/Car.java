package com.gojek.parkinglot;

public interface Car {

	public String getRegNum();

	public void setRegNum(String regNum);

	public int getAllotedSlot();

	public void setAllotedSlot(int allotedSlot);

	public String getColor();

	public void setColor(String color);
}
