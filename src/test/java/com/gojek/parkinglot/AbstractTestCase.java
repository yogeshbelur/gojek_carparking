package com.gojek.parkinglot;

import org.junit.After;
import org.junit.Before;

import com.gojek.parkinglot.exceptions.NoCarFoundException;
import com.gojek.parkinglot.impl.SlotRepositoryImpl;

public abstract class AbstractTestCase {

	protected int capacity = 3;

	protected SlotRepository slotRepository;

	@Before
	public void setUp() {
		slotRepository = SlotRepositoryImpl.getInstance(capacity);
	}

	@After
	public void tearDown() {
		for (int i = 0; i < capacity; i++) {
			try {
				slotRepository.deAllotSlot(i + 1);
			} catch (NoCarFoundException e) {

			}
		}
	}
}
