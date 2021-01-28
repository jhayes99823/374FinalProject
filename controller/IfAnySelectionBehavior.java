package controller;

import java.util.List;

import models.Machine;
import models.Order;

public class IfAnySelectionBehavior implements MachineSelectionStrategy {

	private MachineSelectionStrategy strategy;

	public IfAnySelectionBehavior(MachineSelectionStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public List<Machine> selectMachines(Order order, List<Machine> machines) {
		List<Machine> strategyMachine = strategy.selectMachines(order, machines);
		if (strategyMachine.size() == 0)
			return machines;
		return strategyMachine;
	}

}
