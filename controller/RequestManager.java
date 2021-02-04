package controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import models.ControllerResponse;
import models.ControllerResponseFactory;
import models.Machine;
import models.Order;
import models.OrderFactory;

public class RequestManager {
	List<MachineSelectionStrategy> selectionStrategies;
	NotificationManager notificationManager;

	public String handleRequest(String json) {
		JSONObject orderJSON = (JSONObject) JSONValue.parse(json);
		Order order = OrderFactory.createOrder(orderJSON);
		Machine machine = performSelection(order).get(0);
		if (machine == null) {
			return "No machine found";
		}
		// Communicate with ControllerManager
		// to send request to machine and get
		// response back
		String commandStream = JSONManager.createCommmandStream(order, machine);
		String controllerRespString = ControllerManager.dispatchCommand(commandStream, order.getOrderID());
		JSONObject controllerResponseJSON = (JSONObject) JSONValue.parse(controllerRespString);
		ControllerResponse controllerResponse = ControllerResponseFactory.createControllerResponse(controllerResponseJSON);
		String appResponse = JSONManager.createAppResponse(controllerResponse, machine);
		return appResponse;
	}

	public List<Machine> performSelection(Order order) {
		List<Machine> machines = Database.getInstance().getMachines();
		for (MachineSelectionStrategy strategy : selectionStrategies) {
			machines = strategy.selectMachines(order, machines);
		}
		return machines;
	}

	public void addSelectionStrategy(MachineSelectionStrategy selectionStrategy) {
		this.selectionStrategies.add(selectionStrategy);
	}
}
