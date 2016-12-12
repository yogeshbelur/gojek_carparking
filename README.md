# gojek_carparking

This project has been written considering two types of inputs

	1. File
	2. Command Line

In order to achieve the above i have used Template pattern, where com.gojek.parkinglot.streams.AbstractInputStreamHandler.readLine() is overriden by two implementations of it
	1. FileInputStreamHandler
	2. CommandLineInputStreamHandler

I have considered park and leave as the commands which alters the slotRepository(i.e.,) parking lot, hence used command pattern along with strategy combined in order to implement the below commands
	1. ParkCarCommand
	2. LeaveSlotCommand

And rest of the commands are basically query and hence i have provided a interface ParkingLotQueryService
Note: I could have used the command pattern as above in order to achieve it but intentionally separated the concern of command and queries.

The above will be processed by ParkingLotQueryProcessor
