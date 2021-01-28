package controller;
import java.util.ArrayList;
import java.util.List;

import models.Machine;
import models.Order;

public class AddressSelectionBehavior implements MachineSelectionStrategy {	
	@Override
	public Machine selectMachine(Order order) {
		List<Machine> machines = DatabaseConnection.getMachinesForOrderByAddress(order);
		if(machines.size() > 0) {
			return machines.get(0);
		}
		return null;
	}

}
