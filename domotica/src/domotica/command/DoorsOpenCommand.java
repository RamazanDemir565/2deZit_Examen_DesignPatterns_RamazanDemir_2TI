package domotica.command;

import domotica.model.*;

public class DoorsOpenCommand implements CommandPattern {
	DomoticaObject object;
	
	

	public DoorsOpenCommand(DomoticaObject object) {
	
		this.object = object;
	}

	@Override
	public void execute() {
		try{
			object.setAllDoorsOpen();
		}
		catch (ElectricityException e){
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		try{
			object.setAllDoorsClosed();
		
		}
		catch(ElectricityException e){
			e.printStackTrace();
		}
		
	}
	
	
}
