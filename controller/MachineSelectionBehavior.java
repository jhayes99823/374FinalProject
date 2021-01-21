package controller;
import models.Machine;
import models.Order;

public interface MachineSelectionBehavior {
	public Machine selectMachine(Order order);
}
