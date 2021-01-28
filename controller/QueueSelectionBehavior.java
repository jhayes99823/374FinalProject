package controller;

import java.util.List;

import models.Machine;
import models.Order;

public class QueueSelectionBehavior implements MachineSelectionBehavior{

	@Override
	public Machine selectMachine(Order order) {
		List<Machine> machinesByQueue = ControllerManager.getMachinesByQueueSizes();
		return machinesByQueue.get(0);
	}

}
