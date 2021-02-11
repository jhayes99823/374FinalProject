package controller;

import models.Drink;
import models.Machine;

public class Barista implements Observer {

	private Machine workingAt;
	private Drink attendingTo = null;

	public Barista(Machine workingAt) {
		this.workingAt = workingAt;
	}

	@Override
	public void notify(Drink drink, Machine machine) {
		if (this.workingAt.equals(machine)) {
			this.attendingTo = drink;
		}
	}

	public boolean isAttendingTo() {
		return attendingTo != null;
	}

}
