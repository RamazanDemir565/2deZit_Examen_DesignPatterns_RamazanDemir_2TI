package domotica.command;

import domotica.model.*;

public class DoorsCloseCommand implements CommandPattern {

	DomoticaObject object;
	
	public DoorsCloseCommand(DomoticaObject object){
		this.object = object;
	}
	
	@Override
	public void execute() {
		try {
			object.setAllDoorsClosed();
		} 
		catch (ElectricityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		try {
			object.setAllDoorsOpen();
		} 
		catch (ElectricityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
