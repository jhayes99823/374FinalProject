package models;

import org.json.simple.JSONObject;

public class Condiment {
	private String name;
	private int quantity;
	
	public Condiment(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public JSONObject toJSON() {
		JSONObject condimentJSON = new JSONObject();
		condimentJSON.put("name", name);
		condimentJSON.put("qty", quantity);
		return condimentJSON;
	}
}
