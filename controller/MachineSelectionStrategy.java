package controller;
import java.util.List;

import models.Drink;
import models.Machine;

public interface MachineSelectionStrategy {
	public List<Machine> selectMachines(Drink drink, List<Machine> machines);
}
