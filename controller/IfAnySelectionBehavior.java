package controller;

import java.util.List;

import models.Drink;
import models.Machine;

public class IfAnySelectionBehavior implements MachineSelectionStrategy {

	private MachineSelectionStrategy strategy;

	public IfAnySelectionBehavior(MachineSelectionStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public List<Machine> selectMachines(Drink drink, List<Machine> machines) {
		List<Machine> strategyMachine = strategy.selectMachines(drink, machines);
		if (strategyMachine.size() == 0)
			return machines;
		return strategyMachine;
	}

}
