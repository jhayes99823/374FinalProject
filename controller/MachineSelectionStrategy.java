package controller;
import java.util.List;

import models.Machine;
import models.Order;

public interface MachineSelectionStrategy {
	public List<Machine> selectMachines(Order order, List<Machine> machines);
}
