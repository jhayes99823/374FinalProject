package controller;
import java.util.ArrayList;
import java.util.List;

import models.Machine;
import models.Order;

public class AddressSelectionBehavior implements MachineSelectionStrategy {	
	@Override
	public List<Machine> selectMachines(Order order, List<Machine> machines) {
		ArrayList<Machine> filteredList = new ArrayList<>();
		
		for (Machine machine : machines) {
			if (order.getAddress().equals(machine.getAddress())) {
				filteredList.add(machine);
			}
		}
		
		return filteredList;
	}

}
