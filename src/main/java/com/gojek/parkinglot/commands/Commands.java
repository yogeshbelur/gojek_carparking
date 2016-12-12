package com.gojek.parkinglot.commands;

import java.util.HashMap;
import java.util.Map;

import com.gojek.parkinglot.SlotRepository;

public class Commands {

	private Map<String, Command> commandsMap;

	public Commands(SlotRepository slotRepository) {
		commandsMap = new HashMap<String, Command>(2);
		commandsMap.put("park", new ParkCarCommand(slotRepository));
		commandsMap.put("leave", new LeaveSlotCommand(slotRepository));
	}

	public Command getCommand(String command) {
		return commandsMap.get(command);
	}
}
