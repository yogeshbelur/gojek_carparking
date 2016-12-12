package com.gojek.parkinglot.commands;

import com.gojek.parkinglot.SlotRepository;

public abstract class AbstractCommand implements Command {

	protected SlotRepository slotRepository;

	public AbstractCommand(SlotRepository slotRepository) {
		this.slotRepository = slotRepository;
	}

}
