package models;

import java.util.List;

public class Machine {
	private int id;
	private CoffeeController controller;
	private List<Capability> capabilities;
	
	public Machine(int id, CoffeeController controller, List<Capability> capabilities) {
		this.id = id;
		this.controller = controller;
		this.capabilities = capabilities;
	}
	
	public int getID() {
		return this.id;
	}
	
	public CoffeeController getController() {
		return this.controller;
	}
	
	public List<Capability> getCapabilities(){
		return this.capabilities;
	}
}