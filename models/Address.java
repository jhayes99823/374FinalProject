package models;


public class Address {
	private String street;
	private int ZIP;
	
	public Address(String street, int ZIP) {
		this.street = street;
		this.ZIP = ZIP;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public int getZIP() {
		return this.ZIP;
	}
}
