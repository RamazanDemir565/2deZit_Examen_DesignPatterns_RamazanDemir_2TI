package domotica.model;



import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public abstract class DomoticaObject extends Observable implements Serializable {

	private String id;
	private boolean doorClosed;

	DomoticaObject(String id) {
		this.id = id;
	}

	public String getId() {	return id; }

	public String toString() {
		return getId();
	}
	
	public void setChanged() {
		super.setChanged();
	}

	public boolean isDoorClosed() {
		return doorClosed;
	}
	
	

	
	public void setAllDoorsClosed() throws ElectricityException{
		Checks.checkElectricity();
		this.doorClosed = true;
		System.out.println("Closing the doors");
	}
	
	public void setAllDoorsOpen() throws ElectricityException{
		Checks.checkElectricity();
		this.doorClosed = false;
		System.out.println("Openeing the doors");
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
	
	
}
