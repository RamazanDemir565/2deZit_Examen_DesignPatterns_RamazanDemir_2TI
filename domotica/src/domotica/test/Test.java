package domotica.test;

import domotica.command.*;

import java.util.*;

import domotica.model.*;
import domotica.view.*;

public class Test {
	
	private static CommandPattern lastCommand;

	private static Random random = new Random();

	private static int randomInt(int lo, int hi) {
		return lo + random.nextInt(hi - lo + 1);
	}

	private static float randomFloat(float lo, float hi) {
		return lo + random.nextFloat() * (hi - lo);
	}

	private static void round(House house) throws DomoticaException {
		{
			Living living = house.getLiving();
			living.setAircoTemperature(randomInt(15, 25));
			living.setLightLevel(randomFloat(0, 1));
			living.setRollerShutterDown(!living.isRollerShutterDown());
			living.setAllDoorsClosed(!living.isDoorClosed());
			living.setTvSwitchedOn(!living.isTvSwitchedOn());
			living.setTvChannel(randomInt(0, 99));
			living.setTvVolume(randomInt(0, 10));
		}
		{
			Kitchen kitchen = house.getKitchen();
			kitchen.setAircoTemperature(randomInt(15, 25));
			kitchen.setLightLevel(randomFloat(0, 1));
			kitchen.setRollerShutterDown(!kitchen.isRollerShutterDown());
			kitchen.setAllDoorsClosed(!kitchen.isDoorClosed());
			kitchen.setCooktopSwitchedOn(!kitchen.isCooktopSwitchedOn());
			kitchen.setExtractorHoodSwitchedOn(!kitchen.isExtractorHoodSwitchedOn());
			kitchen.setRefrigeratorTemperature(randomInt(1, 15));
			kitchen.setFreezerTemperature(randomInt(-15, -1));
		}
		{
			Bathroom bathroom = house.getBathroom();
			bathroom.setAircoTemperature(randomInt(15, 25));
			bathroom.setLightLevel(randomFloat(0, 1));
			bathroom.setRollerShutterDown(!bathroom.isRollerShutterDown());
			bathroom.setAllDoorsClosed(!bathroom.isDoorClosed());
			bathroom.setShowerTemperature(randomInt(10, 60));
			bathroom.setShowerFlowLevel(randomFloat(0, 1));
			bathroom.setTapTemperature(randomInt(10, 60));
			bathroom.setTapFlowLevel(randomFloat(0, 1));
		}
		{
			Bedroom bedroom = house.getBedroom();
			bedroom.setAircoTemperature(randomInt(15, 25));
			bedroom.setLightLevel(randomFloat(0, 1));
			bedroom.setRollerShutterDown(!bedroom.isRollerShutterDown());
			bedroom.setAllDoorsClosed(!bedroom.isDoorClosed());
			bedroom.setAlarmSwitchedOn(!bedroom.isAlarmSwitchedOn());
			bedroom.setWaterbedTemperature(randomInt(10, 30));
		}
		
		
	}
	public static void openDoors(DomoticaObject object){
		DoorsOpenCommand openCommand = new DoorsOpenCommand(object);
		openCommand.execute();
		lastCommand = openCommand;
		object.update();
	}
	
	public static void closeDoors(DomoticaObject object){
		DoorsCloseCommand closeCommand = new DoorsCloseCommand(object);
		closeCommand.execute();
		lastCommand = closeCommand;
		object.update();
	}
	
	public static void main(String[] args) throws DomoticaException {
		House house = new House("house");
		new HouseView(house);
		for (int i = 0; i < 100; i++) {
			round(house);
			
			
			openDoors(house);
			closeDoors(house);
		}
		
	}

}
