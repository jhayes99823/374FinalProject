package models;

import java.util.List;

public class Order {
	private int orderID;
	private Address address;
	private String drink;
	private List<Condiment> condiments;

	public Order(int orderID, Address address, String drink, List<Condiment> condiments) {
		this.orderID = orderID;
		this.address = address;
		this.drink = drink;
		this.condiments = condiments;
	}

	public int getOrderID() {
		return this.orderID;
	}

	public Address getAddress() {
		return this.address;
	}

	public String getDrink() {
		return this.drink;
	}

	public List<Condiment> getCondiments() {
		return this.condiments;
	}

	public boolean hasCondiments() {
		return this.condiments.size() != 0;
	}

	public Condiment getCondiment(int index) {
		return this.condiments.get(index);
	}
}
