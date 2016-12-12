package com.gojek.parkinglot.impl;

import com.gojek.parkinglot.Car;

public class CarImpl implements Car {

	private String regNum;

	private String color;

	private int allotedSlot;

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getAllotedSlot() {
		return allotedSlot;
	}

	public void setAllotedSlot(int allotedSlot) {
		this.allotedSlot = allotedSlot;
	}
}
