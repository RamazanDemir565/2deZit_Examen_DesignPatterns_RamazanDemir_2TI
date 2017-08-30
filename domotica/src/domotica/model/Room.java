package domotica.model;

@SuppressWarnings("serial")
public abstract class Room extends DomoticaObject {

	private int aircoTemperature;
	private float lightLevel;
	private boolean rollerShutterDown;
	private boolean doorClosed;

	Room(String id) {
		super(id);
		while (true) {
			try {
				reset();
				break;
			} catch (DomoticaException e) {
				//	do nothing
			}
		};
	}

	public int getAircoTemperature()		{	return aircoTemperature; }
	public float getLightLevel() 			{	return lightLevel; }
	public boolean isRollerShutterDown()	{	return rollerShutterDown; }
	public boolean isDoorClosed() 			{	return doorClosed; }
	
	public void setAircoTemperature(int aircoTemperature) throws ElectricityException {
		Checks.checkInt(aircoTemperature, 15, 25);
		Checks.checkElectricity();
		this.aircoTemperature = aircoTemperature;
	}

	public void setLightLevel(float lightLevel) throws ElectricityException {
		Checks.checkFloat(lightLevel, 0, 1);
		Checks.checkElectricity();
		this.lightLevel = lightLevel;
	}

	public void setRollerShutterDown(boolean rollerShutterDown) throws ElectricityException {
		Checks.checkElectricity();
		this.rollerShutterDown = rollerShutterDown;
	}
	
	public void setAllDoorsClosed(boolean doorClosed) throws ElectricityException {
		Checks.checkElectricity();
		this.doorClosed = true;
		System.out.println("Closing the doors");
	}
	
	public void setAllDoorsOpen(boolean doorOpened) throws ElectricityException{
		Checks.checkElectricity();
		this.doorClosed = false;
		System.out.println("Opening the doors");
	}

	public void reset() throws DomoticaException {
		setAircoTemperature(20);
		setLightLevel(0);
		setRollerShutterDown(true);
		setAllDoorsClosed();
	}
	
	public void openRoom() {
		try {
			setAircoTemperature(20);
			setLightLevel(1);
			setRollerShutterDown(false);
			setAllDoorsOpen();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeRoom() {
		try {
			setAircoTemperature(10);
			setLightLevel(0);
			setRollerShutterDown(true);
			setAllDoorsClosed();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
}
