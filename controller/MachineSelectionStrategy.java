package controller;
import models.Machine;
import models.Order;

public interface MachineSelectionStrategy {
	public Machine selectMachine(Order order);
}
