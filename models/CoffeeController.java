package models;


public class CoffeeController {
	private int id;
	private String type;
	private Address address;
	
	
	public CoffeeController(int id, String type, Address address) {
		this.id = id;
		this.type = type;
		this.address = address;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Address getAddress() {
		return this.address;
	}
}
