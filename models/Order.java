package models;

import java.util.List;

public class Order {
	private int orderID;
	private Address address;
	private Drink drink;
	private List<Condiment> condiments;

	public Order(int orderID, Address address, Drink drink, List<Condiment> condiments) {
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

	public Drink getDrink() {
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

	public Capability getCapabilityRequirement() {
		if (drink.getCapabilityRequirement() == Capability.Programmable) {
			return Capability.Programmable;
		} else if (!condiments.isEmpty()) {
			return Capability.Automated;
		} else {
			return Capability.Simple;
		}
	}
}
