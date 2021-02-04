package models;

import java.util.List;

public class Machine {
	private int id;
	private Controller controller;
	private List<Capability> capabilities;
	private int numberOfOrders = 0;

	public Machine(int id, Controller controller, List<Capability> capabilities) {
		this.id = id;
		this.controller = controller;
		this.capabilities = capabilities;
	}

	public int getID() {
		return this.id;
	}

	public Controller getController() {
		return this.controller;
	}

	public List<Capability> getCapabilities() {
		return this.capabilities;
	}

	public Address getAddress() {
		return this.controller.getAddress();
	}

	public boolean hasCapability(Capability capability) {
		return capabilities.contains(capability);
	}

	public void setNumberOfOrder(int number) {
		this.numberOfOrders = number;
	}

	public int getNumberOfOrder() {
		return this.numberOfOrders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Machine other = (Machine) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
