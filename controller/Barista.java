package controller;

import models.Machine;
import models.Order;

public class Barista implements Observer {

	private Machine workingAt;
	private Order attendingTo = null;

	public Barista(Machine workingAt) {
		this.workingAt = workingAt;
	}

	@Override
	public void notify(Order order, Machine machine) {
		if (this.workingAt.equals(machine)) {
			this.attendingTo = order;
		}
	}

	public boolean isAttendingTo() {
		return attendingTo != null;
	}

}
