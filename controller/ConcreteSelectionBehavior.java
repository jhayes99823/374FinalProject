package controller;
import java.util.List;

import models.Machine;
import models.Order;

public class ConcreteSelectionBehavior implements MachineSelectionBehavior {	
	@Override
	public Machine selectMachine(Order order) {
		List<Machine> machines = DatabaseConnection.getMachinesForOrder(order);
		if(machines.size() > 0) {
			return machines.get(0);
		}
		return null;
	}

}
