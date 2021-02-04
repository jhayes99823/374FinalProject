package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import models.ControllerResponse;
import models.Machine;
import models.Order;

public class JSONManager {
	public static String createCommmandStream(Order order, Machine machine) {
		JSONObject jsonCommand = new JSONObject();
		JSONObject commandBody = new JSONObject();
		commandBody.put("controller_id", machine.getController().getID());
		commandBody.put("coffee_machine_id", machine.getID());
		commandBody.put("orderID", order.getOrderID());
		commandBody.put("DrinkName", order.getDrink());
		if(order.hasCondiments()) {
			commandBody.put("Requesttype", "Automated"); //NEEDS TO BE FIXED
			JSONArray condimentsArray = new JSONArray();
			for(int i = 0; i < order.getCondiments().size(); i++) {
				JSONObject condiment = new JSONObject();
				condiment.put("Name",order.getCondiment(i).getName());
				condiment.put("qty", order.getCondiment(i).getQuantity());
				condimentsArray.add(condiment);
			}
			commandBody.put("Options:", condimentsArray);
		} else {
			commandBody.put("Requesttype", "Simple"); //NEEDS TO BE FIXED
		}
		jsonCommand.put("command", commandBody);
		return jsonCommand.toString();
	}
	
	public static String createAppResponse(ControllerResponse controllerResponse, Machine machine) {
		JSONObject jsonResponse = new JSONObject();
		JSONObject responseBody = new JSONObject();
		responseBody.put("orderID", controllerResponse.getOrderID());
		responseBody.put("coffee_machine_id", machine.getID());
		responseBody.put("status", controllerResponse.getStatus());
		if(controllerResponse.getStatus()==0) {
			responseBody.put("status-message", "Your coffee has been prepared with your desired options.");
		}else {
			responseBody.put("status-message", "Your coffee order has been cancelled.");
			responseBody.put("error-message", controllerResponse.getErrorDesc());
		}
		jsonResponse.put("user-response", responseBody);
		return jsonResponse.toString();
	}
}
