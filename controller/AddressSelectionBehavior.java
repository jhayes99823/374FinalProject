package controller;
import java.util.ArrayList;
import java.util.List;

import models.Drink;
import models.Machine;

public class AddressSelectionBehavior implements MachineSelectionStrategy {	
	@Override
	public List<Machine> selectMachines(Drink drink, List<Machine> machines) {
		ArrayList<Machine> filteredList = new ArrayList<>();
		
		for (Machine machine : machines) {
			if (drink.getAddress().equals(machine.getAddress())) {
				filteredList.add(machine);
			}
		}
		
		return filteredList;
	}
}
