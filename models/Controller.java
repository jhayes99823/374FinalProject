package models;


public class Controller {
	private int id;
	private ControllerType type;
	private Address address;
	
	
	public Controller(int id, ControllerType type, Address address) {
		this.id = id;
		this.type = type;
		this.address = address;
	}
	
	public int getID() {
		return this.id;
	}
	
	public ControllerType getType() {
		return this.type;
	}
	
	public Address getAddress() {
		return this.address;
	}
}
