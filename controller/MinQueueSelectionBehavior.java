package controller;

import java.util.ArrayList;
import java.util.List;

import models.Drink;
import models.Machine;

public class MinQueueSelectionBehavior implements MachineSelectionStrategy {

	@Override
	public List<Machine> selectMachines(Drink drink, List<Machine> machines) {
		List<Machine> filteredList = new ArrayList<Machine>();
		int minQueueSize = machines.get(0).getNumberOfOrder();
		for (Machine machine : machines) {
			if (machine.getNumberOfOrder() < minQueueSize) {
				minQueueSize = machine.getNumberOfOrder();
			}
		}
		for (Machine machine : machines) {
			if (machine.getNumberOfOrder() == minQueueSize) {
				filteredList.add(machine);
			}
		}
		return filteredList;
	}

}
