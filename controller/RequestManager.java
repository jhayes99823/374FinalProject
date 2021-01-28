package controller;
import java.util.List;

import models.ControllerResponse;
import models.Machine;
import models.Order;

public class RequestManager {
	List<MachineSelectionStrategy> selectionStrategies;
	NotificationManager notificationManager;

	public String handleRequest(String json) {
		Order order = JSONManager.parseOrderInput(json);
		Machine machine = performSelection(order).get(0);
		if(machine==null) {
			return "No machine found";
		}
		// Communicate with ControllerManager
		// to send request to machine and get
		// response back
		String commandStream =  JSONManager.createCommmandStream(order, machine);
		String controllerRespString = ControllerManager.dispatchCommand(commandStream, order.getOrderID());
		ControllerResponse controllerResponse = JSONManager.parseControllerResponse(controllerRespString);
		String appResponse = JSONManager.createAppResponse(controllerResponse, machine);
		return appResponse;
	}

	public List<Machine> performSelection(Order order) {
		List<Machine> machines = DatabaseConnection.getMachines();
		for(MachineSelectionStrategy strategy : selectionStrategies) {
			machines = strategy.selectMachines(order, machines);
		}
		return machines;
	}

	public void addSelectionStrategy(MachineSelectionStrategy selectionStrategy) {
		this.selectionStrategies.add(selectionStrategy);
	}
}
