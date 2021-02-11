package controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import models.ControllerResponse;
import models.ControllerResponseFactory;
import models.Drink;
import models.DrinkFactory;
import models.Machine;

public class RequestManager {
	List<MachineSelectionStrategy> selectionStrategies = new ArrayList<MachineSelectionStrategy>();
	NotificationManager notificationManager;

	public String handleRequest(String json) {
		JSONObject drinkJSON = (JSONObject) JSONValue.parse(json);
		Drink drink = DrinkFactory.createDrink(drinkJSON);
		Machine machine = performSelection(drink).get(0);
		if (machine == null) {
			return "No machine found";
		}
		// Communicate with ControllerManager
		// to send request to machine and get
		// response back
		String commandStream = JSONManager.createCommmandStream(drink, machine);
		String controllerRespString = ControllerManager.dispatchCommand(commandStream, drink.getOrderID());
		JSONObject controllerResponseJSON = (JSONObject) JSONValue.parse(controllerRespString);
		ControllerResponse controllerResponse = ControllerResponseFactory.createControllerResponse(controllerResponseJSON);
		String appResponse = JSONManager.createAppResponse(controllerResponse, machine);
		return appResponse;
	}

	public List<Machine> performSelection(Drink drink) {
		List<Machine> machines = Database.getInstance().getMachines();
		for (MachineSelectionStrategy strategy : selectionStrategies) {
			machines = strategy.selectMachines(drink, machines);
		}
		return machines;
	}

	public void addSelectionStrategy(MachineSelectionStrategy selectionStrategy) {
		this.selectionStrategies.add(selectionStrategy);
	}
}
