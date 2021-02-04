package controller;

import java.util.ArrayList;
import java.util.List;

import models.Capability;
import models.Machine;
import models.Order;

public class MachineTypeSelectionBehavior implements MachineSelectionStrategy {

	@Override
	public List<Machine> selectMachines(Order order, List<Machine> machines) {
		List<Machine> selectedMachines = new ArrayList<Machine>();
		switch(order.getCapabilityRequirement()) {
			case Programmable:
				for(Machine machine : machines) {
					if(machine.hasCapability(Capability.Programmable)) {
						selectedMachines.add(machine);
					}
				}
				break;
			case Automated:
				for(Machine machine : machines) {
					if(machine.hasCapability(Capability.Automated)) {
						selectedMachines.add(machine);
					}
				}
				break;
			case Simple:
				for(Machine machine : machines) {
					if(machine.hasCapability(Capability.Simple)) {
						selectedMachines.add(machine);
					}
				}
				break;
		}
		return selectedMachines;
	}

}
